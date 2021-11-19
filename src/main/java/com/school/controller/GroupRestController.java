package com.school.controller;

import com.school.dto.GroupDto;
import com.school.service.IGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/groups")
public class GroupRestController {

    @Autowired
    private IGroupService groupService;

    @GetMapping("")
    public ResponseEntity<?> getAllGroups() throws Exception {
        try {
            return new ResponseEntity<>(groupService.getAll(), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(groupService.getAll(), HttpStatus.NOT_FOUND);
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
    public ResponseEntity<?> createGroup(@RequestBody GroupDto groupDto) throws  Exception{
        try {
            Object o = groupService.create(groupDto);
            return new ResponseEntity<>("Group created", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Group not Created "+ e.getMessage() , HttpStatus.CONFLICT);

        }
    }

    @PutMapping("/{groupCode}")
    public ResponseEntity<?> updateGroup(@RequestBody GroupDto groupDto, @PathVariable Integer groupCode){
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

    @PostMapping
    public ResponseEntity<?> assignTeacher(@RequestParam("teacher") int teacherCode, @RequestParam("group") int groupCode) {
        try {
            groupService.assignTeacher(teacherCode, groupCode);
            return new ResponseEntity<>("Teacher assinged succesfully ", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);

        }
    }
}
