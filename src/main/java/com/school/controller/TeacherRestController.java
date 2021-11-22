package com.school.controller;


import com.school.dto.TeacherDto;
import com.school.exception.SchoolRequestException;
import com.school.model.TeacherModel;
import com.school.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/teachers")
public class TeacherRestController {

    @Autowired
    private ITeacherService teacherService;

    @GetMapping("")
    public ResponseEntity<Collection<TeacherModel>> getAllTeachers(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "5") Integer pageSize,
            @RequestParam(defaultValue = "teacherCode") String sortBy) {
        try {
            return new ResponseEntity<>(teacherService.getAll(pageNo,pageSize, sortBy), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            throw new SchoolRequestException("Can not get Teachers");
        }
    }

    @GetMapping("/code={teacherCode}")
    public ResponseEntity<TeacherModel> getTeacherByCode(@PathVariable int teacherCode) {

        try {
            TeacherModel student = (TeacherModel) teacherService.getByCode(teacherCode );
            return new ResponseEntity<>(student, HttpStatus.OK);
        } catch (Exception e) {
            throw new SchoolRequestException("Can not get Teacher by code");
        }
    }

    @GetMapping("/document={documentNumber}")
    public ResponseEntity<TeacherModel> getTeacherByDocumentNumber(@PathVariable String documentNumber) {

        try {
            TeacherModel student = (TeacherModel) teacherService.getByDocumentNumber(documentNumber);
            return new ResponseEntity<>(student, HttpStatus.OK);
        } catch (Exception e) {
            throw new SchoolRequestException("Can not get Teacher by Document Number");
        }
    }

    @PostMapping("/create")
    public ResponseEntity<String> createTeacher(@Valid @RequestBody TeacherDto teacherDto){
        try {
            teacherService.create(teacherDto);
            return new ResponseEntity<>("Teacher " + teacherDto.getName() + " create.", HttpStatus.CREATED);
        } catch (Exception e) {
            throw new SchoolRequestException("Teacher has not been created");
        }
    }

    @PutMapping("/{teacherCode}")
    public ResponseEntity<String> updateTeacher(@Valid @RequestBody TeacherDto teacherDto, @PathVariable Integer teacherCode){
        try {
            teacherService.update(teacherDto , teacherCode );
            return new ResponseEntity<>("Teacher update.", HttpStatus.CREATED);
        } catch (Exception e) {
            throw new SchoolRequestException("Teacher has not been updated" );
        }
    }

    @DeleteMapping("/code={teacherCode}")
    public ResponseEntity<String> deleteTeacherByCode(@PathVariable Integer teacherCode){
        try {
            teacherService.deleteByCode(teacherCode);
            return new ResponseEntity<>("Teacher deleted", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            throw new SchoolRequestException("Can not delete Teacher by code");
        }
    }

    @DeleteMapping("/document={documentNumber}")
    public ResponseEntity<String> deleteTeacherByDocumentNumber(@PathVariable String documentNumber){
        try {
            teacherService.deleteByDocumentNumber(documentNumber);
            return new ResponseEntity<>("Student deleted", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            throw new SchoolRequestException("Can not delete Teacher by document number");

        }
    }
}
