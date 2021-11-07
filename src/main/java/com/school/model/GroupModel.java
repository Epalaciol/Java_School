package com.school.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "groups")
public class GroupModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer subjectCode;
    private Date date;
    private Date hour;

    @ManyToMany(mappedBy = "subjects", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<StudentModel> students;

    @ManyToOne
    private  CourseModel course;

    @ManyToOne
    private TeacherModel teacher;



}
