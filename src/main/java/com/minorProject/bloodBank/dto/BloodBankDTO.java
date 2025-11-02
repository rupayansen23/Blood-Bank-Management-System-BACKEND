package com.minorProject.bloodBank.dto;

import com.minorProject.bloodBank.Entity.Hospital;
import com.minorProject.bloodBank.Entity.Donor;
import com.minorProject.bloodBank.Entity.BloodBankBloodGroup;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BloodBankDTO 
{
	private int bloodBankId;
	
	@Size(max=50, message="Max. limit 50 characters")
	@Size(min=2,message="Min. 2 characters required")
	@NotNull(message="Blood Bank's name is required")
	private String bloodBankName;
	
	@Size(max=50, message="Max. limit 50 characters")
	@Size(min=2,message="Min. 2 characters required")
	@NotNull(message="Blood Bank's address is required")
	private String bloodBankAddress;
	
	@Size(max=10, message="Max. limit 10 characters")
	@Size(min=10,message="Min. 10 characters required")
	@NotNull(message="Blood Bank's contact number is required")
	private String bloodBankContactNumber;
	
	@NotNull(message="Total no. of Donor field is required")
	private int totalDonors;
	
	@NotNull(message="Total no. of Hospital field is required")
	private int totalHospitals;
	
	@NotNull(message="Total no. of Blood Bank Blood Group field is required")
	private int totalBloodBankBloodGroups;
	
	@OneToMany  //one blood bank can allow many donors for donating blood 
	private List<Donor> donor;
	
	@OneToMany  //one blood bank is in contact with many hospitals for the supply of blood 
	private List<Hospital> hospital;
	
	@OneToMany  //one blood bank has different types of blood groups
	private List<BloodBankBloodGroup> bloodBankBloodGroup;
}