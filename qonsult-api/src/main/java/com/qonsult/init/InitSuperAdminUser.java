//package com.qonsult.init;
//
//import com.qonsult.entity.Group;
//import com.qonsult.entity.Schema;
//import com.qonsult.entity.User;
//import com.qonsult.repository.GroupRepository;
//import com.qonsult.repository.SchemaRepository;
//import com.qonsult.repository.UserRepository;
//import com.qonsult.service.UserService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityNotFoundException;
//
//
//@Component("initSuperAdminUser")
//@RequiredArgsConstructor
//public class InitSuperAdminUser implements DBInitializer{
//
//    private final UserRepository userRepository;
//
//    private final GroupRepository groupRepository;
//
//    private final UserService userService;
//
//    private final SchemaRepository dataSourceConfigRepository;
//
//    private final PasswordEncoder passwordEncoder;
//
//    @Transactional
//    public void initAdmin() {
//        if(!existAdmin()){
//            try {
//                createAdminUser();
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        }
//
//    }
//
//    public boolean existAdmin(){
//        return !userRepository.findAll().isEmpty();
//    }
//
//
//    public void createAdminUser() throws Exception {
//        User user = new User();
//        user.setUsername("admin");
//        user.setPassword(passwordEncoder.encode("admin"));
//        user.setEmail("admin@gmail.com");
//        user.setEmailChecked(true);
//        Group adminGroup = groupRepository.findByName("ADMIN").orElseThrow(()->new EntityNotFoundException("role does not exists"));
//        user.setGroup(adminGroup);
//        userService.saveUser(user);
//        Schema schema = new Schema();
//        schema.setUserName("admin");
//        schema.setName("public");
//        dataSourceConfigRepository.save(schema);
//
//    }
//
//
//    @Override
//    public void init() {
//        if(isAlreadyInitialized()){
//            return;
//        }
//        initAdmin();
//    }
//
//    @Override
//    public boolean isAlreadyInitialized() {
//        return !userRepository.findAll().isEmpty();
//    }
//}
