package com.example.Assignment_A2.service;

import com.example.Assignment_A2.model.Assignment;
import com.example.Assignment_A2.model.Laboratory;
import com.example.Assignment_A2.model.Role;
import com.example.Assignment_A2.model.User;
import com.example.Assignment_A2.model.dto.AssignmentDTO;
import com.example.Assignment_A2.repository.AssignmentRepository;
import com.example.Assignment_A2.repository.LaboratoryRepository;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AssignmentService {

    @Autowired
    private AssignmentRepository assignmentRepository;
    @Autowired
    private LaboratoryService laboratoryService;

    public Assignment toEntity(AssignmentDTO assignmentDTO) {

        Optional<Laboratory> lab = laboratoryService.getLabById(assignmentDTO.getLabNr());

        return Assignment.builder()
                .name(assignmentDTO.getName())
                .description(assignmentDTO.getDescription())
                .deadline(assignmentDTO.getDeadline())
                .labAssignment(lab.get())
                .build();

    }

    public static AssignmentDTO fromEntity(Assignment assignment) {

        return AssignmentDTO.builder()
                .idAssignment(assignment.getIdAssignment())
                .name(assignment.getName())
                .description(assignment.getDescription())
                .deadline(assignment.getDeadline())
                .labNr(assignment.getLabAssignment().getNrLab())
                .build();
    }


        public List<AssignmentDTO> findAll() {

            List<Assignment> a = (List<Assignment>) assignmentRepository.findAll();
            return a.stream().map(AssignmentService::fromEntity).collect(Collectors.toList());


        }
   // }

    public String addAssignment(AssignmentDTO assignmentDTO){

        Assignment assignment = toEntity(assignmentDTO);

        assignmentRepository.save(assignment);
        return "Assignment created successfully!";


    }

    public String updateAssignment(AssignmentDTO assignmentDTO){

        var ass = assignmentRepository.findById(assignmentDTO.getIdAssignment());
        if(ass.isPresent()) {
            Assignment assignment = ass.get();
            Optional<Laboratory> lab = laboratoryService.getLabByNr(assignmentDTO.getLabNr());
            if (lab != null) {
                assignment.setLabAssignment(lab.get());
                assignment.setName(assignmentDTO.getName());
                assignment.setDeadline(assignmentDTO.getDeadline());
                assignment.setDescription(assignmentDTO.getDescription());
                assignmentRepository.save(assignment);
                return "Assignment was updated successfully!";
            }else{
                return "No laboratory with nr " + lab.get().getNrLab() + " was found!";
            }
        }
        return "No assignment with id " + ass.get().getIdAssignment() + " was found!";

    }

    public String deleteAssignment(int id){

        assignmentRepository.deleteById(id);
        return "Assignment was deleted successfully!";

    }

    public String getAssignmentById(int id){
        var ass = assignmentRepository.findById(id);
        if(ass.isPresent()){
            return fromEntity(ass.get()).toString();
        }
        return " ";
    }

    public String getAssignmentByLabId(int nr){
        var lab = laboratoryService.getLabByNr(nr);
        if(lab.isPresent()){
            List<Assignment> assignments = (List<Assignment>) assignmentRepository.findAll();
            for(Assignment a:assignments){
                if(fromEntity(a).getLabNr() == nr){
                    return fromEntity(a).toString();
                }
            }
        }
        return " ";
    }
}
