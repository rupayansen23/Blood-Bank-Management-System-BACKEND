package com.minorProject.bloodBank.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DonorSignupDTO {
    @Size(max=50, message="Max. limit 20 characters")
    @Size(min=2,message="Min. 10 characters required")
    @NotNull(message="Donor's name is required")
    private String donorName;

    @Email(message = "Invalid email format")
    private String donorEmailId;

    @Size(max=50, message="Max. limit 15 characters")
    @Size(min=2,message="Min. 5 characters required")
    @NotNull(message="Admin password is required")
    private String password;

    @Column(length=10, nullable=false, unique=true)
    private String donorContactNumber;
}
