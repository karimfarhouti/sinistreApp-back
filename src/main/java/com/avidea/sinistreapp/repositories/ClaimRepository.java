package com.avidea.sinistreapp.repositories;

import com.avidea.sinistreapp.domain.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {

    @Transactional(readOnly = true)
    Optional<Claim> findByNumber(Long number);
}
