package com.minorProject.bloodBank.dto;

import com.minorProject.bloodBank.enums.BloodGroup;
import com.minorProject.bloodBank.enums.Priority;
import lombok.Data;

@Data
public class CreateBloodRequestDTO {
    private int requesterId;       // Hospital ID
    private BloodGroup bloodGroup;
    private int quantity;
    private Priority priority;
    private String requestTo;
}
