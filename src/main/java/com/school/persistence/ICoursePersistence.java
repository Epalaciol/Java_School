package com.school.persistence;

import com.school.model.CourseModel;

import java.util.Collection;
import java.util.List;

public interface ICoursePersistence<T> {

    T create(CourseModel course);

    List<CourseModel> getAll(int pageNo, int pageSize, String sortBy);

    T getByCode(int courseCode);

    Collection<T> getByName(String name);

    void update(CourseModel course, int courseCode);

    void deleteByCode(Integer courseCode);
}
