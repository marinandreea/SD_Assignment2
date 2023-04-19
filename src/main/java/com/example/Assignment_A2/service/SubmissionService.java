package com.example.Assignment_A2.service;

import com.example.Assignment_A2.model.Assignment;
import com.example.Assignment_A2.model.Submission;
import com.example.Assignment_A2.model.User;
import com.example.Assignment_A2.model.dto.SubmissionDTO;
import com.example.Assignment_A2.repository.AssignmentRepository;
import com.example.Assignment_A2.repository.SumbissionRepository;
import com.example.Assignment_A2.repository.UserRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubmissionService {

    @Autowired
    private SumbissionRepository submissionRepository;
    @Autowired
    private AssignmentRepository assignmentRepository;
    @Autowired
    private UserRepository userRepository;

    public Submission toEntity(SubmissionDTO submissionDTO) {

        Optional<Assignment> assignment = assignmentRepository.findById(submissionDTO.getIdAssignment());
        Optional<User> student = userRepository.findById(submissionDTO.getIdStudent());

        return Submission.builder()
                .assignmentId(assignment.get())
                .userId(student.get())
                .link(submissionDTO.getLink())
                .comment(submissionDTO.getComment())
                .grade(submissionDTO.getGrade())
                .build();
    }

    public static SubmissionDTO fromEntity(Submission submission) {

        return SubmissionDTO.builder()
                .idSubmission(submission.getIdSubmission())
                .idAssignment(submission.getAssignmentId().getIdAssignment())
                .idStudent(submission.getUserId().getIdStudent())
                .link(submission.getLink())
                .comment(submission.getComment())
                .grade(submission.getGrade())
                .build();
    }

    public String addSubmission(SubmissionDTO submissionDTO){

        Submission submission = toEntity(submissionDTO);
        Optional<Assignment> assignment = assignmentRepository.findById(submissionDTO.getIdAssignment());
        Optional<User> student = userRepository.findById(submissionDTO.getIdStudent());

        if(assignment == null){
            return "No assignment with id " + submissionDTO.getIdAssignment() + " was found!";
        }else if(student == null){
            return "No student with id " + submissionDTO.getIdStudent() + " was found!";
        }else{
            submissionRepository.save(submission);
            return "Submission created successfully!";
        }
    }

    public String updateSubmission(SubmissionDTO submissionDTO){

        val submission = submissionRepository.findById(submissionDTO.getIdSubmission());
        if(submission.isPresent()){
            submission.get().setLink(submissionDTO.getLink());
            submission.get().setComment(submissionDTO.getComment());
            submissionRepository.save(submission.get());
            return "Submission updated successfully!";
        }else{
            return "No submission with id " + submissionDTO.getIdSubmission() + " was found!";
        }
    }

    public String deleteSubmission(int id){
        Optional<Submission> submission = submissionRepository.findById(id);
        if(submission != null){
            submissionRepository.delete(submission.get());
            return "Submission deleted successfully!";
        }else{
            return "No submission with id " + id + " was found!";
        }
    }

    public List<SubmissionDTO> getAllSubmissions(){
        List<Submission> s = (List<Submission>) submissionRepository.findAll();
        return s.stream().map(SubmissionService::fromEntity).collect(Collectors.toList());
    }

    public List<SubmissionDTO> getAllSubmissionsForAnAssignment(int idAssignment){
        List<Submission> submissions = (List<Submission>)submissionRepository.findAll();
        List<SubmissionDTO> subb = new ArrayList<>();
        for(Submission s: submissions){
            if(s.getAssignmentId().getIdAssignment() == idAssignment){
                subb.add(fromEntity(s));
            }
        }
        return subb;

    }

    public String gradeSubmission(int idSubmission, float grade){
        Optional<Submission> submission = submissionRepository.findById(idSubmission);
        if(submission.isPresent()){
            submission.get().setGrade(grade);
            submissionRepository.save(submission.get());
            return "Submission was graded successfully!";
        }else{
            return "No submission with id " + idSubmission + "was found!";
        }



    }


}
