package com.school.controller;

import com.school.model.StudentModel;
import com.school.persistence.repository.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class StudentRestController {

    @Autowired
    IStudentRepository IStudentRepository;

    @RequestMapping("/registro")
    public ResponseEntity<String> crearCliente(){

        StudentModel est = new StudentModel();
        est.setEmail("epl@gmail.com");
        est.setName("Prueba");

        try{
            IStudentRepository.save(est);
            return new ResponseEntity<>("Usuario Creado ", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
        }


    }
}
