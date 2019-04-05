package com.example.demo.repository;

import com.example.demo.domain.Results;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StarWarsResultsRepository extends JpaRepository<Results , UUID> {
}
