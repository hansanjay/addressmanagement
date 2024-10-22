package com.tsd.add.registration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tsd.add.registration.service.UserAddressService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/v1/tsd/add")
@CrossOrigin
@Tag(name = "API use to fetch the addresses of the user", description = "Retrieve a list of all users' address")
public class UserAddressFetchController {
	@Autowired
	UserAddressService userAddressService;
	
	@GetMapping(path = "/fetch/{mobile}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "API use to fetch the addresses of the user.", description = "Retrieve a list of all users' address")
	public ResponseEntity<?> fetchAllUserAddress(@PathVariable("mobile") String mobile){
		return userAddressService.fetchAllUserAddress(mobile);
	}
	
	@GetMapping(path = "/fetchCustForLocality/{pincode}/{localityId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Fetch all Customers for Locality", description = "Retrieve a list of all Customer for a locality")
	public ResponseEntity<?> getCustomersForLocality(@PathVariable("pincode") String pincode,@PathVariable("localityId") String localityId){
		return userAddressService.fetchCustForLocality(pincode,localityId);
	}
	
	@GetMapping(path = "/fetchAgentForLocality/{pincode}/{localityId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Fetch all Customers for Locality", description = "Retrieve a list of all Customer for a locality")
	public ResponseEntity<?> fetchAgentForLocality(@PathVariable("pincode") String pincode,@PathVariable("localityId") String localityId){
		return userAddressService.fetchAgentsLocality(pincode,localityId);
	}
	
}