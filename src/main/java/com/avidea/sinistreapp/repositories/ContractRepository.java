package com.avidea.sinistreapp.repositories;

import com.avidea.sinistreapp.domain.Claim;
import com.avidea.sinistreapp.domain.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {

    @Transactional(readOnly = true)
    Optional<Contract> findByNumber(Long number);
}
