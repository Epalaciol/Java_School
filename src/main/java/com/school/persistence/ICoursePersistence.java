package com.school.persistence;


import com.school.model.CourseModel;

public interface ICoursePersistence {

    default void save(CourseModel) {

    }
}
