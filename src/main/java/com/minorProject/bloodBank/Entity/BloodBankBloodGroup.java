package com.minorProject.bloodBank.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

public class BloodBankBloodGroup 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bloodBankBloodGroupId;
	
	@Column(length=30, nullable=false)
	private String bloodBankBloodGroupType;
	
	@Column(length=30, nullable=false)
	private String bloodBankBloodAmount;
	
	@ManyToOne  //different types of donor blood groups are present in one blood bank
	private BloodBank bloodBank;
}
