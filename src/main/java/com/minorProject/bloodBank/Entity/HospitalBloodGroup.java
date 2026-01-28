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
public class HospitalBloodGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int hospitalBloodGroupId;

    @Column(length = 30)
    @Enumerated(EnumType.STRING)
    private BloodGroup hospitalBloodGroupType;   // e.g., A+, O-, AB+

    @Column
    private int hospitalBloodAmount;          // units available

    @ManyToOne   // multiple blood groups exist in one hospital
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;
}
