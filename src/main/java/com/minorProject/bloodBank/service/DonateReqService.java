package com.minorProject.bloodBank.service;
import com.minorProject.bloodBank.Entity.DonateRequest;
import com.minorProject.bloodBank.dto.ChangeStatusDTO;
import org.springframework.http.ResponseEntity;

public interface DonateReqService {
    public ResponseEntity<?> saveDonateRequest(DonateRequest donateRequest);
    public ResponseEntity<?> getDonateReqByBloodBank(int id);
    public ResponseEntity<?> getDonateReqByUserId(int id);
    public ResponseEntity<?> patchUpdateStatus(int id, ChangeStatusDTO changeStatusDTO);
    public ResponseEntity<?> patchDonateRequestFulfill(int id, ChangeStatusDTO changeStatusDTO);
}
