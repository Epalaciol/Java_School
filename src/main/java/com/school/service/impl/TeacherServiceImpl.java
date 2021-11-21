package com.school.service.impl;


import com.school.dto.TeacherDto;
import com.school.model.TeacherModel;
import com.school.persistence.ITeacherPersistence;
import com.school.service.ITeacherService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@NoArgsConstructor
@Service
public class TeacherServiceImpl implements ITeacherService {

    @Autowired
    private ITeacherPersistence teacherPersistence;


    @Override
    public Object create(TeacherDto teacher) {
        TeacherModel teacherModel= new TeacherModel(teacher);
        return  teacherPersistence.create(teacherModel);
    }

    @Override
    public Collection getAll(int pageNo, int pageSize, String sortBy) {

        return teacherPersistence.getAll(pageNo,pageSize, sortBy);
    }

    @Override
    public Object getByCode(Integer teacherCode) {
        return teacherPersistence.getByCode(teacherCode);
    }

    @Override
    public Object getByDocumentNumber(String documentNumber) {
        return teacherPersistence.getByDocumentNumber(documentNumber);
    }

    @Override
    public void update(TeacherDto teacher, int teacherCode) {

        TeacherModel teacherModel = (TeacherModel) getByCode(teacherCode);
        TeacherModel teacherToChange = createTeacherToUpdate(teacherModel, teacher);
        teacherPersistence.update(teacherToChange, teacherModel.getTeacherCode());

    }

    @Override
    public void deleteByCode(Integer teacherCode) {

        teacherPersistence.deleteByCode(teacherCode);

    }

    @Override
    public void deleteByDocumentNumber(String documentNumber) {

        teacherPersistence.deleteByDocumentNumber(documentNumber);

    }

    private TeacherModel createTeacherToUpdate(TeacherModel currentTeacher, TeacherDto futureTeacher){

        currentTeacher.setName(futureTeacher.getName() == null ?
                currentTeacher.getName() :
                futureTeacher.getName());
        currentTeacher.setDocumentNumber(futureTeacher.getDocumentNumber() == null ?
                currentTeacher.getDocumentNumber() :
                futureTeacher.getDocumentNumber());
        currentTeacher.setDocumentType(futureTeacher.getDocumentType() == null ?
                currentTeacher.getDocumentType() :
                futureTeacher.getDocumentType());
        currentTeacher.setEmail(futureTeacher.getEmail() == null ?
                currentTeacher.getEmail() :
                futureTeacher.getEmail());

        return currentTeacher;
    }
}
