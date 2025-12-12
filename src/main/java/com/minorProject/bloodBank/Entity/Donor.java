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
public class Donor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int donorId;

	@Column(length=20, nullable=false)
	private String donorName;

	@Column(nullable=true)
	private int donorAge;

	@Column(length=12, nullable=true)
	private String donorGender;

	@Column(length=12, nullable=true)
	private String donorWeight;

	@Column(length=25, nullable=true)
	private String donorAddress;

	@Column(length=16, nullable=true)
	private String donorBloodGroup;

	@Column(length=10, nullable=false, unique=true)
	private String donorContactNumber;

	@Column(length=30, nullable=false, unique=true)
	private String donorEmailId;

	@Column(length=60, nullable=false, unique=true)
	private String password;

	@OneToMany(mappedBy = "donor", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference(value = "donor-donateRequests")
	private List<DonateRequest> donateRequestList = new ArrayList<>();

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
