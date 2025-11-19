package com.minorProject.bloodBank.Controller;
import com.minorProject.bloodBank.Entity.BloodBank;
import com.minorProject.bloodBank.dto.BloodBankDTO;
import com.minorProject.bloodBank.dto.LoginRequest;
import com.minorProject.bloodBank.service.BloodBankService;
import com.minorProject.bloodBank.utils.BloodBankConverter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class BloodBankController {

    @Autowired
    BloodBankConverter bloodBankConverter;

    @Autowired
    BloodBankService bloodBankService;

    @PostMapping("/saveBloodBank")
    public ResponseEntity<?> saveBloodBank(@Valid @RequestBody BloodBankDTO bloodBankDTO) {
        final BloodBank bloodBank = bloodBankConverter.convertBloodBankDTOtoEntity(bloodBankDTO);
        return bloodBankService.saveBloodBank(bloodBank);
    }

    @GetMapping("/getBloodBankById/{id}")
    public BloodBankDTO findBloodBankBankById(@PathVariable("id") int id) {
        return bloodBankService.getBloodBankById(id);
    }

    @GetMapping("/getAllBloodBanks")
    public List<BloodBankDTO> findAllBloodBanks() {
        return bloodBankService.getAllBloodBanks();
    }

    @PostMapping("/BloodBankLogin")
    public ResponseEntity<?> bloodBankLogin(@Valid @RequestBody LoginRequest loginRequest) {
        return bloodBankService.bloodBankLogin(loginRequest.getUserName(), loginRequest.getPassword());
    }
}
