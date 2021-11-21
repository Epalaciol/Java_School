package com.school.service;

import com.school.dto.CourseDto;
import com.school.model.CourseModel;

import java.util.Collection;
import java.util.List;

public interface ICourseService<T> {

    T create(CourseDto course);

    List<CourseModel> getAll(int pageNo, int pageSize, String sortBy);

    T getByCode(Integer courseCode);

    Collection<T> getByName(String name);

    void update(CourseDto course, int courseCode);

    void deleteByCode(Integer courseCode);
}
