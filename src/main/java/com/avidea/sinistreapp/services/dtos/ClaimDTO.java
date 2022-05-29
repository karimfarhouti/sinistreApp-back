package com.avidea.sinistreapp.services.dtos;

import com.avidea.sinistreapp.domain.enums.ClaimStatus;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class ClaimDTO {

    private Long claimId;
    @NotNull(message = "can't be null")
    private Long claimNumber;

    @NotNull(message = "can't be null")
    private LocalDate claimAccidentDate;

    @NotNull(message = "can't be null")
    private LocalDate claimCreationDate;

    @NotNull(message = "can't be null")
    private ClaimStatus claimStatus;

    @NotBlank(message = "can't be null")
    private String claimImageUrl;

    private Long contractId;
    @NotNull(message = "can't be null")
    private Long contractNumber;

    @NotNull(message = "can't be null")
    private LocalDate contractStartDate;

    @NotNull(message = "can't be null")
    private LocalDate contractEndDate;

    @NotBlank(message = "can't be null")
    @Min(3)
    @Max(50)
    private String contractAssuredName;

    @NotNull(message = "can't be null")
    private Long contractVehicleImmat;

    public Long getClaimId() { return claimId; }

    public void setClaimId(Long claimId) { this.claimId = claimId; }

    public Long getClaimNumber() { return claimNumber; }

    public void setClaimNumber(Long claimNumber) { this.claimNumber = claimNumber; }

    public LocalDate getClaimAccidentDate() { return claimAccidentDate; }

    public void setClaimAccidentDate(LocalDate claimAccidentDate) { this.claimAccidentDate = claimAccidentDate; }

    public LocalDate getClaimCreationDate() { return claimCreationDate; }

    public void setClaimCreationDate(LocalDate claimCreationDate) { this.claimCreationDate = claimCreationDate; }

    public ClaimStatus getClaimStatus() { return claimStatus; }

    public void setClaimStatus(ClaimStatus claimStatus) { this.claimStatus = claimStatus; }

    public String getClaimImageUrl() { return claimImageUrl; }

    public void setClaimImageUrl(String claimImageUrl) { this.claimImageUrl = claimImageUrl; }

    public Long getContractId() { return contractId; }

    public void setContractId(Long contractId) { this.contractId = contractId; }

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

    @Override
    public String toString() {
        return "ClaimDTO{" +
                "claimId=" + claimId +
                ", claimNumber=" + claimNumber +
                ", claimAccidentDate=" + claimAccidentDate +
                ", claimCreationDate=" + claimCreationDate +
                ", claimStatus=" + claimStatus +
                ", contractId=" + contractId +
                ", contractNumber=" + contractNumber +
                ", contractStartDate=" + contractStartDate +
                ", contractEndDate=" + contractEndDate +
                ", contractAssuredName='" + contractAssuredName + '\'' +
                ", contractVehicleImmat=" + contractVehicleImmat +
                '}';
    }
}
