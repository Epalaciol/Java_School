package com.school.controller;


import com.school.dto.TeacherDto;
import com.school.service.IStudentService;
import com.school.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/teachers")
public class TeacherRestController {

    @Autowired
    private ITeacherService teacherService;

    @GetMapping("")
    public ResponseEntity<?> getAllStudents() throws Exception {
        try {
            return new ResponseEntity<>(teacherService.getAll(), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(teacherService.getAll(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllStudentsTest() throws Exception {
        try {
            return new ResponseEntity<>(teacherService.getAll(), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(teacherService.getAll(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/code={teacherCode}")
    public ResponseEntity<?> getStudentByCode(@PathVariable int teacherCode) {

        try {
            Object student =  teacherService.getByCode(teacherCode );
            return new ResponseEntity<>(student, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);

        }
    }

    @GetMapping("/document={documentNumber}")
    public ResponseEntity<?> getStudentByDocumentNumber(@PathVariable String documentNumber) {

        try {
            Object student =  teacherService.getByDocumentNumber(documentNumber);
            return new ResponseEntity<>(student, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);

        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createStudent(@RequestBody TeacherDto teacherDto){
        try {
            teacherService.create(teacherDto);
            return new ResponseEntity<>("Student " + teacherDto.getName() + " create.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("User not Created", HttpStatus.CONFLICT);

        }
    }

    @PutMapping("/{teacherCode}")
    public ResponseEntity<?> updateStudent(@RequestBody TeacherDto teacherDto, @PathVariable Integer teacherCode){
        try {
            teacherService.update(teacherDto , teacherCode );
            return new ResponseEntity<>("Student " + teacherDto.getName() + " update.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);

        }
    }

    @DeleteMapping("/code={teacherCode}")
    public ResponseEntity<?> deleteStudentByCode(@PathVariable Integer teacherCode){
        try {
            teacherService.deleteByCode(teacherCode);
            return new ResponseEntity<>("Student deleted", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);

        }
    }

    @DeleteMapping("/document={documentNumber}")
    public ResponseEntity<?> deleteStudentByDocumentNumber(@PathVariable String documentNumber){
        try {
            teacherService.deleteByDocumentNumber(documentNumber);
            return new ResponseEntity<>("Student deleted", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);

        }
    }

}
