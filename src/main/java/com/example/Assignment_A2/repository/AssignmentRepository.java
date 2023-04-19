package com.example.Assignment_A2.repository;

import com.example.Assignment_A2.model.Assignment;
import com.example.Assignment_A2.model.Attendance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentRepository extends CrudRepository<Assignment, Integer> {
}
