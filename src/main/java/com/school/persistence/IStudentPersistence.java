package com.school.persistence;

import com.school.model.StudentModel;

import java.util.Collection;

public interface IStudentPersistence<T> {

    T  createStudent(StudentModel student);

    Collection<T> getAllStudents();

    T getStudentByCode(int studentCode);

    T getStudentByDocumentNumber(String documentNumber);

    void updateStudent(StudentModel student);

    void deleteStudentByCode(int studentCode);

    void deleteStudentByDocumentNumber(String documentNumber);
}
