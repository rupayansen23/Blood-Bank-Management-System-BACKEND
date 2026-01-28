package com.minorProject.bloodBank.Controller;
import com.minorProject.bloodBank.Entity.DonateRequest;
import com.minorProject.bloodBank.dto.ChangeStatusDTO;
import com.minorProject.bloodBank.dto.DonateRequestDTO;
import com.minorProject.bloodBank.service.DonateReqService;
import com.minorProject.bloodBank.utils.DonateReqConverter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class DonateRequestController {

    @Autowired
    DonateReqConverter donateReqConverter;

    @Autowired
    DonateReqService donateReqService;

    @PostMapping("/donateReq")
    public ResponseEntity<?> makeDonateRequest(@Valid @RequestBody DonateRequestDTO donateRequestDTO) {
        DonateRequest donateRequest = donateReqConverter.convertDonateReqDTOtoEntity(donateRequestDTO);
        return donateReqService.saveDonateRequest(donateRequest);
    }

    @GetMapping("/getReqByBloodBankId/{id}")
    public ResponseEntity<?> getDonateReqByBloodBank(@Valid @PathVariable int id) {
        return donateReqService.getDonateReqByBloodBank(id);
    }

    @GetMapping("/getReqByUserId/{id}")
    public ResponseEntity<?> getDonateReqByUserId(@Valid @PathVariable final int id) {
        return donateReqService.getDonateReqByUserId(id);
    }

    @PatchMapping("/donate-request/{id}")
    public ResponseEntity<?> patchChengeStatus(@Valid @PathVariable int id, @RequestBody ChangeStatusDTO changeStatusDTO) {
        return donateReqService.patchUpdateStatus(id, changeStatusDTO);
    }

    @PatchMapping("/fulfil-donate-req/{id}")
    public ResponseEntity<?>  fulfilDonateRequest(@Valid @PathVariable int id, @RequestBody ChangeStatusDTO changeStatusDTO) {
        return donateReqService.patchDonateRequestFulfill(id, changeStatusDTO);
    }



}
