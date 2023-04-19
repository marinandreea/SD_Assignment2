package com.example.Assignment_A2.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceDTO {

    private int idAttendance;
    private int idStudent;
    private int idLab;
    private String present;

    public String toString(){
        return "idAttendance: " + idAttendance + "; id student: " + idStudent + "; id laboratory " + idLab + "; present: " + present;
    }
}
