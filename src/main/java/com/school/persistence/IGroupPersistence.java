package com.school.persistence;

import com.school.model.GroupModel;

import java.util.Collection;

public interface IGroupPersistence<T> {

    T create(GroupModel group);

    Collection<T> getAll(int pageNo, int pageSize, String sortBy);

    T getByCode(int groupCode);

    void update(GroupModel group, int groupCode);

    void deleteByCode(Integer groupCode);

}
