package com.school.persistence.impl;

import com.school.exception.SchoolRequestException;
import com.school.model.StudentModel;
import com.school.persistence.IStudentPersistence;
import com.school.persistence.repository.IStudentRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@NoArgsConstructor
public class StudentPersistenceImpl implements IStudentPersistence {

    @Autowired
    private IStudentRepository studentRepository;

    @Override
    public StudentModel createStudent(StudentModel student) {
        try {
            return studentRepository.save(student);
        } catch (Exception e) {
            throw new SchoolRequestException(e.getMessage());
        }
    }

    @Override
    public Collection<StudentModel> getAllStudents(int pageNo, int pageSize, String sortBy) {

        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        try {
            if (studentRepository.count() > 0)
                return studentRepository.findAll(paging).getContent();
        } catch (Exception e) {
            throw new SchoolRequestException(e.getMessage());
        }
        return studentRepository.findAll(paging).getContent();
    }

    @Override
    public StudentModel getStudentByCode(int studentCode) {
        try {
            return studentRepository.findById(studentCode);
        } catch (Exception e) {
            throw new SchoolRequestException(e.getMessage());
        }
    }

    @Override
    public StudentModel getStudentByDocumentNumber(String documentNumber) {
        try {
            return studentRepository.findBydocumentNumber(documentNumber);
        } catch (Exception e) {
            throw new SchoolRequestException(e.getMessage());
        }
    }

    @Override
    public void updateStudent(StudentModel student) {
        try {
            studentRepository.save(student);
        } catch (Exception e) {
            throw new SchoolRequestException(e.getMessage());
        }

    }

    @Override
    public void deleteStudentByCode(int studentCode) {

        try {
            studentRepository.deleteById(studentCode);
        } catch (Exception e) {
            throw new SchoolRequestException(e.getMessage());
        }

    }

    @Override
    public void deleteStudentByDocumentNumber(String documentNumber) {

        try {
            StudentModel studentByDocumentNumber =  getStudentByDocumentNumber(documentNumber);

            studentRepository.deleteById(studentByDocumentNumber.getStudentCode());
        } catch (Exception e) {
            throw new SchoolRequestException(e.getMessage());
        }

    }
}