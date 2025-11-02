package com.minorProject.bloodBank.dto;

import com.bloodBankManagement.entity.BloodBank;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BloodBankBloodGroupDTO 
{
	private int bloodBankBloodGroupId;
	
	@Size(max=5, message="Max. limit 5 characters")
	@Size(min=2,message="Min. 2 characters required")
	@NotNull(message="Blood Bank Blood Group Type is required")
	private String bloodBankBloodGroupType;
	
	@Size(max=5, message="Max. limit 5 characters")
	@Size(min=2,message="Min. 2 characters required")
	@NotNull(message="Blood Bank Blood Quantity is required")
	private String bloodBankBloodAmount;
	
	@ManyToOne  //different types of donor blood groups are present in one blood bank
	private BloodBank bloodBank;
}


