package com.school.persistence.impl;

import com.school.model.GroupModel;
import com.school.persistence.IGroupPersistence;
import com.school.persistence.repository.IGroupRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@NoArgsConstructor
public class GroupPersistenceImpl implements IGroupPersistence {

    @Autowired
    private IGroupRepository groupRepository;

    @Override
    public Object create(GroupModel group) {
        try {
            return groupRepository.save(group);
        } catch (Exception e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
    }

    @Override
    public Collection getAll() {
        try {
            if (groupRepository.count() > 0)
                return groupRepository.findAll();
        } catch (Exception e) {
            throw new UnsupportedOperationException("Not supported yet");
        }
        return groupRepository.findAll();
    }

    @Override
    public Object getByCode(int groupCode) {
        try {
            return groupRepository.findById(groupCode);
        } catch (Exception e) {
            throw new UnsupportedOperationException("Not supported yet");
        }
    }

    @Override
    public void update(GroupModel group, int groupCode) {
        try {
            groupRepository.save(group);
        } catch (Exception e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
    }

    @Override
    public void deleteByCode(Integer groupCode) {
        try {
            groupRepository.deleteById(groupCode);
        } catch (Exception e) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}
