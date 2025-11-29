package com.minorProject.bloodBank.service;
import com.minorProject.bloodBank.Entity.DonateRequest;
import org.springframework.http.ResponseEntity;

public interface DonateReqService {
    public ResponseEntity<?> saveDonateRequest(DonateRequest donateRequest);
    public ResponseEntity<?> getDonateReqByBloodBank(int id);
    public ResponseEntity<?> getDonateReqByUserId(int id);
}
