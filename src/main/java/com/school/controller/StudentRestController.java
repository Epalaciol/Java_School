package com.school.controller;

import com.school.dto.StudentDto;
import com.school.exception.SchoolRequestException;
import com.school.model.StudentModel;
import com.school.security.PasswordUtils;
import com.school.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/students")
public class StudentRestController {

    @Autowired
    private IStudentService studentService;

    @GetMapping("")
    public ResponseEntity<Collection<StudentModel>> getAllStudents(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "5") Integer pageSize,
            @RequestParam(defaultValue = "studentCode") String sortBy) throws Exception {
        try {
            return new ResponseEntity<>(studentService.getAll(pageNo,pageSize, sortBy), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            throw new SchoolRequestException("Can not get Students");
        }
    }

    @GetMapping("/code={studentCode}")
    public ResponseEntity<StudentModel> getStudentByCode(@PathVariable int studentCode) {

        try {
            StudentModel student = (StudentModel) studentService.getByCode(studentCode );
            return new ResponseEntity<>(student, HttpStatus.OK);
        } catch (Exception e) {
            throw new SchoolRequestException("Can not find Student by code");

        }
    }

    @GetMapping("/document={documentNumber}")
    public ResponseEntity<StudentModel> getStudentByDocumentNumber(@PathVariable String documentNumber) {

        try {
            StudentModel student = (StudentModel) studentService.getByDocumentNumber(documentNumber);
            return new ResponseEntity<>(student, HttpStatus.OK);
        } catch (Exception e) {
            throw new SchoolRequestException("Can not get Student by documentNumber");

        }
    }

    @PostMapping("/create")
    public ResponseEntity<String> createStudent(@Valid @RequestBody StudentDto studentDto){
        try {
            studentService.create(studentDto);
            return new ResponseEntity<>("Student " + studentDto.getName() + " create.", HttpStatus.CREATED);
        } catch (Exception e) {
            throw new SchoolRequestException("Student has not been created");

        }
    }

    @PutMapping("/{studentCode}")
    public ResponseEntity<String> updateStudent(@Valid @RequestBody StudentDto studentDto, @PathVariable Integer studentCode){
        try {
            studentService.update(studentDto , studentCode );
            return new ResponseEntity<>("Student " + studentDto.getName() + " update.", HttpStatus.CREATED);
        } catch (Exception e) {
            throw new SchoolRequestException("Student has not been updated");
        }
    }

    @DeleteMapping("/code={studentCode}")
    public ResponseEntity<String> deleteStudentByCode(@PathVariable Integer studentCode){
        try {
            studentService.deleteByCode(studentCode);
            return new ResponseEntity<>("Student deleted", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            throw new SchoolRequestException("Can not delete Student by code");
        }
    }

    @DeleteMapping("/document={documentNumber}")
    public ResponseEntity<String> deleteStudentByDocumentNumber(@PathVariable String documentNumber){
        try {
            studentService.deleteByDocumentNumber(documentNumber);
            return new ResponseEntity<>("Student deleted", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            throw new SchoolRequestException("Can not delete Student by documentNumber");
        }
    }

    @PostMapping("/testPwd")
    public ResponseEntity<String> test(@RequestParam("user") Integer studentCode, @RequestParam("password") String pwd) {

        try {
            StudentModel student = (StudentModel) studentService.getByCode(studentCode );
            String passWord = student.getPassword();
            String salt = student.getHash();

            boolean pwdMatch = PasswordUtils.verifyUserPassword(pwd, passWord, salt);

            if(pwdMatch){
                return  new ResponseEntity<>("Password successfully accepted", HttpStatus.OK);
            } else {
                return  new ResponseEntity<>("Password or Student incorrect", HttpStatus.CONFLICT);
            }

        } catch (Exception e) {
            throw new SchoolRequestException("Can not verify Password");
        }
    }
}
