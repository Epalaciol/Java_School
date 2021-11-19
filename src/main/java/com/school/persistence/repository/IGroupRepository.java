package com.school.persistence.repository;

import com.school.model.GroupModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGroupRepository extends JpaRepository<GroupModel, Integer> {

    GroupModel findById(int id);
}
