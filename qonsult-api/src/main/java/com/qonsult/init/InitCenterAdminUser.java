//package com.qonsult.init;
//
//import com.qonsult.entity.auth.Group;
//import com.qonsult.entity.auth.User;
//import com.qonsult.repository.GroupRepository;
//import com.qonsult.service.UserService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//
//@Component
//@RequiredArgsConstructor
//public class InitCenterAdminUser{
//
//    private final GroupRepository groupRepository;
//
//    private final UserService userService;
//
//    private final PasswordEncoder passwordEncoder;
//
//
//    public void createAdminUser(String username) {
//        User user = new User();
//        user.setUsername(username);
//        user.setPassword(passwordEncoder.encode(username));
//        user.setEmailChecked(true);
//        Group adminGroup =  groupRepository.findAll().get(0);
//        user.setGroup(adminGroup);
//        userService.saveUser(user);
//
//    }
//}
