package com.school.persistence.repository;

import com.school.model.GroupModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGroupRepository extends PagingAndSortingRepository<GroupModel, Integer> {

    GroupModel findById(int id);

    Page<GroupModel> findAll(Pageable paging);
}
