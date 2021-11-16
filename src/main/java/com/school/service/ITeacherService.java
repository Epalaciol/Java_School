package com.school.service;

import com.school.dto.StudentDto;
import com.school.dto.TeacherDto;

import java.util.Collection;

public interface ITeacherService<T> {

    T  create(TeacherDto teacher);

    Collection<T> getAll();

    T getByCode(Integer teacherCode);

    T getByDocumentNumber(String documentNumber);

    void update(TeacherDto teacher, int teacherCode);

    void deleteByCode(Integer teacherCode);

    void deleteByDocumentNumber(String documentNumber);
}
