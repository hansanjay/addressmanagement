package com.tsd.add.registration.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "address")
public class Address {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String short_name;
	private String line1;
	private String line2;
	private String localityId;
	private String address;
    private String country;
    private String city;
    private String state_name;
    private String geo_tag;
    private String pin_code;
   @Column(name="is_default",nullable = false)
    private boolean defaultAddress;
    private Long journey_id;
    @Column(name="is_verified",nullable = false)
    private boolean verifiedAddress;
    private Long mobile;

}
