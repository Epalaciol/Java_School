package com.school.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CourseDto implements Serializable {

    private int courseCode;

    @NotNull
    @Size(min = 2, max = 30)
    private String courseName;
    private String description;

}
