package com.school.persistence.impl;

import com.school.persistence.ICoursePersistence;
import com.school.persistence.repository.ICourseRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CoursePersistenceImpl implements ICoursePersistence {

    @Autowired
    ICourseRepository courseRepository;

    @Override
    public void save() {
        courseRepository.save();
    }
}
