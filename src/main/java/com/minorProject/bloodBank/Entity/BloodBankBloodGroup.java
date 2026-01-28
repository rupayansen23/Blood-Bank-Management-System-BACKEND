package com.minorProject.bloodBank.Entity;

import com.minorProject.bloodBank.enums.BloodGroup;
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
	
	@Column(length=30)
	@Enumerated(EnumType.STRING)
	private BloodGroup bloodBankBloodGroupType;
	
	@Column(length=30)
	private int bloodBankBloodAmount;
	
	@ManyToOne  //different types of donor blood groups are present in one blood bank
	@JoinColumn(name = "blood_bank_blood_bank_id")
	private BloodBank bloodBank;
}
