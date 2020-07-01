package com.simplilearn.flight.flyaway.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Airport {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String iataCode;
	private String name;
	private String countryIsoCode;
	private String address;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Airport() {
		super();
	}
	
	public Airport(int id, String iataCode, String name, String countryIsoCode, String address) {
		super();
		this.id = id;
		this.iataCode = iataCode;
		this.name = name;
		this.countryIsoCode = countryIsoCode;
		this.address = address;
	}

	public String getIataCode() {
		return iataCode;
	}

	public void setIataCode(String iataCode) {
		this.iataCode = iataCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountryIsoCode() {
		return countryIsoCode;
	}

	public void setCountryIsoCode(String countryIsoCode) {
		this.countryIsoCode = countryIsoCode;
	}

}
