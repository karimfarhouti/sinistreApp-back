package com.avidea.sinistreapp.services.dtos;

import com.avidea.sinistreapp.domain.enums.ClaimStatus;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class ClaimDTO {

    @NotNull
    private Long claimNumber;

    @NotNull
    private LocalDate claimAccidentDate;

    @NotNull
    private LocalDate claimCreationDate;

    @NotNull
    private ClaimStatus claimStatus;

    @NotNull
    private Long contractNumber;

    @NotNull
    private LocalDate contractStartDate;

    @NotNull
    private LocalDate contractEndDate;

    @NotNull
    @NotEmpty
    @Min(3)
    @Max(50)
    private String contractAssuredName;

    @NotNull
    @NotEmpty
    private Long contractVehicleImmat;

    public Long getClaimNumber() { return claimNumber; }

    public void setClaimNumber(Long claimNumber) { this.claimNumber = claimNumber; }

    public LocalDate getClaimAccidentDate() { return claimAccidentDate; }

    public void setClaimAccidentDate(LocalDate claimAccidentDate) { this.claimAccidentDate = claimAccidentDate; }

    public LocalDate getClaimCreationDate() { return claimCreationDate; }

    public void setClaimCreationDate(LocalDate claimCreationDate) { this.claimCreationDate = claimCreationDate; }

    public ClaimStatus getClaimStatus() { return claimStatus; }

    public void setClaimStatus(ClaimStatus claimStatus) { this.claimStatus = claimStatus; }

    public Long getContractNumber() { return contractNumber; }

    public void setContractNumber(Long contractNumber) { this.contractNumber = contractNumber; }

    public LocalDate getContractStartDate() { return contractStartDate; }

    public void setContractStartDate(LocalDate contractStartDate) { this.contractStartDate = contractStartDate; }

    public LocalDate getContractEndDate() { return contractEndDate; }

    public void setContractEndDate(LocalDate contractEndDate) { this.contractEndDate = contractEndDate; }

    public String getContractAssuredName() { return contractAssuredName; }

    public void setContractAssuredName(String contractAssuredName) { this.contractAssuredName = contractAssuredName; }

    public Long getContractVehicleImmat() { return contractVehicleImmat; }

    public void setContractVehicleImmat(Long contractVehicleImmat) { this.contractVehicleImmat = contractVehicleImmat; }
}
