package com.school.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentDto implements Serializable {

    private String documentType;
    @Size(min=2, max=30)
    private String documentNumber;
    @Size(min=2, max=30)
    private String name;
    private String password;
    private String contactNumber;
    @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+[\\.[A-Za-z0-9_-]+]*@[^-][A-Za-z0-9-]+[\\.[A-Za-z0-9-]+]*(\\.[A-Za-z]{2,})$")
    private String email;

}
