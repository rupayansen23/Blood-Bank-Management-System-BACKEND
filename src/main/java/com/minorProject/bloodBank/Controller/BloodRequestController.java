package com.minorProject.bloodBank.Controller;
import com.minorProject.bloodBank.Entity.BloodRequest;
import com.minorProject.bloodBank.dto.BloodRequestResponseDTO;
import com.minorProject.bloodBank.dto.CreateBloodRequestDTO;
import com.minorProject.bloodBank.service.BloodRequestService;
import com.minorProject.bloodBank.utils.BloodRequestConverter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
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

    @PatchMapping("/bug-patch/{id}")
    public ResponseEntity<?> patchBloodRequests(@PathVariable int id) {
        return bloodRequestService.addStatus(id);
    }

    @PatchMapping("/update-accepted/{id}")
    public ResponseEntity<?> updateStatusToAccepted(@PathVariable int id) {
        return bloodRequestService.updateStatusAccepted(id);
    }

    @PatchMapping("/update-rejected/{id}")
    public ResponseEntity<?> updateStatusToRejected(@PathVariable int id) {
        return bloodRequestService.updateStatusRejected(id);
    }

    @PatchMapping("/update-fulfil/{id}")
    public ResponseEntity<?> updateStatusToFulfil(@PathVariable int id) {
        return bloodRequestService.updateStatusFulFiled(id);
    }
}
