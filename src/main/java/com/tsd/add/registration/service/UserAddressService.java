package com.tsd.add.registration.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.tsd.sdk.request.AddressReq;
import org.tsd.sdk.request.CustAgentMappingReq;
import org.tsd.sdk.request.CustomerReq;
import org.tsd.sdk.response.JsonSuccessResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsd.add.registration.entity.Address;
import com.tsd.add.registration.repo.UserAddressRepo;

@Service
public class UserAddressService {

	private static final Logger logger = LoggerFactory.getLogger(UserAddressService.class);

	@Autowired(required = true)
	private UserAddressRepo userAddressRepo;
	
	@Autowired
	private RestTemplate restTemplate;

	public ResponseEntity<?> createUserAddress(String mobile, AddressReq addressReq) {

		logger.info("Request received for mobile " + mobile + " :: and the body is :: " + addressReq.toString());

		Address address = Address.builder()
				.short_name(addressReq.getShort_name())
				.line1(addressReq.getLine1())
				.line2(addressReq.getLine2())
				.localityId(addressReq.getLocalityId())
				.address(addressReq.getAddress())
				.country(addressReq.getCountry())
				.state_name(addressReq.getState_name())
				.city(addressReq.getCity())
				.pin_code(addressReq.getPin_code())
				.defaultAddress(addressReq.is_default())
				.geo_tag(addressReq.getGeo_tag())
				.verifiedAddress(addressReq.isVerifiedAddress())
				.mobile(Long.parseLong(mobile)).build();
		return ResponseEntity
				.ok(JsonSuccessResponse.ok("Success", HttpStatus.OK.value(), userAddressRepo.save(address)));
	}

	public ResponseEntity<?> fetchAllUserAddress(String mobile) {
		List<Address> addresses = userAddressRepo.findByMobile(Long.parseLong(mobile));
		
		return ResponseEntity.ok(JsonSuccessResponse.ok("Success", HttpStatus.OK.value(), addresses));
	}

	public ResponseEntity<?> modifyUserAddress(String addressId, AddressReq addressReq) {

		userAddressRepo.findById(Long.parseLong(addressId)).map(address -> {
			address.setShort_name(addressReq.getShort_name());
			address.setLine1(addressReq.getLine1());
			address.setLine2(addressReq.getLine2());
			address.setLocalityId(addressReq.getLocalityId());
			address.setAddress(addressReq.getAddress());
			address.setCountry(addressReq.getCountry());
			address.setAddress(addressReq.getAddress());
			address.setCity(addressReq.getCity());
			address.setPin_code(addressReq.getPin_code());
			address.setDefaultAddress(addressReq.is_default());
			address.setGeo_tag(addressId);
			address.setVerifiedAddress(addressReq.isVerifiedAddress());
			address.setMobile(addressReq.getMobile());
			userAddressRepo.save(address);
			return ResponseEntity.ok(JsonSuccessResponse.ok("Success", HttpStatus.OK.value(), address));
		});
		return null;
	}

	public ResponseEntity<?> fetchCustForLocality(String pincode,String localityId) {
		ResponseEntity<JsonSuccessResponse> response = null;
		List<Address> addresses = userAddressRepo.findByLocalityIdAndPincode(pincode,localityId);
		String custServiceURL = "http://localhost:8183/api/v1/tsd/cust/fetch/json";
		Iterator<Address> itr = addresses.iterator();
		List<CustAgentMappingReq> addressMappingDTOs = new ArrayList<CustAgentMappingReq>();
		while(itr.hasNext()) {
			CustAgentMappingReq dto = new CustAgentMappingReq();
			Address address = itr.next();
			response = restTemplate.getForEntity(custServiceURL+"/"+address.getMobile()+"/2", JsonSuccessResponse.class);
			System.out.println(response.getBody().data);
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			try {
				CustomerReq customerReq = objectMapper.readValue(response.getBody().data.toString(), CustomerReq.class);
				dto.setFirstName(customerReq.getFirst_name());
				dto.setLastName(customerReq.getLast_name());
				dto.setMobile(customerReq.getMobile());
				dto.setAddress(address.getAddress());
				addressMappingDTOs.add(dto);
				System.out.println(customerReq.getFirst_name());
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		return ResponseEntity.ok(
				JsonSuccessResponse.ok("Success", HttpStatus.OK.value(), addressMappingDTOs));
	}
	
	public ResponseEntity<?> fetchAgentsLocality(String pincode,String localityId) {
		ResponseEntity<JsonSuccessResponse> response = null;
		List<Address> addresses = userAddressRepo.findByLocalityIdAndPincode(pincode,localityId);
		String custServiceURL = "http://localhost:8183/api/v1/tsd/cust/fetch/json";
		Iterator<Address> itr = addresses.iterator();
		List<CustAgentMappingReq> addressMappingDTOs = new ArrayList<CustAgentMappingReq>();
		while(itr.hasNext()) {
			CustAgentMappingReq dto = new CustAgentMappingReq();
			Address address = itr.next();
			response = restTemplate.getForEntity(custServiceURL+"/"+address.getMobile()+"/3", JsonSuccessResponse.class);
			System.out.println(response.getBody().data);
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			try {
				CustomerReq customerReq = objectMapper.readValue(response.getBody().data.toString(), CustomerReq.class);
				dto.setFirstName(customerReq.getFirst_name());
				dto.setLastName(customerReq.getLast_name());
				dto.setMobile(customerReq.getMobile());
				dto.setAddress(address.getAddress());
				addressMappingDTOs.add(dto);
				System.out.println(customerReq.getFirst_name());
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ResponseEntity.ok(
				JsonSuccessResponse.ok("Success", HttpStatus.OK.value(), addressMappingDTOs));
	}

}