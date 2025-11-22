package com.minorProject.bloodBank.dto;

import com.minorProject.bloodBank.enums.BloodGroup;
import com.minorProject.bloodBank.enums.Priority;
import com.minorProject.bloodBank.enums.RequestStatus;
import lombok.Data;


@Data
public class BloodRequestResponseDTO {
    private int reqId;
    private int requesterId;
    private String requesterName;

    private Integer bloodBankId;        // may be null
    private String bloodBankName;       // may be null

    private BloodGroup bloodGroup;
    private int quantity;
    private Priority priority;
    private RequestStatus status;
}
