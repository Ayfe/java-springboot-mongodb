package com.up.mongoboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.up.mongoboot.model.*;
import com.up.mongoboot.repo.*;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	OrganisationRepository repository;

	@Autowired
	CustomRepository crepo;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		deleteAll();
		addSampleData();
		listAll();
		findFirst();
		findByRegex();
	}

	public void deleteAll() {
		System.out.println("Deleting all records..");
		repository.deleteAll();
	}

	public void addSampleData() {
		System.out.println("Adding sample data");
		repository.save(new Organisation("11111", "SOC1", "PARIS", (double) 1000));
		repository.save(new Organisation("22222", "COM2", "PARIS", (double) 2000));
		repository.save(new Organisation("33333", "SOC3", "PARIS", (double) 3000));
		repository.save(new Organisation("44445", "COM4", "PARIS", (double) 4000));

	}

	public void listAll() {
		System.out.println("Listing sample data");
		repository.findAll().forEach(u -> System.out.println(u.toString()));
	}

	public void findFirst() {
		System.out.println("Finding first by Name");
		Organisation u = repository.findFirstByName("SOC");
		System.out.println(u);
	}

	public void findByRegex() {
		System.out.println("Finding by Regex - All with address starting with ^SOC");
		repository.findOrganisationByRegExAddress("SOC^").forEach(u -> System.out.println(u.toString()));
	}
}