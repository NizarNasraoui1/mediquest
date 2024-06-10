package com.qonsult.service;

import com.qonsult.entity.auth.Group;

import java.util.List;

public interface GroupService {
    List<Group>findAllBySchema(String schemaName);
    Group saveGroup(Group group);
    Group findById(Long id);
}
