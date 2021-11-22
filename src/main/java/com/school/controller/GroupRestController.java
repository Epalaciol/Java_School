package com.school.controller;

import com.school.dto.GroupDto;
import com.school.exception.SchoolRequestException;
import com.school.model.GroupModel;
import com.school.service.IGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/groups")
public class GroupRestController {

    @Autowired
    private IGroupService groupService;

    @GetMapping("")
    public ResponseEntity<Collection<GroupModel>> getAllGroups(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "5") Integer pageSize,
            @RequestParam(defaultValue = "groupCode") String sortBy) {
        try {
            Collection<GroupModel> list = groupService.getAll(pageNo, pageSize, sortBy);

            return new ResponseEntity<>(list, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            throw new SchoolRequestException("Can not get Courses");
        }
    }

    @GetMapping("/code={groupCode}")
    public ResponseEntity<GroupModel> getGroupByCode(@PathVariable int groupCode) {

        try {
            GroupModel student = (GroupModel) groupService.getByCode(groupCode);
            return new ResponseEntity<>(student, HttpStatus.OK);
        } catch (Exception e) {
            throw new SchoolRequestException("Can not get Course by code");
        }
    }

    @PostMapping("/create")
    public ResponseEntity<String> createGroup(@Valid @RequestBody GroupDto groupDto) {
        try {
            groupService.create(groupDto);
            return new ResponseEntity<>("Group created", HttpStatus.CREATED);
        } catch (Exception e) {
            throw new SchoolRequestException("Group has not been created");
        }
    }

    @PutMapping("/{groupCode}")
    public ResponseEntity<String> updateGroup(@Valid @RequestBody GroupDto groupDto, @PathVariable Integer groupCode){
        try {
            groupService.update(groupDto , groupCode );
            return new ResponseEntity<>("Group update.", HttpStatus.CREATED);
        } catch (Exception e) {
            throw new SchoolRequestException("Group has not been updated");
        }
    }

    @DeleteMapping("/code={groupCode}")
    public ResponseEntity<String> deleteGroupByCode(@PathVariable Integer groupCode){
        try {
            groupService.deleteByCode(groupCode);
            return new ResponseEntity<>("Group deleted", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            throw new SchoolRequestException("Can not delete Group code by code");
        }
    }

    @PostMapping("/assignTeacher")
    public ResponseEntity<String> assignTeacher( @RequestParam("group") int groupCode, @RequestParam("teacher") int teacherCode)   {
        try {
            groupService.assignTeacher(groupCode, teacherCode);
            return new ResponseEntity<>("Teacher assigned successfully ", HttpStatus.CREATED);
        } catch (Exception e) {
            throw new SchoolRequestException("Can not assign Teacher to a Group");
        }
    }

    @PostMapping("/assignStudent")
    public ResponseEntity<String> assignStudent( @RequestParam("group") int groupCode, @RequestParam("student") int studentCode)   {
        try {
            groupService.assignStudent(groupCode, studentCode);
            return new ResponseEntity<>("Student assigned successfully ", HttpStatus.CREATED);
        } catch (Exception e) {
            throw new SchoolRequestException("Can not assign Student to a Group");
        }
    }
}
