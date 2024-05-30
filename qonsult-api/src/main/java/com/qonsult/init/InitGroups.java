package com.qonsult.init;

import com.qonsult.entity.Group;
import com.qonsult.exception.RoleAlreadyExistsException;
import com.qonsult.repository.GroupRepository;
import com.qonsult.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component("initSuperAdminRole")
@RequiredArgsConstructor
public class InitGroups implements DBInitializer {

    public final GroupRepository groupRepository;

    private final UserService userService;
    @Override
    public void init() throws RoleAlreadyExistsException {
        if(isAlreadyInitialized()){
            return ;
        }
        ArrayList<Group> groups = new ArrayList<>();
        groups.add(new Group("ADMIN"));
        groups.add(new Group("DOCTOR"));
        groups.add(new Group("OTHERS"));
        userService.saveRoles(groups);
    }

    @Override
    public boolean isAlreadyInitialized() {
        return !groupRepository.findAll().isEmpty();
    }
}
