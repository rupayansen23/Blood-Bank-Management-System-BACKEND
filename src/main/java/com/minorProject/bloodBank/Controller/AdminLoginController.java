package com.minorProject.bloodBank.Controller;

import com.minorProject.bloodBank.Entity.Admin;
import com.minorProject.bloodBank.dto.AdminDTO;
import com.minorProject.bloodBank.service.AdminLoginService;
import com.minorProject.bloodBank.utils.AdminConverter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class AdminLoginController {
    @Autowired
    AdminLoginService adminLoginService;
    @Autowired
    AdminConverter adminConverter;
    @PostMapping("/saveAdmin")
    public AdminDTO saveAdminCredential(@Valid @RequestBody AdminDTO adminDTO) {
        final Admin admin = adminConverter.convertAdminDTOtoEntity(adminDTO);
        return adminLoginService.saveAdminLoginCredential(admin);
    }

    @GetMapping("/")
    public String testing() {
        return "Welcome";
    }

    @PostMapping("/adminLogin")
    public ResponseEntity<String> adminLogin(@Valid @RequestBody AdminDTO adminDTO) {
        final Admin admin = adminConverter.convertAdminDTOtoEntity(adminDTO);
        return adminLoginService.findAdminLoginCredential(admin);
    }
}
