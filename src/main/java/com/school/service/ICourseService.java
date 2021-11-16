package com.school.service;

import com.school.dto.CourseDto;
import com.school.dto.StudentDto;

import java.util.Collection;

public interface ICourseService<T> {

    T create(CourseDto course);

    Collection<T> getAll();

    T getByCode(Integer courseCode);

    Collection<T> getByName(String name);

    void update(CourseDto course, int courseCode);

    void deleteByCode(Integer courseCode);
}
