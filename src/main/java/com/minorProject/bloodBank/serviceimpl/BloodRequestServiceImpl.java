package com.minorProject.bloodBank.serviceimpl;

import com.minorProject.bloodBank.Entity.BloodBankBloodGroup;
import com.minorProject.bloodBank.Entity.BloodRequest;
import com.minorProject.bloodBank.Entity.Hospital;
import com.minorProject.bloodBank.Entity.HospitalBloodGroup;
import com.minorProject.bloodBank.Repository.BloodBankBloodGroupRepository;
import com.minorProject.bloodBank.Repository.BloodRequestRepository;
import com.minorProject.bloodBank.Repository.HospitalBloodGroupRepo;
import com.minorProject.bloodBank.Repository.HospitalRepository;
import com.minorProject.bloodBank.dto.BloodRequestResponseDTO;
import com.minorProject.bloodBank.enums.RequestStatus;
import com.minorProject.bloodBank.service.BloodRequestService;
import com.minorProject.bloodBank.utils.BloodRequestConverter;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
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

    @Autowired
    BloodBankBloodGroupRepository bloodBankBloodGroupRepository;

    @Autowired
    HospitalBloodGroupRepo hospitalBloodGroupRepo;

    @Autowired
    HospitalRepository hospitalRepository;


    @Override
    public ResponseEntity<?> takeBloodRequest(BloodRequest bloodRequest) {
        try{
            bloodRequest.setStatus(RequestStatus.PENDING);
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
            List<BloodRequest> requests = bloodRequestRepository.getBloodRequestByBloodBankId(id);
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

    @Override
    public ResponseEntity<?> addStatus(int id) {
        BloodRequest bloodRequest = bloodRequestRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Blood Request not found with Id " + id));
        if(bloodRequest != null) {
            bloodRequest.setStatus(RequestStatus.PENDING);
            bloodRequestRepository.save(bloodRequest);
            return ResponseEntity.ok(bloodRequestConverter.convertEntityToBloodRequestResponseDTO(bloodRequest));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Entry not Found");
    }

    @Override
    public ResponseEntity<?> updateStatusAccepted(int id) {
        BloodRequest bloodRequest = bloodRequestRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Blood Request not found with Id " + id));
        if(bloodRequest != null) {
            bloodRequest.setStatus(RequestStatus.ASSIGNED);
            bloodRequestRepository.save(bloodRequest);
            return ResponseEntity.ok(bloodRequestConverter.convertEntityToBloodRequestResponseDTO(bloodRequest));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Entry not Found");
    }

    @Override
    public ResponseEntity<?> updateStatusRejected(int id) {
        BloodRequest bloodRequest = bloodRequestRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Blood Request not found with Id " + id));
        if(bloodRequest != null) {
            bloodRequest.setStatus(RequestStatus.REJECTED);
            bloodRequestRepository.save(bloodRequest);
            return ResponseEntity.ok(bloodRequestConverter.convertEntityToBloodRequestResponseDTO(bloodRequest));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Entry not Found");
    }

    @Override
    @Transactional
    public ResponseEntity<?> updateStatusFulFiled(int id) {
        BloodRequest bloodRequest = bloodRequestRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Blood Request not found with Id " + id));

        if(bloodRequest != null) {
            BloodBankBloodGroup bloodBankBloodGroup = bloodBankBloodGroupRepository.findByBankAndGroup(
                    bloodRequest.getBloodBank().getBloodBankId(),
                    bloodRequest.getBloodGroup()
            ).orElseThrow(()-> new EntityNotFoundException("No records found"));

            if(bloodBankBloodGroup.getBloodBankBloodAmount() > bloodRequest.getQuantity()) {
                 int amount = bloodBankBloodGroup.getBloodBankBloodAmount();
                 int quantity = bloodRequest.getQuantity();
                 amount = amount - quantity;
                 bloodBankBloodGroup.setBloodBankBloodAmount(amount);

                HospitalBloodGroup hospitalBloodGroup = hospitalBloodGroupRepo.findByHospitalBloodGroupType(bloodRequest.getBloodGroup());
                if(hospitalBloodGroup != null) {
                    int hospitalBloodAmount = hospitalBloodGroup.getHospitalBloodAmount();
                    hospitalBloodAmount += quantity;
                    hospitalBloodGroup.setHospitalBloodAmount(hospitalBloodAmount);
                    bloodRequest.setStatus(RequestStatus.FULFILLED);
                    hospitalBloodGroupRepo.save(hospitalBloodGroup);
                } else {
                    HospitalBloodGroup hospitalBloodGroup1 = new HospitalBloodGroup();
                    hospitalBloodGroup1.setHospitalBloodGroupType(bloodRequest.getBloodGroup());
                    hospitalBloodGroup1.setHospital(bloodRequest.getRequester());
                    hospitalBloodGroup1.setHospitalBloodAmount(quantity);
                    hospitalBloodGroupRepo.save(hospitalBloodGroup1);
                }
            }
            bloodRequest.setStatus(RequestStatus.FULFILLED);
            bloodRequestRepository.save(bloodRequest);
            return ResponseEntity.ok(bloodRequestConverter.convertEntityToBloodRequestResponseDTO(bloodRequest));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Entry not Found");
    }
}
