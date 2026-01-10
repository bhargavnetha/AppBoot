package com.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boot.entity.Candidate;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {

}
