package com.avidea.sinistreapp.web.rest;

import com.avidea.sinistreapp.ApplicationConstants;
import com.avidea.sinistreapp.domain.Claim;
import com.avidea.sinistreapp.domain.Contract;
import com.avidea.sinistreapp.repositories.ClaimRepository;
import com.avidea.sinistreapp.repositories.ContractRepository;
import com.avidea.sinistreapp.services.dtos.ClaimDTO;
import com.avidea.sinistreapp.services.mapper.ClaimMapper;
import com.avidea.sinistreapp.web.rest.exceptions.ClaimNotFoundException;
import com.avidea.sinistreapp.web.rest.exceptions.ClaimNumberAlreadyExistsException;
import com.avidea.sinistreapp.web.rest.exceptions.ContractNumberAlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class ClaimResource {

    private static final Logger log = LoggerFactory.getLogger(ClaimResource.class);
    private final ClaimRepository claimRepository;
    private final ContractRepository contractRepository;

    private final ClaimMapper claimMapper;

    public ClaimResource(ClaimRepository claimRepository, ContractRepository contractRepository, ClaimMapper claimMapper) {
        this.claimRepository = claimRepository;
        this.contractRepository = contractRepository;
        this.claimMapper = claimMapper;
    }


    @PostMapping("/claims")
    public ResponseEntity<Claim> createClaim(@Valid @RequestBody ClaimDTO claimDTO) {

        final Long claimNumber = claimDTO.getClaimNumber();

        log.debug("claim number: {}", claimNumber);

        final Optional<Claim> maybeClaim = claimRepository.findById(claimNumber);
        if (maybeClaim.isPresent())
            throw new ClaimNumberAlreadyExistsException("Claim with number " + claimNumber + " already exists");
        final Long contractNumber = claimDTO.getContractNumber();
        final Optional<Contract> maybeContract = contractRepository.findById(contractNumber);
        if (maybeContract.isPresent())
            throw new ContractNumberAlreadyExistsException("Contract with number " + contractNumber + " already exists");

        final Claim claim = claimRepository.save(claimMapper.toEntity(claimDTO));

        return ResponseEntity.created(URI.create(ApplicationConstants.APPLICATION + "/api/claims")).body(claim);
    }

    @PutMapping("/claims")
    public ResponseEntity<Claim> updateClaim(@Valid @RequestBody ClaimDTO claimDTO) {
        log.debug("ClaimDTO object received: {}", claimDTO);
        final Claim claim = claimRepository.save(claimMapper.toEntity(claimDTO));
        return ResponseEntity.ok().body(claim);
    }

    @DeleteMapping("/claims/{claimId}")
    public ResponseEntity<Void> deleteClaim(@NotNull @PathVariable Long claimId) {
        final Optional<Claim> maybeClaim = claimRepository.findById(claimId);
        if (!maybeClaim.isPresent())
            throw new ClaimNotFoundException("Claim with id: " + claimId + " was not found");
        claimRepository.delete(maybeClaim.get());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/claims")
    public List<Claim> getAll() {
        return claimRepository.findAll();
    }

    @GetMapping("/claim/{claimId}")
    public ResponseEntity<Claim> getByNumber(@NotNull @PathVariable Long claimId) {
        final Optional<Claim> maybeClaim = claimRepository.findById(claimId);
        return maybeClaim.map(claim -> ResponseEntity.ok().body(claim)).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
