package com.example.Assignment_A2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Optional;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAssignment;
    @Column
    private String name;
    @Column
    private String deadline;
    @Column
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "labNr",unique = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    //@JsonIgnore
    //@Nullable
    private Laboratory labAssignment;

//    @OneToMany(mappedBy = "assignmentId")
//    private List<Submission> submissonList;



}
