package com.avidea.sinistreapp.services.mapper;

import com.avidea.sinistreapp.domain.Claim;
import com.avidea.sinistreapp.domain.Contract;
import com.avidea.sinistreapp.services.dtos.ClaimDTO;
import org.springframework.stereotype.Service;

@Service
public class ClaimMapper implements EntityMapper<Claim, ClaimDTO> {

    @Override
    public Claim toEntity(ClaimDTO dto) {
        final Claim claim = new Claim();
        claim.setNumber(dto.getClaimNumber());
        claim.setAccidentDate(dto.getClaimAccidentDate());
        claim.setCreationDate(dto.getClaimCreationDate());
        claim.setStatus(dto.getClaimStatus());

        final Contract contract = new Contract();
        contract.setNumber(dto.getContractNumber());
        contract.setAssuredName(dto.getContractAssuredName());
        contract.setEndDate(dto.getContractEndDate());
        contract.setStartDate(dto.getContractStartDate());
        contract.setVehicleImmat(dto.getContractVehicleImmat());

        contract.setClaim(claim);
        claim.setContract(contract);

        return claim;
    }

}
