package com.minorProject.bloodBank.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminDTO {

    @Size(max=50, message="Max. limit 15 characters")
    @Size(min=2,message="Min. 5 characters required")
    @NotNull(message="Admin username is required")
    private String userName;

    @Size(max=50, message="Max. limit 15 characters")
    @Size(min=2,message="Min. 5 characters required")
    @NotNull(message="Admin password is required")
    private String password;
}
