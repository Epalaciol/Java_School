package com.school.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.security.auth.Subject;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "courses")
public class CourseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courseCode;
    private String courseName;
    private String description;

    @OneToMany
    private List<GroupModel> groups;

}
