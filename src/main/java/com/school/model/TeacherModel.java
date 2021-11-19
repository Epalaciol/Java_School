package com.school.model;

import com.school.dto.TeacherDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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
    private String documentNumber;
    private String name;
    private String email;

//    @OneToMany(cascade = CascadeType.REMOVE,orphanRemoval=true)
//    private List<GroupModel> groups;

    public TeacherModel(TeacherDto teacherDto, int teacherCode) {
        this.teacherCode = teacherCode;
        this.documentType = teacherDto.getDocumentType();
        this.documentNumber = teacherDto.getDocumentNumber();
        this.name = teacherDto.getName();
        this.email = teacherDto.getEmail();
    }
}
