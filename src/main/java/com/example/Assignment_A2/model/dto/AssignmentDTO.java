package com.example.Assignment_A2.model.dto;

import com.example.Assignment_A2.model.Assignment;
import com.example.Assignment_A2.model.Laboratory;
import com.example.Assignment_A2.service.LaboratoryService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssignmentDTO {

    private int idAssignment;
    private String name;
    private String deadline;
    private String description;
    private int labNr;



    public String toString(){
        return "id: " + this.idAssignment
                + "; name: " + this.name
                + "; deadline: " + this.deadline
                + "; assignment description: " + this.description
                + "; laboratory id: " + this.labNr;
    }


}
