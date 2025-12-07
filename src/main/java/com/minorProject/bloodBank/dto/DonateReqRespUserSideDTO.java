package com.minorProject.bloodBank.dto;

import lombok.Data;

@Data
public class DonateReqRespUserSideDTO {
    private int requestId;
    private DonorDTO donorDTO;
    private String bloodBankName;
    private int unites;
}
