package com.minorProject.bloodBank.serviceimpl;

import com.minorProject.bloodBank.Entity.DonateRequest;
import com.minorProject.bloodBank.Repository.BloodBankRepository;
import com.minorProject.bloodBank.Repository.DonateRequestRepository;
import com.minorProject.bloodBank.dto.DonateReqRespUserSideDTO;
import com.minorProject.bloodBank.dto.DonateRequestDTO;
import com.minorProject.bloodBank.service.DonateReqService;
import com.minorProject.bloodBank.utils.DonateReqConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DonateReqServiceImpl implements DonateReqService {

    @Autowired
    DonateRequestRepository donateRequestRepository;

    @Autowired
    DonateReqConverter donateReqConverter;


    @Override
    public ResponseEntity<?> saveDonateRequest(DonateRequest donateRequest) {
        try {
            donateRequestRepository.save(donateRequest);
            return ResponseEntity.ok("request save success");
        }
        catch (RuntimeException re) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("cannot save request to db");
        }
        return null;
    }

    @Override
    public ResponseEntity<?> getDonateReqByBloodBank(int id) {
        try {
            List<DonateRequest> donateRequestList = donateRequestRepository.findByBloodBank_BloodBankId(id);
            return ResponseEntity.ok(donateRequestList);
        }
        catch (RuntimeException re) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(re.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> getDonateReqByUserId(int id) {
        try {
            List<DonateRequest> donateRequestList = donateRequestRepository.findByDonor_DonorId(id);
            List<DonateReqRespUserSideDTO> donateReqRespUserSideDTOS = new ArrayList<>();
            for(DonateRequest donateRequest : donateRequestList) {
                donateReqRespUserSideDTOS.add(donateReqConverter.convertEntitytoDonateReqDTO(donateRequest));
            }
            return ResponseEntity.ok(donateReqRespUserSideDTOS);
        }
        catch (RuntimeException re) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(re.getMessage());
        }
    }
}
