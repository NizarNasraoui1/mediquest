//package com.qonsult.init;
//
//import com.qonsult.entity.auth.Group;
//import com.qonsult.exception.RoleAlreadyExistsException;
//import com.qonsult.repository.GroupRepository;
//import com.qonsult.service.UserService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//
//@Component("initCenterAdminRole")
//@RequiredArgsConstructor
//public class InitCenterAdminRole implements DBInitializer {
//
//    public final GroupRepository groupRepository;
//
//    private final UserService userService;
//    @Override
//    public void init() throws RoleAlreadyExistsException {
//        if(isAlreadyInitialized()){
//            return ;
//        }
//        Group adminCenterGroup =new Group();
//        adminCenterGroup.setName("CENTER_ADMIN");
//        adminCenterGroup.setRoles(userService.getAllPermissions());
//        userService.saveRole(adminCenterGroup);
//    }
//
//    @Override
//    public boolean isAlreadyInitialized() {
//        return !groupRepository.findAll().isEmpty();
//    }
//}
