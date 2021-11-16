package com.school.persistence.repository;

import com.school.model.StudentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudentRepository extends JpaRepository<StudentModel, Integer> {

    StudentModel findById(int id);

    StudentModel findBydocumentNumber(String documentNumber);
}
