package com.minorProject.bloodBank.Controller;

import com.minorProject.bloodBank.Entity.Hospital;
import com.minorProject.bloodBank.dto.HospitalDTO;
import com.minorProject.bloodBank.service.HospitalService;
import com.minorProject.bloodBank.utils.HospitalConverter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HospitalController {

    @Autowired
    HospitalConverter hospitalConverter;

    @Autowired
    HospitalService hospitalService;

    @PostMapping("/saveHospital")
    public HospitalDTO saveHospital(@Valid @RequestBody HospitalDTO hospitalDTO) {
        final Hospital hospital = hospitalConverter.convertHospitalDTOtoEntity(hospitalDTO);
        return hospitalService.saveHospital(hospital);
    }
}
