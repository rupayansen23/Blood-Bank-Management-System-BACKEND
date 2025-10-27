package com.minorProject.bloodBank.serviceimpl;

import com.minorProject.bloodBank.Entity.Admin;
import com.minorProject.bloodBank.Repository.AdminLoginRepository;
import com.minorProject.bloodBank.dto.AdminDTO;
import com.minorProject.bloodBank.service.AdminLoginService;
import com.minorProject.bloodBank.utils.AdminConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AdminLoginServiceImpl implements AdminLoginService {

    @Autowired
    AdminLoginRepository adminLoginRepository;

    @Autowired
    AdminConverter adminConverter;

    @Override
    public AdminDTO saveAdminLoginCredential(Admin admin) {
        adminLoginRepository.save(admin);
        AdminDTO adminDTO = adminConverter.convertEntityToAdminDTO(admin);
        return adminDTO;
    }

    @Override
    public ResponseEntity<String> findAdminLoginCredential(Admin admin) {
        try {
            Admin admin1 = adminLoginRepository.findByUsernameAndPassword(admin.getUserName(), admin.getPassword());
            if(admin1 == null) {
                throw new RuntimeException("Invalid Credentials");
            }
        }
        catch(RuntimeException r) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
        }
        return ResponseEntity.ok("Login Success");
    }
}
