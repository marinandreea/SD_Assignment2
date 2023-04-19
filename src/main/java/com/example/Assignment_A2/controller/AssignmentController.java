package com.example.Assignment_A2.controller;

import com.example.Assignment_A2.model.Assignment;
import com.example.Assignment_A2.model.Attendance;
import com.example.Assignment_A2.model.Laboratory;
import com.example.Assignment_A2.model.dto.AssignmentDTO;
import com.example.Assignment_A2.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@EnableMethodSecurity
public class AssignmentController {

    @Autowired
    public AssignmentService assignmentService;

    @RequestMapping("/assignment")
    @PreAuthorize("hasAuthority('TEACHER')")
    public List<AssignmentDTO> getAllAssignments(){
        return assignmentService.findAll();
    }

    @RequestMapping("/assignment/{id}")
    @PreAuthorize("hasAuthority('TEACHER')")
    public Optional<String> getAssignmentById(@PathVariable int id){
        return Optional.ofNullable(assignmentService.getAssignmentById(id));
    }

    @RequestMapping("/assignmentLab/{idLab}")
    @PreAuthorize("hasAuthority('STUDENT')")
    public Optional<String> getAssignmentByLabId(@PathVariable int idLab){
        return Optional.ofNullable(assignmentService.getAssignmentByLabId(idLab));
    }



    @RequestMapping(method = RequestMethod.POST,value = "/assignment/create")
    @PreAuthorize("hasAuthority('TEACHER')")
    public String addAssignment(@RequestBody AssignmentDTO assignmentDTO){
        return assignmentService.addAssignment(assignmentDTO);
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/assignment/update")
    @PreAuthorize("hasAuthority('TEACHER')")
    public String updateAssignment(@RequestBody AssignmentDTO assignment){
        return assignmentService.updateAssignment(assignment);
    }

    @RequestMapping(method = RequestMethod.DELETE,value="/assignment/delete/{id}")
    @PreAuthorize("hasAuthority('TEACHER')")
    public String deleteAssignment(@PathVariable int id){
        return assignmentService.deleteAssignment(id);
    }
}
