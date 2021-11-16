package com.school.persistence;

import com.school.dto.CourseDto;
import com.school.model.CourseModel;

import java.util.Collection;

public interface ICoursePersistence<T> {

    T create(CourseModel course);

    Collection<T> getAll();

    T getByCode(Integer courseCode);

    Collection<T> getByName(String name);

    void update(CourseModel course, int courseCode);

    void deleteByCode(Integer courseCode);
}
