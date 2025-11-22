package com.minorProject.bloodBank.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Getter
@Setter
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

//	@Column(name = "hospital_address", nullable = false)
	@Column(name = "hospital_address", length=50, nullable=true)
	private String hospitalAddress;

	@Column(length=10, nullable=false, unique=true)
	private String hospitalContactNumber;
	
	@Column(nullable=false)
	public int totalRecipients;

	@Column(nullable = false, unique = true)
	private String adminID;

	@Column(nullable = false)
	private String password;
	
	@ManyToOne  //many hospitals are in contact with one blood bank for the requirement of blood 
	private BloodBank bloodBank;

	@OneToMany(mappedBy = "requester")
	@JsonIgnore
	private List<BloodRequest> bloodRequests;

	public String getAdminID() {
		return adminID;
	}

	public void setAdminID(String adminID) {
		this.adminID = adminID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}