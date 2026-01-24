package com.minorProject.bloodBank.dto;

import com.minorProject.bloodBank.enums.RequestStatus;
import lombok.Data;

@Data
public class ChangeStatusDTO {
    private Integer bloodBankId;
    private RequestStatus status;
}
