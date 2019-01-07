package com.up.mongoboot.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.mongodb.client.result.UpdateResult;
import com.up.mongoboot.model.Organisation;

@Component
public class CustomRepositoryImpl implements CustomRepository {

	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public long updateOrganisation(String address, Double NumOfEmployees) {
		
		Query query = new Query(Criteria.where("address").is(address));
		Update update = new Update();
		update.set("NumOfEmployees", NumOfEmployees);
		
		UpdateResult result = mongoTemplate.updateFirst(query, update, Organisation.class);

		if (result != null)
			return result.getModifiedCount();
		else
			return 0;
	}

}
