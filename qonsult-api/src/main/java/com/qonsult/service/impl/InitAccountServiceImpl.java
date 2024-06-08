package com.qonsult.service.impl;

import com.qonsult.config.tenant_config.DataSourceBasedMultiTenantConnectionProviderImpl;
import com.qonsult.dto.AccountDTO;
import com.qonsult.dto.RegisterDTO;
import com.qonsult.entity.*;
import com.qonsult.entity.auth.*;
import com.qonsult.init.MigrateDB;
import com.qonsult.repository.*;
import com.qonsult.service.AccountService;
import com.qonsult.service.InitAccountService;
import com.qonsult.service.QuestionnaireRequestService;
import com.qonsult.util.JPAUtility;
import lombok.RequiredArgsConstructor;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ConfigurationBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.util.*;

@Service
@RequiredArgsConstructor
public class InitAccountServiceImpl implements InitAccountService {

    private final AccountRepository accountRepository;

    private final AccountService accountService;

    private final GroupRepository groupRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final SchemaRepository schemaRepository;

    private final JdbcTemplate jdbcTemplate;

    private final DataSourceBasedMultiTenantConnectionProviderImpl dataSourceBasedMultiTenantConnectionProvider;

    private final MigrateDB migrateDB;

    private final QuestionnaireRequestService questionnaireRequestService;


    public void register(RegisterDTO registerDTO) throws Exception{
        AccountDTO accountDTO = registerDTO.getCenter();
        String schemaName = "schema_"+ UUID.randomUUID().toString().substring(0,6);
        String adminUsername = UUID.randomUUID().toString();
        String adminPassword = UUID.randomUUID().toString();
        createSchema(schemaName);
        dataSourceBasedMultiTenantConnectionProvider.addDataSource(schemaName);
        migrateSchema(schemaName);
        Schema schema = new Schema();
        schema.setName(schemaName);
        schemaRepository.save(schema);
        Account account = accountService.addNewAccount(accountDTO);
        account.setSchema(schema);
        accountRepository.save(account);
        User user = new User();
        user.setUsername(adminUsername);
        user.setPassword(passwordEncoder.encode(adminPassword));
        user.setEmailChecked(true);

        ArrayList<Group> groups = new ArrayList<>();
        Group adminGroup = new Group("ADMIN");
        Group doctorGroup = new Group("DOCTOR");
        Group othersGroup = new Group("OTHERS");
        groups.addAll(Arrays.asList(adminGroup, doctorGroup, othersGroup));
        List<Role> roles = roleRepository.findAll();
        for(Role role:roles){
            role.setGroups(groups);
        }
        for (Group group : groups) {
            group.setRoles(roles);
            group.setAccount(account);
        }
        groupRepository.saveAll(groups);
        user.setGroup(adminGroup);
        userRepository.save(user);
        createAndSaveQuestionnaireModel(schemaName);
    }

    public void createSchema(String schemaName) {
        String sql = "CREATE SCHEMA " + schemaName;
        jdbcTemplate.execute(sql);
    }

    public void migrateSchema(String schemaName) {
        DataSource dataSource = dataSourceBasedMultiTenantConnectionProvider.selectDataSource(schemaName);
        migrateDB.migrateSchema(dataSource);
    }

    public void createAndSaveQuestionnaireModel(String schemaName) {
        DataSource dataSource = dataSourceBasedMultiTenantConnectionProvider.selectDataSource(schemaName);
        EntityManagerFactory emf = JPAUtility.createEntityManagerFactory(dataSource);
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            Set<Class<?>> classes = getAllClassesInPackage("com.qonsult.init.models");

            for (Class<?> cls : classes) {
                Method getModelMethod = cls.getMethod("getModel");
                QuestionnaireModel questionnaireModel = (QuestionnaireModel) getModelMethod.invoke(null);

                em.persist(questionnaireModel);

                QuestionnaireRequest questionnaireRequest = questionnaireRequestService.createQuestionnaireRequestFromModel(questionnaireModel);
                em.persist(questionnaireRequest);
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            em.close();
            emf.close();
        }
    }
    public static Set<Class<?>> getAllClassesInPackage(String packageName) {
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .forPackages(packageName) // Specify the package to scan
                .addScanners(new SubTypesScanner(false))); // Include SubTypesScanner, without excluding Object class
        return reflections.getSubTypesOf(Object.class); // Fetch all subclasses of Object (i.e., all classes)
    }
}
