package com.minorProject.bloodBank.utils;

import com.minorProject.bloodBank.Entity.BloodBank;
import com.minorProject.bloodBank.dto.AddBloodBankDTO;
import com.minorProject.bloodBank.dto.BloodBankDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;


@Component
public class BloodBankConverter {
    public BloodBank convertBloodBankDTOtoEntity(BloodBankDTO bloodBankDTO) {
        BloodBank bloodBank = new BloodBank();
        if(bloodBankDTO != null) {
            BeanUtils.copyProperties(bloodBankDTO, bloodBank);
        }
        return bloodBank;
    }
    public BloodBankDTO convertEntityToBloodBankDTO(BloodBank bloodBank) {
        BloodBankDTO bloodBankDTO = new BloodBankDTO();
        if(bloodBank != null) {
            BeanUtils.copyProperties(bloodBank, bloodBankDTO);
        }
        return bloodBankDTO;
    }
    public BloodBank convertAddBloodBankDTOtoEntity(AddBloodBankDTO addBloodBankDTO) {
        BloodBank bloodBank = new BloodBank();
        if(addBloodBankDTO != null) {
            bloodBank.setBloodBankName(addBloodBankDTO.getBloodBankName());
            bloodBank.setBloodBankAddress(addBloodBankDTO.getBloodBankAddress());
            bloodBank.setBloodBankContactNumber(addBloodBankDTO.getBloodBankContactNumber());
            bloodBank.setAdminID(addBloodBankDTO.getAdminID());
            bloodBank.setPassword(addBloodBankDTO.getPassword());
        }
        return bloodBank;
    }
}
