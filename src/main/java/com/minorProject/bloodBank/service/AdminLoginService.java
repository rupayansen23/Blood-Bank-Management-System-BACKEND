package com.minorProject.bloodBank.service;

import com.minorProject.bloodBank.Entity.Admin;
import com.minorProject.bloodBank.dto.AdminDTO;
import org.springframework.http.ResponseEntity;

public interface AdminLoginService {
    public AdminDTO saveAdminLoginCredential(Admin admin);
    public ResponseEntity<AdminDTO> findAdminLoginCredential(String userName, String password);
    public AdminDTO getAdminInformation(String userName);
}
