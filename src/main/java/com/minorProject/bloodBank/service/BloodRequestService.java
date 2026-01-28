package com.minorProject.bloodBank.service;

import com.minorProject.bloodBank.Entity.BloodRequest;
import com.minorProject.bloodBank.dto.BloodRequestResponseDTO;
import com.minorProject.bloodBank.dto.CreateBloodRequestDTO;
import org.springframework.http.ResponseEntity;

public interface BloodRequestService {
    public ResponseEntity<?> takeBloodRequest(BloodRequest bloodRequest);
    public ResponseEntity<?> showRequestsById(int id);
    ResponseEntity<?> addStatus(int id);
    ResponseEntity<?> updateStatusAccepted(int id);
    ResponseEntity<?> updateStatusRejected(int id);
    ResponseEntity<?> updateStatusFulFiled(int id);
}
