package com.school.model;

import com.school.dto.CourseDto;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private String courseName;
    private String description;

//    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval=true)
//    private List<GroupModel> groups = new ArrayList<>();

    public  CourseModel(CourseDto courseDto, int courseCode){
        this.courseCode = courseCode;
        this.courseName = courseDto.getCourseName();
        this.description = courseDto.getDescription();
    }


}
