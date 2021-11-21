package com.school.model;

import com.school.dto.GroupDto;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "groups")
public class GroupModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int groupCode;
    private String date;
    private String hour;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<StudentModel> students;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "courseCode")
    private CourseModel course;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "teacherCode")
    private TeacherModel teacher;

    public GroupModel(GroupDto groupDto){


        this.date = groupDto.getDate();
        this.hour = groupDto.getHour();
    }



}
