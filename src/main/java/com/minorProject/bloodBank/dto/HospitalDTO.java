package com.minorProject.bloodBank.dto;

import com.minorProject.bloodBank.Entity.BloodBank;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HospitalDTO 
{
	private int hospitalId;
	
	@Size(max=50, message="Max. limit 50 characters")
	@Size(min=2,message="Min. 2 characters required")
	@NotNull(message="Hospital's name is required")
	private String hospitalName;
	
	@Size(max=50, message="Max. limit 30 characters")
	@Size(min=2,message="Min. 2 characters required")
	@NotNull(message="Hospital's blood group is required")
	private String hospitalBloodGroupType;
	
	@Size(max=50, message="Max. limit 30 characters")
	@Size(min=2,message="Min. 2 characters required")
	@NotNull(message="Hospital's amount of blood is required")
	private String hospitalBloodAmount;
	
	@Size(max=50, message="Max. limit 50 characters")
	@Size(min=2,message="Min. 2 characters required")
	@NotNull(message="Hospital's contact number is required")
	private String hospitalAddress;
	
	@Size(max=10, message="Max. limit 10 characters")
	@Size(min=10,message="Min. 10 characters required")
	@NotNull(message="Hospital's contact number is required")
	private String hospitalContactNumber;
	
	@NotNull(message="Total no. of Receiver field is required")
	public int totalRecipients;

	@NotNull(message = "Admin User id is required")
	private String adminID;

	@NotNull(message = "Admin Password is Required")
	private String password;
	
	@ManyToOne  //many hospitals are in contact with one blood bank for the requirement of blood 
	private BloodBank bloodBank;
}