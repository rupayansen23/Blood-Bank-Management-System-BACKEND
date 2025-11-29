package com.minorProject.bloodBank.utils;

import com.minorProject.bloodBank.Entity.DonateRequest;
import com.minorProject.bloodBank.Entity.Donor;
import com.minorProject.bloodBank.Repository.BloodBankRepository;
import com.minorProject.bloodBank.Repository.DonorRepository;
import com.minorProject.bloodBank.dto.DonateRequestDTO;
import com.minorProject.bloodBank.dto.DonorDTO;
import com.minorProject.bloodBank.enums.RequestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DonateReqConverter {

    @Autowired
    public DonorConverter donorConverter;

    @Autowired
    BloodBankRepository bloodBankRepository;

    @Autowired
    DonorRepository donorRepository;

    public DonateRequest convertDonateReqDTOtoEntity(final DonateRequestDTO donateRequestDTO) {
        DonateRequest donateRequest = new DonateRequest();
        if(donateRequestDTO != null) {
            donateRequest.setReqId(donateRequestDTO.getRequestId());
            DonorDTO donorDTO = donateRequestDTO.getDonorDTO();
            donateRequest.setDonor(donorConverter.convertDonorDTOtoEntity(donorDTO));
            donateRequest.setBloodBank(bloodBankRepository.getReferenceById(donateRequestDTO.getBloodBankId()));
            donateRequest.setUnits(donateRequestDTO.getUnites());
            donateRequest.setRequestStatus(RequestStatus.PENDING);
//            donateRequest.setBloodGroup(donateRequestDTO.getDonorDTO().getDonorBloodGroup());
            String bloodGroup = donateRequestDTO.getDonorDTO().getDonorBloodGroup();
            donateRequest.setBloodGroup(bloodGroup);
            Optional<Donor> donorOptional = donorRepository.findDonorBydonorId(donateRequest.getDonor().getDonorId());
            if(donorOptional.isPresent()) {
                Donor donor = donorOptional.get();
                donor.setDonorAge(donateRequestDTO.getDonorDTO().getDonorAge());
                donor.setDonorBloodGroup(donateRequestDTO.getDonorDTO().getDonorBloodGroup());
                donor.setDonorWeight(donateRequestDTO.getDonorDTO().getDonorWeight());
                donor.setDonorBloodGroup(donateRequestDTO.getDonorDTO().getDonorBloodGroup());
                donor.setDonorAddress(donateRequestDTO.getDonorDTO().getDonorAddress());
                donor.setDonorGender(donateRequestDTO.getDonorDTO().getDonorGender());
                donorRepository.save(donor);
            }
        }
        return donateRequest;
    }
}
