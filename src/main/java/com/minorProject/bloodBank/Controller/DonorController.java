package com.minorProject.bloodBank.Controller;


import com.minorProject.bloodBank.Entity.Donor;
import com.minorProject.bloodBank.Repository.DonorRepository;
import com.minorProject.bloodBank.dto.DonorDTO;
import com.minorProject.bloodBank.dto.DonorSignupDTO;
import com.minorProject.bloodBank.dto.LoginRequest;
import com.minorProject.bloodBank.service.DonorService;
import com.minorProject.bloodBank.utils.DonorConverter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class DonorController {

    @Autowired
    DonorConverter donorConverter;

    @Autowired
    DonorService donorService;

    @PostMapping("/signup")
    public DonorDTO donorSignUp(@Valid @RequestBody DonorSignupDTO donorSignupDTO) {
        Donor donor = donorConverter.convertDonorSignUpDTOtoEntity(donorSignupDTO);
        return donorService.donorSignUpService(donor);
    }

    @GetMapping("/getAllDonors")
    public List<DonorDTO> getAllDonorInfo() {
        return donorService.getALLDonors();
    }

    @GetMapping("/donorInfo/{id}")
    public DonorDTO getDonorById(@Valid @PathVariable int id)  {
        return donorService.getDonorInfoById(id);
    }

    @PostMapping("/donorLogin")
    public ResponseEntity<?> donorLogin(@Valid @RequestBody LoginRequest loginRequest) {
        return donorService.donorLogin(loginRequest.getUserName(), loginRequest.getPassword());
    }

}
