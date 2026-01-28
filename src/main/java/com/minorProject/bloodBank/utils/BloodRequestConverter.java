package com.minorProject.bloodBank.utils;
import com.minorProject.bloodBank.Entity.BloodRequest;
import com.minorProject.bloodBank.Repository.BloodBankRepository;
import com.minorProject.bloodBank.Repository.HospitalRepository;
import com.minorProject.bloodBank.dto.BloodRequestResponseDTO;
import com.minorProject.bloodBank.dto.CreateBloodRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BloodRequestConverter {

    @Autowired
    BloodBankRepository bloodBankRepository;

    @Autowired
    HospitalRepository hospitalRepository;

    public BloodRequest convertCreateBloodRequestToEntity(CreateBloodRequestDTO bloodRequestDTO) {
        BloodRequest bloodRequest = new BloodRequest();
        if(bloodRequestDTO != null) {
            bloodRequest.setBloodBank(bloodBankRepository.findByBloodBankName(bloodRequestDTO.getRequestTo()));
            bloodRequest.setRequester(hospitalRepository.findByHospitalId(bloodRequestDTO.getRequesterId()));
            bloodRequest.setBloodGroup(bloodRequestDTO.getBloodGroup());
            bloodRequest.setQuantity(bloodRequestDTO.getQuantity());
            bloodRequest.setPriority(bloodRequestDTO.getPriority());
        }
        return bloodRequest;
    }
    public BloodRequestResponseDTO convertEntityToBloodRequestResponseDTO(final BloodRequest bloodRequest) {
        BloodRequestResponseDTO bloodRequestResponseDTO = new BloodRequestResponseDTO();
        if(bloodRequest != null) {
            bloodRequestResponseDTO.setBloodBankId(bloodRequest.getBloodBank().getBloodBankId());
            bloodRequestResponseDTO.setRequesterName(bloodRequest.getRequester().getHospitalName());
            bloodRequestResponseDTO.setRequesterId(bloodRequest.getRequester().getHospitalId());
            bloodRequestResponseDTO.setBloodBankName(bloodRequest.getBloodBank().getBloodBankName());
            bloodRequestResponseDTO.setBloodGroup(bloodRequest.getBloodGroup());
            bloodRequestResponseDTO.setPriority(bloodRequest.getPriority());
            bloodRequestResponseDTO.setQuantity(bloodRequest.getQuantity());
            bloodRequestResponseDTO.setReqId(bloodRequest.getReqId());
            bloodRequestResponseDTO.setStatus(bloodRequest.getStatus());
        }
        return bloodRequestResponseDTO;
    }
}
