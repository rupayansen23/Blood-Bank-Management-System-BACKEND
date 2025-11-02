package com.minorProject.bloodBank.serviceimpl;

import com.minorProject.bloodBank.Entity.Hospital;
import com.minorProject.bloodBank.Repository.HospitalRepository;
import com.minorProject.bloodBank.dto.HospitalDTO;
import com.minorProject.bloodBank.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;

public class HospitalServiceImpl implements HospitalService {

    @Autowired
    HospitalRepository hospitalRepository

    @Override
    public HospitalDTO saveHospital(Hospital hospital) {

    }
}
