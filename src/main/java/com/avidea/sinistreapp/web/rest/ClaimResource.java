package com.avidea.sinistreapp.web.rest;

import com.avidea.sinistreapp.ApplicationConstants;
import com.avidea.sinistreapp.domain.Claim;
import com.avidea.sinistreapp.domain.Contract;
import com.avidea.sinistreapp.repositories.ClaimRepository;
import com.avidea.sinistreapp.repositories.ContractRepository;
import com.avidea.sinistreapp.services.ClaimService;
import com.avidea.sinistreapp.services.dtos.ClaimDTO;
import com.avidea.sinistreapp.services.mapper.ClaimMapper;
import com.avidea.sinistreapp.web.rest.exceptions.ClaimFileDoesNotExistException;
import com.avidea.sinistreapp.web.rest.exceptions.ClaimNotFoundException;
import com.avidea.sinistreapp.web.rest.exceptions.ClaimNumberAlreadyExistsException;
import com.avidea.sinistreapp.web.rest.exceptions.ContractNumberAlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class ClaimResource {

    private static final Logger log = LoggerFactory.getLogger(ClaimResource.class);
    private final ClaimRepository claimRepository;
    private final ContractRepository contractRepository;

    private final ClaimService claimService;
    private final ClaimMapper claimMapper;

    public ClaimResource(ClaimRepository claimRepository, ContractRepository contractRepository, ClaimService claimService, ClaimMapper claimMapper) {
        this.claimRepository = claimRepository;
        this.contractRepository = contractRepository;
        this.claimService = claimService;
        this.claimMapper = claimMapper;
    }


    @PostMapping("/claims")
    public ResponseEntity<Claim> createClaim(@RequestBody @Valid ClaimDTO claimDTO) {

        final Long claimNumber = claimDTO.getClaimNumber();

        log.debug("claim number: {}", claimNumber);

        final Optional<Claim> maybeClaim = claimRepository.findByNumber(claimNumber);
        if (maybeClaim.isPresent())
            throw new ClaimNumberAlreadyExistsException("claim with number " + claimNumber + " already exists");

        final Long contractNumber = claimDTO.getContractNumber();
        final Optional<Contract> maybeContract = contractRepository.findByNumber(contractNumber);
        if (maybeContract.isPresent())
            throw new ContractNumberAlreadyExistsException("contract with number " + contractNumber + " already exists");

        final Claim claim = claimRepository.save(claimMapper.toEntity(claimDTO));

        return ResponseEntity.created(URI.create(ApplicationConstants.APPLICATION + "/api/claims")).body(claim);
    }

    @PutMapping("/claims")
    public ResponseEntity<Claim> updateClaim(@RequestBody @Valid ClaimDTO claimDTO) {
        log.debug("ClaimDTO object received: {}", claimDTO);
        final Long claimId = claimDTO.getClaimId();
        final Optional<Claim> maybeClaim = claimRepository.findById(claimId);

        if (!maybeClaim.isPresent())
            return ResponseEntity.badRequest().build();

        final Claim claim = maybeClaim.get();
        final Long claimNumber = claimDTO.getClaimNumber();
        final Optional<Claim> maybeClaimByNumber = claimRepository.findByNumber(claimNumber);
        if (maybeClaimByNumber.isPresent() && !maybeClaimByNumber.get().equals(claim))
            throw new ClaimNumberAlreadyExistsException("claim with number " + claimNumber + " already exist");

        final Long contractNumber = claimDTO.getContractNumber();
        final Optional<Contract> maybeContract = contractRepository.findByNumber(contractNumber);
        if (maybeContract.isPresent() && !maybeContract.get().equals(claim.getContract()))
            throw new ContractNumberAlreadyExistsException("contract with number " + contractNumber + " already exists");
        claimDTO.setClaimImageUrl(claim.getImageUrl());
        claimRepository.save(claimMapper.toEntity(claimDTO));
        return ResponseEntity.ok().body(claim);
    }

    @DeleteMapping("/claims/{claimId}")
    public ResponseEntity<Void> deleteClaim(@PathVariable Long claimId) {
        final Optional<Claim> maybeClaim = claimRepository.findById(claimId);
        if (!maybeClaim.isPresent())
            throw new ClaimNotFoundException("Claim with id: " + claimId + " was not found");

        claimService.delete(maybeClaim.get());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/claim/{claimId}/image")
    public ResponseEntity<Claim> updateClaimImage(@PathVariable Long claimId, @RequestParam("claimImage") MultipartFile file) {
        final Optional<Claim> maybeClaim = claimRepository.findById(claimId);
        if (!maybeClaim.isPresent())
            throw new ClaimNotFoundException("Claim with number " + claimId + " does not exist");
        final Claim claim = maybeClaim.get();
        if (claim.getImageUrl() == null || claim.getImageUrl().isEmpty())
            throw new ClaimFileDoesNotExistException("No file associated with claim number: " + claim.getNumber());
        try {
            log.debug("claim imageUrl: {}", claim.getImageUrl());
            claimService.updateClaimFile(claim, file);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().body(claim);
    }

    @DeleteMapping("/claim/{claimId}/image")
    public ResponseEntity<Claim> deleteClaimImage(@PathVariable Long claimId) {
        final Optional<Claim> maybeClaim = claimRepository.findById(claimId);
        if (!maybeClaim.isPresent())
            throw new ClaimNotFoundException("Claim with number " + claimId + " does not exist");
        final Claim claim = maybeClaim.get();
        if (claim.getImageUrl() == null || claim.getImageUrl().isEmpty())
            throw new ClaimFileDoesNotExistException("No file associated with claim number: " + claim.getNumber());
        try {
            claimService.deleteClaimFile(claim);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().body(claim);
    }

    @GetMapping("/claims")
    public List<Claim> getAll() {
        return claimRepository.findAll();
    }

    @GetMapping("/claim/{claimId}")
    public ResponseEntity<Claim> getByNumber(@PathVariable Long claimId) {
        final Optional<Claim> maybeClaim = claimRepository.findById(claimId);
        return maybeClaim.map(claim -> ResponseEntity.ok().body(claim)).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
