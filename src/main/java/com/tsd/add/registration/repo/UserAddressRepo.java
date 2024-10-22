package com.tsd.add.registration.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tsd.add.registration.entity.Address;


@Repository
public interface UserAddressRepo extends JpaRepository<Address, Long>{

	List<Address> findByMobile(Long mobile);
	
	List<Address> findByLocalityId(String localityId);
	
	@Query("SELECT a.localityId, a.pin_code FROM Address a where a.pin_code = :pincode and a.localityId = :localityId")
	List<Address> findByLocalityIdAndPincode(String pincode,String localityId);
		
}
