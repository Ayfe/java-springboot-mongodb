package com.up.mongoboot.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "Organisations")
public class Organisation {

	@Id
	private String siren;
	private String name;
	private String address;
	private Double numOfEmployees;

	public Organisation(String siren, String name, String address, Double numOfEmployees) {
		super();
		this.siren = siren;
		this.name = name;
		this.address = address;
		this.numOfEmployees = numOfEmployees;
	}

}
