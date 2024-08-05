package com.google.petshop.repositoy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.google.petshop.entity.Roles;

@RepositoryRestResource(path="roles")
public interface RoleRepository extends JpaRepository<Roles, Integer>{
	

}
