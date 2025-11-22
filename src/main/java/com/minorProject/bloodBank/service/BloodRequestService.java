package com.minorProject.bloodBank.service;

import com.minorProject.bloodBank.Entity.BloodRequest;
import com.minorProject.bloodBank.dto.CreateBloodRequestDTO;
import org.springframework.http.ResponseEntity;

public interface BloodRequestService {
    public ResponseEntity<?> takeBloodRequest(BloodRequest bloodRequest);
    public ResponseEntity<?> showRequestsById(int id);
}
