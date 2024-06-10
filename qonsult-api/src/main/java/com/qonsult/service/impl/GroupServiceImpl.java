package com.qonsult.service.impl;

import com.qonsult.config.tenant_config.TenantSchemaResolver;
import com.qonsult.entity.auth.Group;
import com.qonsult.repository.GroupRepository;
import com.qonsult.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    @Override
    public List<Group> findAllBySchema(String schemaName) {
        return groupRepository.findAll(GroupRepository.hasSchemaName(schemaName));
    }

    @Override
    public Group saveGroup(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public Group findById(Long id) {
        return groupRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("group not found"));
    }
}
