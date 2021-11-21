package com.school.model;

import com.school.dto.CourseDto;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "courses")
public class CourseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courseCode;
    @Column(unique = true)
    private String courseName;
    private String description;


    public  CourseModel(CourseDto courseDto, int courseCode){
        this.courseCode = courseCode;
        this.courseName = courseDto.getCourseName();
        this.description = courseDto.getDescription();
    }



}
