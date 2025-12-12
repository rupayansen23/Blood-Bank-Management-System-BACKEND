package com.minorProject.bloodBank.serviceimpl;
import com.minorProject.bloodBank.Entity.BloodBankBloodGroup;
import com.minorProject.bloodBank.Entity.DonateRequest;
import com.minorProject.bloodBank.Repository.BloodBankBloodGroupRepository;
import com.minorProject.bloodBank.Repository.DonateRequestRepository;
import com.minorProject.bloodBank.dto.ChangeStatusDTO;
import com.minorProject.bloodBank.dto.DonateReqRespUserSideDTO;
import com.minorProject.bloodBank.enums.RequestStatus;
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

    @Autowired
    BloodBankBloodGroupRepository bloodBankBloodGroupRepository;


    @Override
    public ResponseEntity<?> saveDonateRequest(DonateRequest donateRequest) {
        try {
            donateRequest.setRequestStatus(RequestStatus.PENDING);
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
            List<DonateReqRespUserSideDTO> donateRequestResp = new ArrayList<>();
            if(donateRequestList != null) {
                for(DonateRequest donateRequest : donateRequestList) {
                    donateRequestResp.add(donateReqConverter.convertEntitytoDonateReqDTO(donateRequest));
                }
            }
            return ResponseEntity.ok(donateRequestResp);
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

    @Override
    public ResponseEntity<?> patchUpdateStatus(int id, ChangeStatusDTO changeStatusDTO) {
        try {
            DonateRequest donateRequest = donateRequestRepository.findByReqId(id)
                    .orElseThrow(() -> new RuntimeException("Data not found"));
            if(donateRequest != null) {
                donateRequest.setRequestStatus(changeStatusDTO.getStatus());
                donateRequestRepository.save(donateRequest);
                return ResponseEntity.ok(changeStatusDTO);
            }
        }
        catch(RuntimeException re) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot Fulfil the request");
        }
        return null;
    }

    @Override
    public ResponseEntity<?> patchDonateRequestFulfill(int id, ChangeStatusDTO changeStatusDTO) {
        try {
            DonateRequest donateRequest = donateRequestRepository.findByReqId(id)
                    .orElseThrow(()->new RuntimeException("Data not Found"));
            if(donateRequest != null) {
                BloodBankBloodGroup bloodBankBloodGroup = bloodBankBloodGroupRepository.findByBloodBankBloodGroupType(donateRequest.getBloodGroup());
                if (bloodBankBloodGroup == null) {
                    bloodBankBloodGroup = new BloodBankBloodGroup();
                    bloodBankBloodGroup.setBloodBankBloodGroupType(donateRequest.getBloodGroup());
                    bloodBankBloodGroup.setBloodBankBloodAmount(donateRequest.getUnits());
                    bloodBankBloodGroup.setBloodBank(donateRequest.getBloodBank());
                } else {
                    bloodBankBloodGroup.setBloodBankBloodAmount(bloodBankBloodGroup.getBloodBankBloodAmount() + donateRequest.getUnits());
                }
                bloodBankBloodGroupRepository.save(bloodBankBloodGroup);
                donateRequest.setRequestStatus(changeStatusDTO.getStatus());
                donateRequestRepository.save(donateRequest);
                return ResponseEntity.ok(changeStatusDTO);
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot Fulfil your Request");
        }
        return null;
    }
}
