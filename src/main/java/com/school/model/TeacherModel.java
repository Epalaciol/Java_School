package com.school.model;

import com.school.dto.TeacherDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "teachers")
public class TeacherModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int teacherCode;
    private String documentType;

    @Column(unique = true)
    private String documentNumber;
    private String name;

    @Column(unique = true)
    private String email;

    public TeacherModel(TeacherDto teacherDto) {
        this.documentType = teacherDto.getDocumentType();
        this.documentNumber = teacherDto.getDocumentNumber();
        this.name = teacherDto.getName();
        this.email = teacherDto.getEmail();
    }
}
