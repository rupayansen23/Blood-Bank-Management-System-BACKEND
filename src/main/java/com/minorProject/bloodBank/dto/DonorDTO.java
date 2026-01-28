package com.minorProject.bloodBank.dto;

import com.minorProject.bloodBank.Entity.BloodBank;
import com.minorProject.bloodBank.enums.BloodGroup;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DonorDTO 
{
	private int donorId;
	
	@Size(max=50, message="Max. limit 20 characters")
	@Size(min=2,message="Min. 10 characters required")
	@NotNull(message="Donor's name is required")
	private String donorName;
	
//	@NotNull(message="Donor's age is required")
	private int donorAge;
	
	@Size(max=10, message="Max. limit 12 characters")
	@Size(min=3,message="Min. 6 characters required")
//	@NotNull(message="Donor's gender is required")
	private String donorGender;
	
//	@NotNull(message="Donor's weight is required")
	private String donorWeight;
	
	@Size(max=50, message="Max. limit 25 characters")
	@Size(min=2,message="Min. 10 characters required")
//	@NotNull(message="Donor's address is required")
	private String donorAddress;

	@Enumerated(EnumType.STRING)
	//@NotNull(message = "Donor blood group is required")
	private BloodGroup donorBloodGroup;


	@Size(max=10, message="Max. limit 10 characters")
	@Size(min=10,message="Min. 10 characters required")
	@NotNull(message="Donor's contact number is required")
	private String donorContactNumber;
	
	@Email(message = "Invalid email format")
	private String donorEmailId;

	@Size(max=50, message="Max. limit 15 characters")
	@Size(min=2,message="Min. 5 characters required")
	@NotNull(message="Donor password is required")
	private String password;
	
	@ManyToOne  //many donors are allowed to donate blood in one blood bank
	private BloodBank bloodBank; 
}