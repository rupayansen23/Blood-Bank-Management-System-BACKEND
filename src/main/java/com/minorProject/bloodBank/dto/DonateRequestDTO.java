package com.minorProject.bloodBank.dto;


import lombok.Data;

@Data
public class DonateRequestDTO {
    private int requestId;
    private DonorDTO donorDTO;
    private int bloodBankId;
    private int unites;
}
