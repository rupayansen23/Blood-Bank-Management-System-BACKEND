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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AdminLoginServiceImpl implements AdminLoginService {

    @Autowired
    AdminLoginRepository adminLoginRepository;

    @Autowired
    AdminConverter adminConverter;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Override
    public AdminDTO saveAdminLoginCredential(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        adminLoginRepository.save(admin);
        AdminDTO adminDTO = adminConverter.convertEntityToAdminDTO(admin);
        return adminDTO;
    }

    @Override
    public AdminDTO getAdminInformation(String userName) {
        try {
            Admin admin = adminLoginRepository.findByUsername(userName);
            if (admin != null) {
                admin.setPassword(null);
                return adminConverter.convertEntityToAdminDTO(admin);
            } else {
                throw new RuntimeException("Cannot found Entity");
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        return  null;
    }

    @Override
    public ResponseEntity<AdminDTO> findAdminLoginCredential(final String userName, final String password) {
        try {
            Admin admin1 = adminLoginRepository.findByUsername(userName);
            if(admin1 != null && passwordEncoder.matches(password, admin1.getPassword())) {
                AdminDTO adminDTO = adminConverter.convertEntityToAdminDTO(admin1);
                return ResponseEntity.ok(adminDTO);
            } else {
                throw new RuntimeException("Invalid Credentials");
            }
        }
        catch(RuntimeException r) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}
