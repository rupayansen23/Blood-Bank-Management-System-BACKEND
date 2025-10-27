package com.minorProject.bloodBank.utils;

import com.minorProject.bloodBank.Entity.Admin;
import com.minorProject.bloodBank.dto.AdminDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class AdminConverter {
    public AdminDTO convertEntityToAdminDTO(Admin admin)
    {
        AdminDTO adminDTO = new AdminDTO();
        if(admin != null)
        {
            BeanUtils.copyProperties(admin, adminDTO);
        }
        return adminDTO;
    }
    public Admin convertAdminDTOtoEntity(AdminDTO adminDTO) {
        Admin admin = new Admin();
        if(adminDTO != null) {
            BeanUtils.copyProperties(adminDTO, admin);
        }
        return admin;
    }
}
