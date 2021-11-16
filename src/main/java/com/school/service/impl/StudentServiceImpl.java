package com.school.service.impl;

import com.school.dto.StudentDto;
import com.school.model.StudentModel;
import com.school.persistence.IStudentPersistence;
import com.school.service.IStudentService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@NoArgsConstructor
@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private IStudentPersistence studentPersistence;

    @Override
    public Object create(StudentDto student) {

        int code = (int) UUID.randomUUID().hashCode();
        StudentModel studentModel = new StudentModel(student, code);
        studentPersistence.createStudent(studentModel);
        return studentModel;
    }

    @Override
    public Collection getAll() {

        return studentPersistence.getAllStudents();
    }

    @Override
    public Object getByCode(Integer studentCode) {
        return  studentPersistence.getStudentByCode(studentCode);
    }

    @Override
    public Object getByDocumentNumber(String documentNumber) {
        return studentPersistence.getStudentByDocumentNumber(documentNumber);
    }

    @Override
    public void update(StudentDto student, int studentCode) {

        StudentModel studentModel = (StudentModel) getByCode(studentCode);
        StudentModel studentToChange = createStudentToUpdate(studentModel, student);
        studentPersistence.updateStudent(studentToChange);

    }

    @Override
    public void deleteByCode(Integer studentCode) {

        studentPersistence.deleteStudentByCode(studentCode);
    }

    @Override
    public void deleteByDocumentNumber(String documentNumber) {

        studentPersistence.deleteStudentByDocumentNumber(documentNumber);
    }



    private StudentModel createStudentToUpdate(StudentModel currentStudent, StudentDto futureStudent) {

        currentStudent.setName(futureStudent.getName() == null ?
                currentStudent.getName() :
                futureStudent.getName());
        currentStudent.setContactNumber(futureStudent.getContactNumber() == null ?
                currentStudent.getContactNumber() :
                futureStudent.getContactNumber());
        currentStudent.setDocumentNumber(futureStudent.getDocumentNumber() == null ?
                currentStudent.getDocumentNumber() :
                futureStudent.getDocumentNumber());
        currentStudent.setDocumentType(futureStudent.getDocumentType() == null ?
                currentStudent.getDocumentType() :
                futureStudent.getDocumentType());
        currentStudent.setEmail(futureStudent.getEmail() == null ?
                currentStudent.getEmail() :
                futureStudent.getEmail());
        currentStudent.setPassword(futureStudent.getPassword() == null ?
                currentStudent.getPassword() :
                futureStudent.getPassword());
        return currentStudent;
    }


    private String encryptPassword(String password){

        String pwdEncrypt = "";

        return pwdEncrypt;
    }
}
