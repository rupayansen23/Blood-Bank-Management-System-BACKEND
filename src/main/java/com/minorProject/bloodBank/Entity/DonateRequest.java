package com.minorProject.bloodBank.Entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.minorProject.bloodBank.enums.RequestStatus;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class DonateRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reqId;

    @ManyToOne
    @JsonManagedReference
    private Donor donor;

    @ManyToOne
    @JoinColumn()
    @JsonBackReference
    private BloodBank bloodBank;

    private RequestStatus requestStatus = RequestStatus.PENDING;
    private String bloodGroup;
    private int units;
}
