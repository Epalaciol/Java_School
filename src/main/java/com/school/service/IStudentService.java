package com.school.service;

import com.school.dto.StudentDto;

import java.util.Collection;

public interface IStudentService<T> {

    T  create(StudentDto student);

    Collection<T> getAll(int pageNo, int pageSize, String sortBy);

    T getByCode(Integer studentCode);

    T getByDocumentNumber(String documentNumber);

    void update(StudentDto student, int studentCode);

    void deleteByCode(Integer studentCode);

    void deleteByDocumentNumber(String documentNumber);
}
