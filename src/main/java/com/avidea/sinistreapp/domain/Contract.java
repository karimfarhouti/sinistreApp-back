package com.avidea.sinistreapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "contract")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "myGenerator")
    @SequenceGenerator(name = "myGenerator", sequenceName = "mySequence")
    private Long id;

    @Column(name = "number", unique = true)
    private Long number;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "assured_name", nullable = false)
    private String assuredName;

    @Column(name = "vehicle_immat", nullable = false)
    private Long vehicleImmat;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "claim_id", referencedColumnName = "id")
    @JsonIgnore
    private Claim claim;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Long getNumber() { return number; }

    public void setNumber(Long number) { this.number = number; }

    public LocalDate getStartDate() { return startDate; }

    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }

    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public String getAssuredName() { return assuredName; }

    public void setAssuredName(String assuredName) { this.assuredName = assuredName; }

    public Long getVehicleImmat() { return vehicleImmat; }

    public void setVehicleImmat(Long vehicleImmat) { this.vehicleImmat = vehicleImmat; }

    public Claim getClaim() { return claim; }

    public void setClaim(Claim claim) { this.claim = claim; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contract contract = (Contract) o;
        return id != null && id.equals(contract.id);
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
