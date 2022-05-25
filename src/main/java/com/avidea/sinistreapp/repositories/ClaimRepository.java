package com.avidea.sinistreapp.repositories;

import com.avidea.sinistreapp.domain.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> { }
