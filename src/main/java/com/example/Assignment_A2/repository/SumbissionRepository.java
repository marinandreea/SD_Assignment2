package com.example.Assignment_A2.repository;

import com.example.Assignment_A2.model.Attendance;
import com.example.Assignment_A2.model.Submission;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SumbissionRepository extends CrudRepository<Submission, Integer> {
}
