package com.school.persistence.repository;

import com.school.model.TeacherModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITeacherRepository extends JpaRepository<TeacherModel, Integer> {

    TeacherModel findById(int id);

    TeacherModel findBydocumentNumber(String documentNumber);
}
