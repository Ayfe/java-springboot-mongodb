package com.groupeup.poc.web;

import com.mongodb.MongoClient;
import com.mongodb.client.model.Filters;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AffiliatesController {

    @Autowired
    @Qualifier("ERP")
    MongoClient mongoClientERP;

    @Autowired
    @Qualifier("User")
    MongoClient mongoClientUser;

    @Autowired
    @Qualifier("Unknown")
    MongoClient mongoClientUnknown;


    @RequestMapping(method = RequestMethod.GET, value="/cdr/v1/erp/affiliates/{siret}")
    public AffiliateResponse getAffiliateErp(@PathVariable String siret) {
        List<Document> result = new ArrayList<>();
        mongoClientERP.getDatabase("db-ldl-fr").getCollection("affiliates").find(new Document("corporation.registration_number", siret)).into(result);

        AffiliateResponse resp = new AffiliateResponse();
        resp.response = result;

        return resp;

    }

    @RequestMapping(method = RequestMethod.GET, value="/cdr/v1/user/affiliates/{siret}")
    public AffiliateResponse getAffiliateUser(@PathVariable String siret) {
        List<Document> result = new ArrayList<>();
        mongoClientUser.getDatabase("db-ldl-fr").getCollection("affiliates").find(new Document("corporation.registration_number", siret)).into(result);

        AffiliateResponse resp = new AffiliateResponse();
        resp.response = result;

        return resp;
    }

    @RequestMapping(method = RequestMethod.GET, value="/cdr/v1/unknown/affiliates/{siret}")
    public AffiliateResponse getAffiliateUnknown(@PathVariable String siret) {
        List<Document> result = new ArrayList<>();
        mongoClientUnknown.getDatabase("db-ldl-fr").getCollection("affiliates").find(new Document("corporation.registration_number", siret)).into(result);

        AffiliateResponse resp = new AffiliateResponse();
        resp.response = result;

        return resp;
    }
    
    @RequestMapping(method = RequestMethod.GET, value="/cdr/v1/erp/affiliates")
    public AffiliateResponse getAffiliatesErp() {
        List<Document> result = new ArrayList<>();
        mongoClientERP.getDatabase("db-ldl-fr").getCollection("affiliates").find(Filters.exists("_id")).into(result);

        AffiliateResponse resp = new AffiliateResponse();
        resp.response = result;

        return resp;
    }
    
    @RequestMapping(method = RequestMethod.GET, value="/cdr/v1/user/affiliates")
    public AffiliateResponse getAffiliatesUser() {
        List<Document> result = new ArrayList<>();
        mongoClientUser.getDatabase("db-ldl-fr").getCollection("affiliates").find().into(result);

        AffiliateResponse resp = new AffiliateResponse();
        resp.response = result;

        return resp;
    }
    
    
    @RequestMapping(method = RequestMethod.GET, value="/cdr/v1/unknown/affiliates")
    public AffiliateResponse getAffiliatesUnknown() {
        List<Document> result = new ArrayList<>();
        mongoClientUnknown.getDatabase("db-ldl-fr").getCollection("affiliates").find(Filters.exists("_id")).into(result);

        AffiliateResponse resp = new AffiliateResponse();
        resp.response = result;

        return resp;
    }
    
    
    @RequestMapping(method = RequestMethod.GET, value="/cdr/v1/erp/affiliates/vat/{vat}")
    public AffiliateResponse getAffiliateErpByVat(@PathVariable String vat) {
        List<Document> result = new ArrayList<>();
        mongoClientERP.getDatabase("db-ldl-fr").getCollection("affiliates").find(new Document("corporation.vat_number", vat)).into(result);

        AffiliateResponse resp = new AffiliateResponse();
        resp.response = result;

        return resp;

    }
    
    @RequestMapping(method = RequestMethod.GET, value="/cdr/v1/user/affiliates/vat/{vat}")
    public AffiliateResponse getAffiliateUserByVat(@PathVariable String vat) {
        List<Document> result = new ArrayList<>();
        mongoClientUser.getDatabase("db-ldl-fr").getCollection("affiliates").find(new Document("corporation.vat_number", vat)).into(result);

        AffiliateResponse resp = new AffiliateResponse();
        resp.response = result;

        return resp;

    }

    public static class AffiliateResponse {
        public List<Document> getResponse() {
            return response;
        }

        public void setResponse(List<Document> response) {
            this.response = response;
        }

        List<Document> response;

    }
}
