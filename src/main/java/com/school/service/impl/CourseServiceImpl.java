package com.school.service.impl;

import com.school.dto.CourseDto;
import com.school.dto.CourseDto;
import com.school.model.CourseModel;
import com.school.model.TeacherModel;
import com.school.persistence.ICoursePersistence;
import com.school.service.ICourseService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

@NoArgsConstructor
@Service
public class CourseServiceImpl implements ICourseService {

    @Autowired
    private ICoursePersistence coursePersistence;

    @Override
    public Object create(CourseDto course) {

        int code = (int) UUID.randomUUID().hashCode();
        CourseModel courseModel= new CourseModel(course, code);
        return  coursePersistence.create(courseModel);
    }

    @Override
    public Collection getAll() {
        return coursePersistence.getAll();
    }

    @Override
    public Object getByCode(Integer courseCode) {
        return coursePersistence.getByCode(courseCode);
    }

    @Override
    public Collection getByName(String name) {
        return coursePersistence.getByName(name);
    }

    @Override
    public void update(CourseDto course, int courseCode) {

        CourseModel courseModel = (CourseModel) getByCode(courseCode);
        CourseModel courseToChange = createCourseToUpdate(courseModel, course);
        coursePersistence.update(courseToChange, courseCode);
    }

    @Override
    public void deleteByCode(Integer courseCode) {

        coursePersistence.deleteByCode(courseCode);
    }

    private CourseModel createCourseToUpdate(CourseModel currentCourse, CourseDto futureCourse){

        currentCourse.setCourseName(futureCourse.getCourseName() == null ?
                currentCourse.getCourseName() :
                futureCourse.getCourseName());
        currentCourse.setDescription(futureCourse.getDescription() == null ?
                currentCourse.getDescription() :
                futureCourse.getDescription());


        return currentCourse;
    }
}
