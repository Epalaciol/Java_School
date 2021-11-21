package com.school.persistence;

import com.school.model.TeacherModel;

import java.util.Collection;

public interface ITeacherPersistence<T> {

    T create(TeacherModel teacher);

    Collection<T> getAll(int pageNo, int pageSize, String sortBy);

    T getByCode( int teacherCode);

    T getByDocumentNumber(String documentNumber);

    void update(TeacherModel teacher, int teacherCode);

    void deleteByCode(int teacherCode);

    void deleteByDocumentNumber(String documentNumber);

}
