package com.google.petshop.repositoy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.google.petshop.entity.Address;
import com.google.petshop.projections.AddressProjection;

@RepositoryRestResource(path = "address",excerptProjection = AddressProjection.class)
public interface AddressRepository extends JpaRepository<Address, Integer>{
     
}
