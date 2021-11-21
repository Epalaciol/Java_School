package com.school.persistence.impl;

import com.school.model.TeacherModel;
import com.school.persistence.ITeacherPersistence;
import com.school.persistence.repository.ITeacherRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@NoArgsConstructor
public class TeacherPersistenceImpl implements ITeacherPersistence<TeacherModel> {

    @Autowired
    private ITeacherRepository teacherRepository;

    @Override
    public TeacherModel create(TeacherModel teacher) {
        try{
            return teacherRepository.save(teacher);
        } catch (Exception e) {
            throw new UnsupportedOperationException(e.getMessage());

        }
    }

    @Override
    public Collection<TeacherModel> getAll(int pageNo, int pageSize, String sortBy) {

        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        try {
            if (teacherRepository.count() > 0)
                return teacherRepository.findAll(paging).getContent();
        } catch (Exception e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
        return teacherRepository.findAll(paging).getContent();
    }

    @Override
    public TeacherModel getByCode(int teacherCode) {
        try {
            return teacherRepository.findById(teacherCode);
        } catch (Exception e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
    }

    @Override
    public TeacherModel getByDocumentNumber(String documentNumber) {
        try {
            return teacherRepository.findBydocumentNumber(documentNumber);
        } catch (Exception e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
    }

    @Override
    public void update(TeacherModel teacher, int teacherCode) {
        try {
            teacherRepository.save(teacher);
        } catch (Exception e) {
            throw new UnsupportedOperationException(e.getMessage());
        }

    }

    @Override
    public void deleteByCode(int teacherCode) {
        try {
            teacherRepository.deleteById(teacherCode);
        } catch (Exception e) {
            throw new UnsupportedOperationException(e.getMessage());
        }

    }

    @Override
    public void deleteByDocumentNumber(String documentNumber) {
        try {
            TeacherModel teacherByDocumentNumber = getByDocumentNumber(documentNumber);

            teacherRepository.deleteById(teacherByDocumentNumber.getTeacherCode());
        } catch (Exception e) {
            throw new UnsupportedOperationException(e.getMessage());
        }

    }
}
