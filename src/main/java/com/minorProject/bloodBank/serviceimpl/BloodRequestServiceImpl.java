package com.minorProject.bloodBank.serviceimpl;

import com.minorProject.bloodBank.Entity.BloodRequest;
import com.minorProject.bloodBank.Repository.BloodRequestRepository;
import com.minorProject.bloodBank.dto.BloodRequestResponseDTO;
import com.minorProject.bloodBank.service.BloodRequestService;
import com.minorProject.bloodBank.utils.BloodRequestConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class BloodRequestServiceImpl implements BloodRequestService {

    @Autowired
    BloodRequestConverter bloodRequestConverter;

    @Autowired
    BloodRequestRepository bloodRequestRepository;

    @Override
    public ResponseEntity<?> takeBloodRequest(BloodRequest bloodRequest) {
        try{
            bloodRequestRepository.save(bloodRequest);
            return ResponseEntity.ok(bloodRequestConverter.convertEntityToBloodRequestResponseDTO(bloodRequest));
        }
        catch(RuntimeException e) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot update data to db");
        }
        return null;
    }

    @Override
    public ResponseEntity<?> showRequestsById(int id) {
        try {
            List<BloodRequest> requests = bloodRequestRepository.getBloodRequestById(id);
            if(requests.isEmpty()) {
                return ResponseEntity.ok().body("No Records Found");
            }
            List<BloodRequestResponseDTO> responses = new ArrayList<>();
            for(BloodRequest req : requests) {
                responses.add(bloodRequestConverter.convertEntityToBloodRequestResponseDTO(req));
            }
            return ResponseEntity.ok(responses);
        }
        catch(RuntimeException e) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return null;
    }
}
