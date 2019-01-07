package com.up.mongoboot.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.up.mongoboot.model.Organisation;

public interface OrganisationRepository extends MongoRepository<Organisation, String> {
	
	Organisation findFirstByName(String name);
	
    @Query("{address:'?0'}")
    List<Organisation> findOrganisationByAddress(String address);

    @Query("{address : { $regex: ?0 } }")
    List<Organisation> findOrganisationByRegExAddress(String domain);

}