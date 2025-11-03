package com.minorProject.bloodBank.utils;

import com.minorProject.bloodBank.Entity.Hospital;
import com.minorProject.bloodBank.dto.HospitalDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;


@Component
public class HospitalConverter {
    public Hospital convertHospitalDTOtoEntity(HospitalDTO hospitalDTO) {
        Hospital hospital = new Hospital();
        if(hospitalDTO != null)
        {
            BeanUtils.copyProperties(hospitalDTO, hospital);
        }
        return hospital;
    }
    public HospitalDTO convertEntitytoHospitalDTO(Hospital hospital) {
        HospitalDTO hospitalDTO = new HospitalDTO();
        if(hospital != null) {
            BeanUtils.copyProperties(hospital, hospitalDTO);
        }
        return hospitalDTO;
    }
}
