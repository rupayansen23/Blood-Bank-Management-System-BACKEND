package com.minorProject.bloodBank.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.minorProject.bloodBank.enums.BloodGroup;
import com.minorProject.bloodBank.enums.RequestStatus;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class DonateRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reqId;

    // Donor is parent in DB sense? Here we treat Donor as parent (One Donor -> Many DonateRequest)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "donor_id")
    @JsonBackReference(value = "donor-donateRequests")
    private Donor donor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blood_bank_id")
    @JsonBackReference(value = "bloodBank-donateRequests")
    private BloodBank bloodBank;

    private RequestStatus requestStatus;

    @Enumerated(EnumType.STRING)
    private BloodGroup bloodGroup;

    private int units;
}
