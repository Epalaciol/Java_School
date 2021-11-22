package com.school.persistence.impl;

import com.school.exception.SchoolRequestException;
import com.school.model.CourseModel;
import com.school.persistence.ICoursePersistence;
import com.school.persistence.repository.ICourseRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
@NoArgsConstructor
public class CoursePersistenceImpl implements ICoursePersistence {

    @Autowired
    private ICourseRepository courseRepository;

    @Override
    public CourseModel create(CourseModel course) {
        try {
            return courseRepository.save(course);
        } catch (Exception e) {
            throw new SchoolRequestException(e.getMessage());
        }
    }

    @Override
    public List<CourseModel> getAll(int pageNo, int pageSize, String sortBy) {

        Pageable paging = (Pageable) PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        try {
            if (courseRepository.count() > 0)
                return courseRepository.findAll( paging).getContent();
        } catch (Exception e) {
            throw new SchoolRequestException(e.getMessage());
        }
        return courseRepository.findAll(paging).getContent();
    }

    @Override
    public CourseModel getByCode(int courseCode) {
        try {
            return courseRepository.findById(courseCode);
        } catch (Exception e) {
            throw new SchoolRequestException(e.getMessage());
        }
    }

    @Override
    public List<CourseModel> getByName(String name) {
        try {
            return courseRepository.findByCourseNameContaining(name);
        } catch (Exception e) {
            throw new SchoolRequestException(e.getMessage());
        }
    }

    @Override
    public void update(CourseModel course, int courseCode) {
        try {
            courseRepository.save(course);
        } catch (Exception e) {
            throw new SchoolRequestException(e.getMessage());
        }

    }

    @Override
    public void deleteByCode(Integer courseCode) {
        try {
            courseRepository.deleteById(courseCode);
        } catch (Exception e) {
            throw new SchoolRequestException(e.getMessage());
        }
    }
}
