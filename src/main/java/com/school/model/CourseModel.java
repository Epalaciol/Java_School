package com.school.model;

import com.school.dto.CourseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.security.auth.Subject;
import java.util.List;

@Entity
@NoArgsConstructor
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

    public  CourseModel(CourseDto courseDto, int courseCode){
        this.courseCode = courseCode;
        this.courseName = courseDto.getCourseName();
        this.description = courseDto.getDescription();
    }


}
