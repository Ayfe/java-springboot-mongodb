package com.up.mongoboot.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.up.mongoboot.model.Organisation;
import com.up.mongoboot.repo.OrganisationRepository;

@Controller
public class OrganisationController {

	@Autowired
	OrganisationRepository repository;

	@GetMapping("/getOrganisationBySiren")
	@ResponseBody
	public Optional<Organisation> getOrganisationBySiren(
			@RequestParam(name = "siren", required = false, defaultValue = "unknown") String siren) {
		return repository.findById(siren);
	}

}
