package com.school.service;

import com.school.dto.GroupDto;

import java.util.Collection;

public interface IGroupService<T> {

    T create(GroupDto group);

    Collection<T> getAll(int pageNo, int pageSize, String sortBy);

    T getByCode(int groupCode);

    void update(GroupDto group, int groupCode);

    void deleteByCode(Integer groupCode);

    void assignTeacher(int groupCode, int teacherCode);

    void assignStudent(int groupCode, int studentCode);
}
