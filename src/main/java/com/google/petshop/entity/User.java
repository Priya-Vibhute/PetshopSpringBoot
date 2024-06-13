package com.google.petshop.entity;




import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "userinfo")// To change table name
public class User {
	@Id// used to mark primary key
	private String id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(unique = true,length = 100)
	private String email;
	
	private String password;
	private int age;
	@OneToOne(orphanRemoval = true,cascade = CascadeType.MERGE)
	
	private Address address;
	

}
