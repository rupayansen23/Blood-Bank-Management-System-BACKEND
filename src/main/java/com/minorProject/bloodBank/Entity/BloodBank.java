package com.minorProject.bloodBank.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

public class BloodBank 
{
	@Id  //primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bloodBankId;
	
	@Column(length=30, nullable=false)
	private String bloodBankName;
	
	@Column(length=50, nullable=false)
	private String bloodBankAddress;
	
	@Column(length=10, nullable=false, unique=true)
	private String bloodBankContactNumber;
	
	@Column(nullable=false)
	private int totalDonors;
	
	@Column(nullable=false)
	private int totalHospitals;
	
	@Column(nullable=false)
	private int totalBloodBankBloodGroups;

	@Column(nullable = false, unique = true)
	private String adminID;

	@Column(nullable = false)
	private String password;
	
	@OneToMany  //one blood bank can allow many donors for donating blood 
	private List<Donor> donor;
	
	@OneToMany  //one blood bank is in contact with many hospitals for the supply of blood 
	private List<Hospital> hospital;
	
	@OneToMany  //one blood bank has different types of blood groups
	private List<BloodBankBloodGroup> bloodBankBloodGroup;

	@OneToMany(mappedBy = "bloodBank")
	private List<BloodRequest> requests;


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}