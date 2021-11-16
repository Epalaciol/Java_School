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
public class TeacherDto implements Serializable {

    private String documentType;
    private String documentNumber;
    private String name;
    private String email;

    // Should implement a list to receive groups
    // groups must exists before assignation


}
