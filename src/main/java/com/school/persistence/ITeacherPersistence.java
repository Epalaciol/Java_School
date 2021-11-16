package com.school.persistence;

import com.school.model.TeacherModel;

import java.util.Collection;

public interface ITeacherPersistence<T> {

    T create(TeacherModel teacher);

    Collection<T> getAll();

    T getByCode( int teacherCode);

    T getByDocumentNumber(String documentNumber);

    void update(TeacherModel teacher);

    void deleteByCode(int teacherCode);

    void deleteByDocumentNumber(String documentNumber);

}
