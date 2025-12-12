package com.minorProject.bloodBank.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class BloodBank {
	@Id
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

	// If Donor/Hospital entities should belong to BloodBank, add mappedBy on those side first.
	// Removed: private List<Donor> donor;
	// Removed: private List<Hospital> hospital;

	@OneToMany(mappedBy = "bloodBank", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<BloodBankBloodGroup> bloodBankBloodGroups = new ArrayList<>();

	@OneToMany(mappedBy = "bloodBank") // matches BloodRequest.bloodBank
	@JsonManagedReference(value = "bloodBank-bloodRequests")
	private List<BloodRequest> requests = new ArrayList<>();

	@OneToMany(mappedBy = "bloodBank", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference(value = "bloodBank-donateRequests")
	private List<DonateRequest> donateRequestList = new ArrayList<>();

	@OneToMany
	List<Donor> donors;

	@OneToMany
	List<Hospital> hospitals;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
