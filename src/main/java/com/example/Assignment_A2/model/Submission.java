package com.example.Assignment_A2.model;

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
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSubmission;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "assignmentId",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    //@JsonIgnore
    private Assignment assignmentId;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "userId",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    //@JsonIgnore
    private User userId;
    @Column
    private String link;
    @Column
    private String comment;
    @Column
    private float grade;

}
