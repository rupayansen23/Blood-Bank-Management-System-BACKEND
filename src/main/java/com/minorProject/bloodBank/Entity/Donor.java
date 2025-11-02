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

public class Donor 
{
	@Id  //primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int donorId;
	
	@Column(length=20, nullable=false)
	private String donorName;
	
	@Column(nullable=false)
	private int donorAge;
	
	@Column(length=12, nullable=false)
	private String donorGender;
	
	@Column(length=12, nullable=false)
	private String donorWeight;
	
	@Column(length=25, nullable=false)
	private String donorAddress;
	
	@Column(length=16, nullable=false)
	private String donorBloodGroup;
	
	@Column(length=10, nullable=false, unique=true)
	private String donorContactNumber;
	
	@Column(length=30, nullable=false, unique=true)
	private String donorEmailId;
	
	@ManyToOne  //many donors are allowed to donate blood in one blood bank
	private BloodBank bloodBank; 
}
