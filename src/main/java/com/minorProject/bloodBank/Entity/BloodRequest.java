package com.minorProject.bloodBank.Entity;

import jakarta.persistence.*;
import com.minorProject.bloodBank.enums.Priority;
import com.minorProject.bloodBank.enums.RequestStatus;
import com.minorProject.bloodBank.enums.BloodGroup;
import lombok.Data;

@Entity
@Table(name="blood_request")
@Data
public class BloodRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reqId;

    @ManyToOne(optional = false)
    @JoinColumn(name="requester_id")
    private Hospital requester;

    @ManyToOne
    @JoinColumn(name="blood_bank_id")
    private BloodBank bloodBank;

    @Enumerated(EnumType.STRING)
    private BloodGroup bloodGroup;

    private int quantity;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Enumerated(EnumType.STRING)
    private RequestStatus status = RequestStatus.PENDING;

}
