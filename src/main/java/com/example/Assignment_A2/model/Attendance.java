package com.example.Assignment_A2.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAttendance;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "studentId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    //@JsonIgnore
    private User userId;
    //@JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "labNr")
    @OnDelete(action = OnDeleteAction.CASCADE)
    //@JsonIgnore
    private Laboratory labId;
    @Column
    private String present;


}
