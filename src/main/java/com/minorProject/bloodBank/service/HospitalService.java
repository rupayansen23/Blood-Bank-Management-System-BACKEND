package com.minorProject.bloodBank.service;

import com.minorProject.bloodBank.Entity.Hospital;
import com.minorProject.bloodBank.dto.HospitalDTO;

public interface HospitalService {
    public HospitalDTO saveHospital(Hospital hospital);
}
