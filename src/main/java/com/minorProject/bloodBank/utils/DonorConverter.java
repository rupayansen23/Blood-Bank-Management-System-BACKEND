package com.minorProject.bloodBank.utils;
import com.minorProject.bloodBank.Entity.Donor;
import com.minorProject.bloodBank.dto.DonorDTO;
import com.minorProject.bloodBank.dto.DonorSignupDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class DonorConverter {
    public Donor convertDonorSignUpDTOtoEntity(DonorSignupDTO donorSignupDTO) {
        Donor donor = new Donor();
        if(donorSignupDTO != null) {
            BeanUtils.copyProperties(donorSignupDTO, donor);
        }
        return donor;
    }
    public DonorDTO convertEntityToDonorDTO(Donor donor) {
        DonorDTO donorDTO = new DonorDTO();
        if(donor != null) {
            BeanUtils.copyProperties(donor, donorDTO);
        }
        return donorDTO;
    }
    public Donor convertDonorDTOtoEntity(DonorDTO donorDTO) {
        Donor donor = new Donor();
        if(donorDTO != null) {
            BeanUtils.copyProperties(donorDTO, donor);
        }
        return donor;
    }
}
