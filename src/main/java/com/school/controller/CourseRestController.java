package com.school.controller;

import com.school.dto.CourseDto;
import com.school.exception.SchoolRequestException;
import com.school.model.CourseModel;
import com.school.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/courses")
public class CourseRestController {

    @Autowired
    private ICourseService courseService;

    @GetMapping("")
    public ResponseEntity<List<CourseModel>> getAllCourses(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "5") Integer pageSize,
            @RequestParam(defaultValue = "courseCode") String sortBy) {

        try {
            List<CourseModel> list = courseService.getAll(pageNo, pageSize, sortBy);

            return new ResponseEntity<>(list,  HttpStatus.OK);
        } catch (Exception e) {
            throw new SchoolRequestException("Can not get Courses");
        }
    }

    @GetMapping("/code={courseCode}")
    public ResponseEntity<CourseModel> getCourseByCode(@PathVariable int courseCode) {

        try {
            CourseModel student = (CourseModel) courseService.getByCode(courseCode );
            return new ResponseEntity<>(student, HttpStatus.OK);
        } catch (Exception e) {
            throw new SchoolRequestException("Can not get Course by code");

        }
    }

    @GetMapping("/name={name}")
    public ResponseEntity<List<CourseModel>> getCourseByName(
            @PathVariable String name,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "courseName") String sortBy) {

        try {
            List<CourseModel> course = (List<CourseModel>) courseService.getByName(name.toLowerCase());
            return new ResponseEntity<>(course, HttpStatus.OK);
        } catch (Exception e) {
            throw new SchoolRequestException("Can not get Courses by name");

        }
    }

    @PostMapping("/create")
    public ResponseEntity<String> createTeacher(@Valid @RequestBody CourseDto courseDto) {
        try {
            courseService.create(courseDto);
            return new ResponseEntity<>("Course " + courseDto.getCourseName() + " created.", HttpStatus.CREATED);
        } catch (Exception e) {
            throw new SchoolRequestException("Course has not been created");
        }
    }

    @PutMapping("/{courseCode}")
    public ResponseEntity<String> updateTeacher(@Valid @RequestBody CourseDto courseDto, @PathVariable Integer courseCode){
        try {
            courseService.update(courseDto , courseCode );
            return new ResponseEntity<>("Course update.", HttpStatus.CREATED);
        } catch (Exception e) {
            throw new SchoolRequestException("Course has not been updated");

        }
    }

    @DeleteMapping("/code={courseCode}")
    public ResponseEntity<String> deleteTeacherByCode(@PathVariable Integer courseCode){
        try {
            courseService.deleteByCode(courseCode);
            return new ResponseEntity<>("Course deleted", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            throw new SchoolRequestException("Can not delete course");

        }
    }


}
