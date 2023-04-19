package com.example.Assignment_A2.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubmissionDTO {

    private int idSubmission;
    private int idAssignment;
    private int idStudent;
    private String link;
    private String comment;
    private float grade;

    public String toString(){
        return "idSubmission: " + idSubmission + "; idAssignment: " + idAssignment + "; idStudent: " + idStudent + "; git link: "+ link +"; comment: " + comment+ "; grade: "+ grade;
    }
}
