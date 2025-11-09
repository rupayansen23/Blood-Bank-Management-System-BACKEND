package com.minorProject.bloodBank.Controller;
import com.minorProject.bloodBank.Entity.Hospital;
import com.minorProject.bloodBank.dto.HospitalDTO;
import com.minorProject.bloodBank.dto.LoginRequest;
import com.minorProject.bloodBank.service.HospitalService;
import com.minorProject.bloodBank.utils.HospitalConverter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class HospitalController {

    @Autowired
    HospitalConverter hospitalConverter;

    @Autowired
    HospitalService hospitalService;

    @PostMapping("/saveHospital")
    public HospitalDTO saveHospital(@Valid @RequestBody HospitalDTO hospitalDTO) {
        Hospital hospital =  hospitalConverter.convertHospitalDTOtoEntity(hospitalDTO);
        return hospitalService.saveHospital(hospital);
    }

    @PostMapping("/hospitalLogin")
    public ResponseEntity<String> hospitalLogin(@Valid @RequestBody LoginRequest loginRequest) {
        return hospitalService.hospitalLogin(loginRequest.getUserName(), loginRequest.getPassword());
    }

    @GetMapping("/getAllHospitals")
    public List<HospitalDTO> getAllHospitals() {
        return hospitalService.getAllHospitals();
    }
}
