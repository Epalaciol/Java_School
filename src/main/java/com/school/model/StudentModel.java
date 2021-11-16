package com.school.model;

import com.school.dto.StudentDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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
    private String documentNumber;
    private String name;
    private String password;
    private String contactNumber;
    private String email;
    private String hash;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<GroupModel> subjects;


    public StudentModel(StudentDto studentDto, int studentCode) {
        this.studentCode = studentCode;
        this.documentType = studentDto.getDocumentType();
        this.documentNumber = studentDto.getDocumentNumber();
        this.name = studentDto.getName();
        this.password = studentDto.getPassword();
        this.contactNumber = studentDto.getContactNumber();
        this.email = studentDto.getEmail();
    }
}

