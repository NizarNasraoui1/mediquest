package com.qonsult.service.impl;

import com.qonsult.config.tenant_config.DataSourceBasedMultiTenantConnectionProviderImpl;
import com.qonsult.config.tenant_config.TenantDataSource;
import com.qonsult.dto.AccountDTO;
import com.qonsult.dto.RegisterDTO;
import com.qonsult.entity.*;
import com.qonsult.entity.auth.*;
import com.qonsult.init.InitQuestionnaires;
import com.qonsult.init.MigrateDB;
import com.qonsult.init.models.DentistQuestionnaireModel;
import com.qonsult.repository.*;
import com.qonsult.service.AccountService;
import com.qonsult.service.InitAccountService;
import com.qonsult.service.QuestionnaireRequestService;
import com.qonsult.util.JPAUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

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
        Account account = accountService.addCenter(accountDTO);
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

            QuestionnaireModel questionnaireModel = DentistQuestionnaireModel.getModel();

            em.persist(questionnaireModel);

            QuestionnaireRequest questionnaireRequest = questionnaireRequestService.createQuestionnaireRequestFromModel(questionnaireModel);
            em.persist(questionnaireRequest);

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
