package com.school.persistence.impl;

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
    public Object createStudent(StudentModel student) {
        try {
            return studentRepository.save(student);
        } catch (Exception e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
    }

    @Override
    public Collection getAllStudents(int pageNo, int pageSize, String sortBy) {

        Pageable paging = (Pageable) PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        try {
            if (studentRepository.count() > 0)
                return studentRepository.findAll(paging).getContent();
        } catch (Exception e) {
            throw new UnsupportedOperationException("Not supported yet");
        }
        return studentRepository.findAll(paging).getContent();
    }

    @Override
    public Object getStudentByCode(int studentCode) {
        try {
            return studentRepository.findById(studentCode);
        } catch (Exception e) {
            throw new UnsupportedOperationException("Not supported yet");
        }
    }

    @Override
    public Object getStudentByDocumentNumber(String documentNumber) {
        try {
            return studentRepository.findBydocumentNumber(documentNumber);
        } catch (Exception e) {
            throw new UnsupportedOperationException("Not supported yet");
        }
    }

    @Override
    public void updateStudent(StudentModel student) {
        try {
            studentRepository.save(student);
        } catch (Exception e) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

    }

    @Override
    public void deleteStudentByCode(int studentCode) {

        try {
            studentRepository.deleteById(studentCode);
        } catch (Exception e) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

    }

    @Override
    public void deleteStudentByDocumentNumber(String documentNumber) {

        try {
            StudentModel studentByDocumentNumber = (StudentModel) getStudentByDocumentNumber(documentNumber);

            studentRepository.deleteById(studentByDocumentNumber.getStudentCode());
        } catch (Exception e) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

    }
}