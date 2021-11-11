package com.school.service;

import com.school.dto.StudentDto;
import com.school.model.StudentModel;

import java.util.Collection;
import java.util.Optional;

public interface IStudentService<T> {

    T  create(StudentDto student);

    Collection<T> getAll();

    T getByCode(Integer studentCode);

    T getByDocumentNumber(String documentNumber);

    void update(StudentDto student, int studentCode);

    void deleteByCode(Integer studentCode);

    void deleteByDocumentNumber(String documentNumber);
}
