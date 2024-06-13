package com.google.petshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
	
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private int id;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String state;
	private int pincode;
	@OneToOne(mappedBy = "address")
	@JsonIgnore
	private User user;
	

	
}
