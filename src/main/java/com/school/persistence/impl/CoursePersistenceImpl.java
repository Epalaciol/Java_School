package com.school.persistence.impl;

import com.school.model.CourseModel;
import com.school.persistence.ICoursePersistence;
import com.school.persistence.repository.ICourseRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@NoArgsConstructor
public class CoursePersistenceImpl implements ICoursePersistence {

    @Autowired
    private ICourseRepository courseRepository;

    @Override
    public Object create(CourseModel course) {
        try {
            return courseRepository.save(course);
        } catch (Exception e) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    @Override
    public Collection getAll() {
        try {
            if (courseRepository.count() > 0)
                return courseRepository.findAll();
        } catch (Exception e) {
            throw new UnsupportedOperationException("Not supported yet");
        }
        return courseRepository.findAll();
    }

    @Override
    public Object getByCode(Integer courseCode) {
        try {
            return courseRepository.findById(courseCode);
        } catch (Exception e) {
            throw new UnsupportedOperationException("Not supported yet");
        }
    }

    @Override
    public Collection getByName(String name) {
        try {
            return courseRepository.findByCourseNameContaining(name);
        } catch (Exception e) {
            throw new UnsupportedOperationException("Not supported yet");
        }
    }

    @Override
    public void update(CourseModel course, int courseCode) {
        try {
            courseRepository.save(course);
        } catch (Exception e) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

    }

    @Override
    public void deleteByCode(Integer courseCode) {
        try {
            courseRepository.deleteById(courseCode);
        } catch (Exception e) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}
