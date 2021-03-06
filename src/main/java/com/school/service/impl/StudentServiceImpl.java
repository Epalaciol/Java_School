package com.school.service.impl;

import com.school.dto.StudentDto;
import com.school.model.StudentModel;
import com.school.persistence.IStudentPersistence;
import com.school.security.PasswordUtils;
import com.school.service.IStudentService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@NoArgsConstructor
@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private IStudentPersistence studentPersistence;

    @Override
    public StudentModel create(StudentDto student) {

        String[] passwordEncoded = encryptPassword(student.getPassword());
        StudentModel studentModel = new StudentModel(student, passwordEncoded[0], passwordEncoded[1]);
        studentPersistence.createStudent(studentModel);
        return studentModel;
    }

    @Override
    public Collection<StudentModel> getAll(int pageNo, int pageSize, String sortBy) {

        return studentPersistence.getAllStudents(pageNo, pageSize, sortBy);
    }

    @Override
    public StudentModel getByCode(Integer studentCode) {
        return (StudentModel) studentPersistence.getStudentByCode(studentCode);
    }

    @Override
    public StudentModel getByDocumentNumber(String documentNumber) {
        return (StudentModel) studentPersistence.getStudentByDocumentNumber(documentNumber);
    }

    @Override
    public void update(StudentDto student, int studentCode) {

        StudentModel studentModel = getByCode(studentCode);
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


    private String[] encryptPassword(String password){

        String salt = PasswordUtils.getSalt(30);
        String mySecurePassword = PasswordUtils.generateSecurePassword(password, salt);

        return new String[]{salt, mySecurePassword};
    }
}
