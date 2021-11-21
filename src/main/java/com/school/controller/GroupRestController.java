package com.school.controller;

import com.school.dto.GroupDto;
import com.school.service.IGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/groups")
public class GroupRestController {

    @Autowired
    private IGroupService groupService;

    @GetMapping("")
    public ResponseEntity<?> getAllGroups(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "5") Integer pageSize,
            @RequestParam(defaultValue = "groupCode") String sortBy) throws Exception {
        try {
            return new ResponseEntity<>(groupService.getAll(pageNo,pageSize, sortBy), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(groupService.getAll(pageNo,pageSize, sortBy), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/code={groupCode}")
    public ResponseEntity<?> getGroupByCode(@PathVariable int groupCode) {

        try {
            Object student =  groupService.getByCode(groupCode );
            return new ResponseEntity<>(student, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);

        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createGroup(@Valid @RequestBody GroupDto groupDto) throws  Exception{
        try {
            groupService.create(groupDto);
            return new ResponseEntity<>("Group created", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Group not Created "+ e.getMessage() , HttpStatus.CONFLICT);

        }
    }

    @PutMapping("/{groupCode}")
    public ResponseEntity<?> updateGroup(@Valid @RequestBody GroupDto groupDto, @PathVariable Integer groupCode){
        try {
            groupService.update(groupDto , groupCode );
            return new ResponseEntity<>("Group update.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);

        }
    }

    @DeleteMapping("/code={groupCode}")
    public ResponseEntity<?> deleteGroupByCode(@PathVariable Integer groupCode){
        try {
            groupService.deleteByCode(groupCode);
            return new ResponseEntity<>("Group deleted", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);

        }
    }

    @PostMapping("/assignTeacher")
    public ResponseEntity<?> assignTeacher( @RequestParam("group") int groupCode, @RequestParam("teacher") int teacherCode)  throws  Exception {
        try {
            groupService.assignTeacher(groupCode, teacherCode);
            return new ResponseEntity<>("Teacher assinged succesfully ", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);

        }
    }

    @PostMapping("/assignStudent")
    public ResponseEntity<?> assignStudent( @RequestParam("group") int groupCode, @RequestParam("student") int studentCode)  throws  Exception {
        try {
            groupService.assignStudent(groupCode, studentCode);
            return new ResponseEntity<>("Teacher assinged succesfully ", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);

        }
    }
}
