package com.school.controller;

import com.school.dto.StudentDto;
import com.school.model.StudentModel;
import com.school.security.PasswordUtils;
import com.school.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


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
    public ResponseEntity<?> createStudent(@Valid @RequestBody StudentDto studentDto){
        try {
            studentService.create(studentDto);
            return new ResponseEntity<>("Student " + studentDto.getName() + " create.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("User not Created " + e.getMessage(), HttpStatus.CONFLICT);

        }
    }

    @PutMapping("/{studentCode}")
    public ResponseEntity<?> updateStudent(@Valid @RequestBody StudentDto studentDto, @PathVariable Integer studentCode){
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

    @PostMapping("/testPwd")
    public ResponseEntity<?> test(@RequestParam("user") Integer studentCode, @RequestParam("password") String pwd) {

        try {
            StudentModel student = (StudentModel) studentService.getByCode(studentCode );
            String passWord = student.getPassword();
            String salt = student.getHash();

            boolean pwdMatch = PasswordUtils.verifyUserPassword(pwd, passWord, salt);

            if( pwdMatch){
                return  new ResponseEntity<>(" La contrasena ingresada si es correcta", HttpStatus.OK);
            } else {
                return  new ResponseEntity<>(" La contrasena ingresada no es correcta", HttpStatus.CONFLICT);

            }

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);

        }
    }
}
