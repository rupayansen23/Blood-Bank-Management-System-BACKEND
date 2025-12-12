package com.minorProject.bloodBank.dto;

import com.minorProject.bloodBank.enums.RequestStatus;
import lombok.Data;

@Data
public class DonateReqRespUserSideDTO {
    private int requestId;
    private DonorDTO donorDTO;
    private String bloodBankName;
    private RequestStatus requestStatus;
    private int unites;
}
