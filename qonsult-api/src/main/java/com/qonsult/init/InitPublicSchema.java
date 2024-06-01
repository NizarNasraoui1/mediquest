package com.qonsult.init;

import com.qonsult.entity.auth.*;
import com.qonsult.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Component("initPublicSchema")
@RequiredArgsConstructor
public class InitPublicSchema implements DBInitializer {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final GroupRepository groupRepository;

    private final RoleRepository roleRepository;

    private final AccountRepository accountRepository;

    private final SchemaRepository schemaRepository;

    @Override
    public void init() {
        if(!isAlreadyInitialized()){
            Account account = new Account();
            accountRepository.save(account);

            User user = new User();
            user.setUsername("admin");
            user.setPassword(passwordEncoder.encode("admin"));
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
            }
            groupRepository.saveAll(groups);
            user.setGroup(adminGroup);
            userRepository.save(user);
            Schema schema = new Schema();
            schema.setName("public");
//            schema.setUserName("admin");
            schemaRepository.save(schema);
        }
    }
    @Override
    public boolean isAlreadyInitialized () {
        User user = userRepository.findByUsername("admin");
        return user != null;
    }
}
