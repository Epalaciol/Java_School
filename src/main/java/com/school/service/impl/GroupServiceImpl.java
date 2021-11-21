package com.school.service.impl;

import com.school.dto.GroupDto;
import com.school.model.CourseModel;
import com.school.model.GroupModel;
import com.school.model.StudentModel;
import com.school.model.TeacherModel;
import com.school.persistence.ICoursePersistence;
import com.school.persistence.IGroupPersistence;
import com.school.persistence.IStudentPersistence;
import com.school.persistence.ITeacherPersistence;
import com.school.service.IGroupService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;


@NoArgsConstructor
@Service
public class GroupServiceImpl implements IGroupService {

    @Autowired
    private IGroupPersistence groupPersistence;

    @Autowired
    private ICoursePersistence coursePersistence;

    @Autowired
    private ITeacherPersistence teacherPersistence;

    @Autowired
    private IStudentPersistence studentPersistence;


    @Override
    public Object create(GroupDto group) {

        GroupModel groupModel = assignGroupToCourse(
                new GroupModel(group), group.getCourseCode());

        if ( groupModel.getCourse() == null)
            throw new UnsupportedOperationException("Not able to create");

        return groupPersistence.create(groupModel);
    }

    @Override
    public Collection getAll() {
        return groupPersistence.getAll();
    }

    @Override
    public Object getByCode(int groupCode) {
        return groupPersistence.getByCode(groupCode);
    }

    @Override
    public void update(GroupDto group, int groupCode) {

        GroupModel groupModel = (GroupModel) getByCode(groupCode);
        GroupModel groupToChange = createGroupToUpdate(groupModel, group);
        groupPersistence.update(groupToChange, groupCode);
    }

    @Override
    public void deleteByCode(Integer groupCode) {
        groupPersistence.deleteByCode(groupCode);

    }

    @Override
    public void assignTeacher(int groupCode, int teacherCode) {

        try {
            TeacherModel teacherModel = (TeacherModel) teacherPersistence.getByCode(teacherCode);
            GroupModel groupModel = (GroupModel) getByCode(groupCode);
            groupModel.setTeacher(teacherModel);
            groupPersistence.update(groupModel, groupModel.getGroupCode());
        }catch (Exception e){
            throw new UnsupportedOperationException(e.getMessage());
        }
    }

    @Override
    public void assignStudent(int groupCode, int studentCode) {

        try {
            StudentModel studentModel = (StudentModel) studentPersistence.getStudentByCode(studentCode);
            GroupModel groupModel = (GroupModel) getByCode(groupCode);
            groupModel.getStudents().add(studentModel);
            groupPersistence.update(groupModel, groupModel.getGroupCode());
        }catch (Exception e){
            throw new UnsupportedOperationException(e.getMessage());
        }
    }


    private GroupModel createGroupToUpdate( GroupModel currentGroup, GroupDto futureGroup){

        currentGroup.setDate(futureGroup.getDate() == null ?
                currentGroup.getDate() :
                futureGroup.getDate());
        currentGroup.setHour(futureGroup.getHour() == null ?
                currentGroup.getHour() :
                futureGroup.getHour());

        return currentGroup;
    }

    private GroupModel assignGroupToCourse(GroupModel groupModel, int courseCode){
        try {
            CourseModel course = (CourseModel) coursePersistence.getByCode(courseCode);
            groupModel.setCourse(course);
            return groupModel;

        }catch (Exception e){
            throw new UnsupportedOperationException(e.getMessage());
        }
    }
}
