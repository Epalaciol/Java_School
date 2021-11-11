package com.school.controller;

import com.school.dto.StudentDto;
import com.school.model.StudentModel;
import com.school.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/students")
public class StudentRestController {

    @Autowired
    private IStudentService studentService;

    @GetMapping("")
    public ResponseEntity<?> getAllStudents() throws Exception {
        try {
            return new ResponseEntity<>(studentService.getAll(), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(studentService.getAll(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllStudentsTest() throws Exception {
        try {
            return new ResponseEntity<>(studentService.getAll(), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(studentService.getAll(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/code={studentCode}")
    public ResponseEntity<?> getStudentByCode(@PathVariable int studentCode) {

        try {
            Object student =  studentService.getByCode(studentCode );
            return new ResponseEntity<>(student, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);

        }
    }

    @GetMapping("/document={documentNumber}")
    public ResponseEntity<?> getStudentByDocumentNumber(@PathVariable String documentNumber) {

        try {
            Object student =  studentService.getByDocumentNumber(documentNumber);
            return new ResponseEntity<>(student, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);

        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createStudent(@RequestBody StudentDto studentDto){
        try {
            studentService.create(studentDto);
            return new ResponseEntity<>("Student " + studentDto.getName() + " create.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("User not Created", HttpStatus.CONFLICT);

        }
    }

    @PutMapping("/{studentCode}")
    public ResponseEntity<?> updateStudent(@RequestBody StudentDto studentDto, @PathVariable Integer studentCode){
        try {
            studentService.update(studentDto , studentCode );
            return new ResponseEntity<>("Student " + studentDto.getName() + " update.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);

        }
    }

    @DeleteMapping("/code={studentCode}")
    public ResponseEntity<?> deleteStudentByCode(@PathVariable Integer studentCode){
        try {
            studentService.deleteByCode(studentCode);
            return new ResponseEntity<>("Student deleted", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);

        }
    }

    @DeleteMapping("/document={documentNumber}")
    public ResponseEntity<?> deleteStudentByDocumentNumber(@PathVariable String documentNumber){
        try {
            studentService.deleteByDocumentNumber(documentNumber);
            return new ResponseEntity<>("Student deleted", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);

        }
    }



}
