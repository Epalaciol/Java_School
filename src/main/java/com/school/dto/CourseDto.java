package com.school.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CourseDto implements Serializable {

    private int courseCode;
    private String courseName;
    private String description;

    // Should implement a list with groups and create groups


}
