package com.school.persistence.repository;

import com.school.model.CourseModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICourseRepository extends PagingAndSortingRepository<CourseModel, Integer> {

    CourseModel findById(int id);

    List<CourseModel> findByCourseNameContaining(String name);

    Page<CourseModel> findAll(Pageable paging);

}
