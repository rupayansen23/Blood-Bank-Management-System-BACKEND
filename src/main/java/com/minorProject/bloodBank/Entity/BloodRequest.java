package com.minorProject.bloodBank.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import com.minorProject.bloodBank.enums.Priority;
import com.minorProject.bloodBank.enums.RequestStatus;
import com.minorProject.bloodBank.enums.BloodGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name="blood_request")
@Data
public class BloodRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reqId;

    @ManyToOne(optional = false)
    @JoinColumn(name="requester_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Hospital requester;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="blood_bank_id")
    @ToString.Exclude               // ➜ ei line add
    @EqualsAndHashCode.Exclude      // ➜ ei line add
    @JsonBackReference("bloodBank-bloodRequests")
    private BloodBank bloodBank;

    @Enumerated(EnumType.STRING)
    private BloodGroup bloodGroup;

    private int quantity;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Enumerated(EnumType.STRING)
    private RequestStatus status = RequestStatus.PENDING;

}
