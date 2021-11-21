package com.school.controller;

import com.school.dto.CourseDto;
import com.school.model.CourseModel;
import com.school.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

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
            @RequestParam(defaultValue = "courseCode") String sortBy) throws Exception {

        try {
            List<CourseModel> list = (List<CourseModel>) courseService.getAll(pageNo, pageSize, sortBy);

            return new ResponseEntity<>(list,  HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/code={courseCode}")
    public ResponseEntity<?> getCourseByCode(@PathVariable int courseCode) {

        try {
            Object student =  courseService.getByCode(courseCode );
            return new ResponseEntity<>(student, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);

        }
    }

    @GetMapping("/name={name}")
    public ResponseEntity<?> getCourseByName(
            @PathVariable String name,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "courseName") String sortBy) {

        try {
            Object student =  courseService.getByName(name.toLowerCase());
            return new ResponseEntity<>(student, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);

        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createTeacher(@Valid @RequestBody CourseDto courseDto) throws  Exception{
        try {
            courseService.create(courseDto);
            return new ResponseEntity<>("Teacher " + courseDto.getCourseName() + " create.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Teacher not Created "+ e.getMessage() , HttpStatus.CONFLICT);

        }
    }

    @PutMapping("/{courseCode}")
    public ResponseEntity<?> updateTeacher(@Valid @RequestBody CourseDto courseDto, @PathVariable Integer courseCode){
        try {
            courseService.update(courseDto , courseCode );
            return new ResponseEntity<>("Student " + courseDto.getCourseName() + " update.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);

        }
    }

    @DeleteMapping("/code={courseCode}")
    public ResponseEntity<?> deleteTeacherByCode(@PathVariable Integer courseCode){
        try {
            courseService.deleteByCode(courseCode);
            return new ResponseEntity<>("Student deleted", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);

        }
    }


}
