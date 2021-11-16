package com.school.persistence.repository;

import com.school.model.CourseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICourseRepository extends JpaRepository<CourseModel, Integer> {


    List<CourseModel> findByCourseNameContaining(String name);
}
