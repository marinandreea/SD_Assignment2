package com.example.Assignment_A2.repository;

import com.example.Assignment_A2.model.Laboratory;
import com.example.Assignment_A2.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaboratoryRepository extends CrudRepository<Laboratory, Integer> {
}
