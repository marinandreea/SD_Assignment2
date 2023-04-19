package com.example.Assignment_A2.controller;

import com.example.Assignment_A2.model.Submission;
import com.example.Assignment_A2.model.dto.AssignmentDTO;
import com.example.Assignment_A2.model.dto.SubmissionDTO;
import com.example.Assignment_A2.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@EnableMethodSecurity
public class SubmissionController {

    @Autowired
    private SubmissionService submissionService;

    @RequestMapping("/submission")
    @PreAuthorize("hasAuthority('TEACHER')")
    public List<SubmissionDTO> getAllSubmissions(){
        return submissionService.getAllSubmissions();
    }

    @RequestMapping("/submission/{id}")
    @PreAuthorize("hasAuthority('TEACHER')")
    public List<SubmissionDTO> getAllSubmissionsForAnAssig(@PathVariable int id){
        return submissionService.getAllSubmissionsForAnAssignment(id);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/submission/create")
    @PreAuthorize("hasAuthority('STUDENT')")
    public String addSubmission(@RequestBody SubmissionDTO submissionDTO){
        return submissionService.addSubmission(submissionDTO);
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/submission/updateStudent")
    @PreAuthorize("hasAuthority('STUDENT')")
    public String updateSubmission(@RequestBody SubmissionDTO submissionDTO){
        return submissionService.updateSubmission(submissionDTO);
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/submission/gradeAss/{idSubmission}/{grade}")
    @PreAuthorize("hasAuthority('TEACHER')")
    public String gradeSubmission(@PathVariable int idSubmission, @PathVariable float grade){
        return submissionService.gradeSubmission(idSubmission,grade);
    }

    @RequestMapping(method = RequestMethod.DELETE,value="/submission/delete/{id}")
    @PreAuthorize("hasAuthority('STUDENT')")
    public String deleteAssignment(@PathVariable int id){
        return submissionService.deleteSubmission(id);
    }


}
