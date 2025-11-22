package com.minorProject.bloodBank.Controller;
import com.minorProject.bloodBank.Entity.BloodRequest;
import com.minorProject.bloodBank.dto.CreateBloodRequestDTO;
import com.minorProject.bloodBank.service.BloodRequestService;
import com.minorProject.bloodBank.utils.BloodRequestConverter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BloodRequestController {

    @Autowired
    BloodRequestService bloodRequestService;

    @Autowired
    BloodRequestConverter bloodRequestConverter;

    @PostMapping("/bloodRequest")
    public ResponseEntity<?> bloodRequest(@Valid @RequestBody CreateBloodRequestDTO createBloodRequestDTO) {
        BloodRequest bloodRequest = bloodRequestConverter.convertCreateBloodRequestToEntity(createBloodRequestDTO);
        return bloodRequestService.takeBloodRequest(bloodRequest);
    }

    @GetMapping("/getRequestsById/{id}")
    public ResponseEntity<?> getRequestsById(@Valid @PathVariable int id) {
        return bloodRequestService.showRequestsById(id);
    }
}
