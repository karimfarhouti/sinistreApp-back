package com.avidea.sinistreapp.domain;

import com.avidea.sinistreapp.domain.enums.ClaimStatus;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "claim")
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "myGenerator")
    @SequenceGenerator(name = "myGenerator", sequenceName = "mySequence")
    private Long id;

    @Column(name = "number",unique = true)
    private Long number;

    @Column(name = "accident_date", nullable = false)
    private LocalDate accidentDate;

    @Column(name = "creation_date", nullable = false)
    private LocalDate creationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ClaimStatus status;

    @Column(name = "image_url")
    private String imageUrl;

    @OneToOne(mappedBy = "claim"
            , cascade = CascadeType.ALL)
    private Contract contract;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Long getNumber() { return number; }

    public void setNumber(Long number) { this.number = number; }

    public LocalDate getAccidentDate() { return accidentDate; }

    public void setAccidentDate(LocalDate accidentDate) { this.accidentDate = accidentDate; }

    public LocalDate getCreationDate() { return creationDate; }

    public void setCreationDate(LocalDate creationDate) { this.creationDate = creationDate; }

    public ClaimStatus getStatus() { return status; }

    public void setStatus(ClaimStatus status) { this.status = status; }

    public String getImageUrl() { return imageUrl; }

    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public Contract getContract() { return contract; }

    public void setContract(Contract contract) { this.contract = contract; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Claim claim = (Claim) o;
        return id != null && id.equals(claim.id);
    }

    @Override
    public int hashCode() { return 31; }
}
