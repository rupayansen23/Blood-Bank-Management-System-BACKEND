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

public class Hospital 
{
	@Id  //primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int hospitalId;
	
	@Column(length=50, nullable=false)
	private String hospitalName;
	
	@Column(length=30, nullable=false)
	private String hospitalBloodGroupType;
	
	@Column(length=30, nullable=false)
	private String hospitalBloodAmount;
	
	@Column(length=50, nullable=false)
	private String hospitalAddress;
	
	@Column(length=10, nullable=false, unique=true)
	private String hospitalContactNumber;
	
	@Column(nullable=false)
	public int totalRecipients;
	
	@ManyToOne  //many hospitals are in contact with one blood bank for the requirement of blood 
	private BloodBank bloodBank;
}