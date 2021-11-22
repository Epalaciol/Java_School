package com.school.persistence.impl;

import com.school.exception.SchoolRequestException;
import com.school.model.GroupModel;
import com.school.persistence.IGroupPersistence;
import com.school.persistence.repository.IGroupRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@NoArgsConstructor
public class GroupPersistenceImpl implements IGroupPersistence {

    @Autowired
    private IGroupRepository groupRepository;

    @Override
    public GroupModel create(GroupModel group) {
        try {
            return groupRepository.save(group);
        } catch (Exception e) {
            throw new SchoolRequestException(e.getMessage());
        }
    }

    @Override
    public Collection<GroupModel> getAll(int pageNo, int pageSize, String sortBy) {

        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        try {
            if (groupRepository.count() > 0)
                return groupRepository.findAll(paging).getContent();
        } catch (Exception e) {
            throw new SchoolRequestException(e.getMessage());
        }
        return groupRepository.findAll(paging).getContent();
    }

    @Override
    public GroupModel getByCode(int groupCode) {
        try {
            return groupRepository.findById(groupCode);
        } catch (Exception e) {
             throw new SchoolRequestException(e.getMessage());
        }
    }

    @Override
    public void update(GroupModel group, int groupCode) {
        try {
            groupRepository.save(group);
        } catch (Exception e) {
            throw new SchoolRequestException(e.getMessage());
        }
    }

    @Override
    public void deleteByCode(Integer groupCode) {
        try {
            groupRepository.deleteById(groupCode);
        } catch (Exception e) {
            throw new SchoolRequestException(e.getMessage());
        }
    }
}
