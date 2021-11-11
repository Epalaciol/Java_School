package com.school.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
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

    @OneToMany
    private List<GroupModel> groups;
}
