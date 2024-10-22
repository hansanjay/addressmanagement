package com.tsd.add.registration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
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
@Tag(name = "User Address Registration API", description = "Operations related to registration of user's Address")
public class UserAddressModifyController {

	@Autowired
	UserAddressService userAddressService;
	
	@PutMapping(path = "/modify/{addressId}",
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "User Address Registration API", description = "Operations related to registration of user's Address")
    public ResponseEntity<?> modifyUserAddress(@PathVariable("addressId") String addressId,@RequestBody AddressReq addressReq) {
        return userAddressService.modifyUserAddress(addressId,addressReq);
    }
	
}