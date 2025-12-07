package com.minorProject.bloodBank.dto;

import lombok.Data;

@Data
public class AddBloodBankDTO {
    private String bloodBankName;
    private String bloodBankAddress;
    private String bloodBankContactNumber;
    private String adminID;
    private String password;
}
