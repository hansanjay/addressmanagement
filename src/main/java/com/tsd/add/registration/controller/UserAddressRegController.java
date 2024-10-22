package com.tsd.add.registration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tsd.sdk.request.AddressReq;

import com.tsd.add.registration.service.UserAddressService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/v1/tsd/add")
@CrossOrigin
@Tag(name = "User Registration API", description = "Operations related to registration of user")
public class UserAddressRegController {

	@Autowired
	UserAddressService userAddressService;
	
	@PostMapping(path = "/register/{mobile}",
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "User registration", description = "Operations related to registration of user")
    public ResponseEntity<?> createUserAddress(@PathVariable("mobile") String mobile,@RequestBody AddressReq addressReq) {
        return userAddressService.createUserAddress(mobile,addressReq);
    }
	
}