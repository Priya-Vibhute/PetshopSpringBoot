package com.google.petshop.projections;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface AddressProjection {


	  String getAddressLine1();
	  String getAddressLine2();
	  @JsonIgnore
	  String getCity();
	  @JsonIgnore
	  String getState();
	  @JsonIgnore
	  String getPincode();
	
	  
	  default String getAddress()
	  {
		  return getCity()+" "+getState()+" "+getPincode();
	  }
	  
}
