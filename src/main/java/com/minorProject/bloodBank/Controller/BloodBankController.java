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
public class BloodBankController {

    @Autowired
    BloodBankConverter bloodBankConverter;

    @Autowired
    BloodBankService bloodBankService;

    @PostMapping("/saveBloodBank")
    public BloodBankDTO saveBloodBank(@Valid @RequestBody BloodBankDTO bloodBankDTO) {
        final BloodBank bloodBank = bloodBankConverter.convertBloodBankDTOtoEntity(bloodBankDTO);
        BloodBankDTO bloodBankDTO1 =  bloodBankService.saveBloodBank(bloodBank);
        return bloodBankDTO1;
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
    public ResponseEntity<String> bloodBankLogin(@Valid @RequestBody LoginRequest loginRequest) {
        return bloodBankService.bloodBankLogin(loginRequest.getUserName(), loginRequest.getPassword());
    }

}
