package com.example.Assignment_A2.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Laboratory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLab;
    @Column
    private int nrLab;
    @Column
    private String date;
    @Column
    private String title;
    @Column
    private String curricula;
    @Column
    private String description;
//    @JsonManagedReference
//    @OneToMany(mappedBy = "labNr")
//    private List<Attendance> attendanceList;
//
//    @OneToOne(mappedBy = "labAssignment")
//    private Assignment labAssignment;
}
