package com.school.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.school.dto.StudentDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "students")
public class StudentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentCode;
    private String documentType;

    @Column(unique = true)
    private String documentNumber;
    private String name;

    @JsonIgnore
    private String password;
    private String contactNumber;

    @Column(unique = true)
    private String email;

    @JsonIgnore
    private String hash;

    public StudentModel(StudentDto studentDto,  String hash , String passwordEncoded) {
        this.documentType = studentDto.getDocumentType();
        this.documentNumber = studentDto.getDocumentNumber();
        this.name = studentDto.getName();
        this.password = passwordEncoded;
        this.contactNumber = studentDto.getContactNumber();
        this.email = studentDto.getEmail();
        this.hash = hash;
    }
}

