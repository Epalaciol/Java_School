package com.school.persistence.impl;

import com.school.model.StudentModel;
import com.school.model.TeacherModel;
import com.school.persistence.ITeacherPersistence;
import com.school.persistence.repository.ITeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

public class TeacherPersistenceImpl implements ITeacherPersistence {

    @Autowired
    ITeacherRepository teacherRepository;

    @Override
    public Object create(TeacherModel teacher) {
        try{
            return teacherRepository.save(teacher);
        } catch (Exception e) {
            throw new UnsupportedOperationException("Teacher not saved");
        }
    }

    @Override
    public Collection getAll() {
        try {
            if (teacherRepository.count() > 0)
                return teacherRepository.findAll();
        } catch (Exception e) {
            throw new UnsupportedOperationException("Not supported yet");
        }
        return teacherRepository.findAll();
    }

    @Override
    public Object getByCode(int teacherCode) {
        try {
            return teacherRepository.findById(teacherCode);
        } catch (Exception e) {
            throw new UnsupportedOperationException("Not supported yet");
        }
    }

    @Override
    public Object getByDocumentNumber(String documentNumber) {
        try {
            return teacherRepository.findBydocumentNumber(documentNumber);
        } catch (Exception e) {
            throw new UnsupportedOperationException("Not supported yet");
        }
    }

    @Override
    public void update(TeacherModel teacher) {
        try {
            teacherRepository.save(teacher);
        } catch (Exception e) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

    }

    @Override
    public void deleteByCode(int teacherCode) {
        try {
            teacherRepository.deleteById(teacherCode);
        } catch (Exception e) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

    }

    @Override
    public void deleteByDocumentNumber(String documentNumber) {
        try {
            TeacherModel teacherByDocumentNumber = (TeacherModel) getByDocumentNumber(documentNumber);

            teacherRepository.deleteById(teacherByDocumentNumber.getTeacherCode());
        } catch (Exception e) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

    }
}
