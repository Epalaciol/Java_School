package com.school.service;

import com.school.dto.GroupDto;

import java.util.Collection;

public interface IGroupService<T> {

    T create(GroupDto group);

    Collection<T> getAll();

    T getByCode(Integer groupCode);

    void update(GroupDto group, int groupCode);

    void deleteByCode(Integer groupCode);

}
