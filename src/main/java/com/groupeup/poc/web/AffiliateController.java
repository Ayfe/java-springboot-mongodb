package com.groupeup.poc.web;

import com.mongodb.MongoClient;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AffiliateController {

    @Autowired
    @Qualifier("ERP")
    MongoClient mongoClientERP;

    @Autowired
    @Qualifier("User")
    MongoClient mongoClientUser;

    @Autowired
    @Qualifier("Unknown")
    MongoClient mongoClientUnknown;


    @RequestMapping(method = RequestMethod.GET, value="/erp/affiliate/{siret}")
    public AffiliateResponse getAffiliateErp(@PathVariable String siret) {
        List<Document> result = new ArrayList<>();
        mongoClientERP.getDatabase("db-ldl-fr").getCollection("affiliates").find(new Document("corporation.registration_number", siret)).into(result);

        AffiliateResponse resp = new AffiliateResponse();
        resp.response = result;

        return resp;

    }

    @RequestMapping(method = RequestMethod.GET, value="/user/affiliate/{siret}")
    public AffiliateResponse getAffiliateUser(@PathVariable String siret) {
        List<Document> result = new ArrayList<>();
        mongoClientUser.getDatabase("db-ldl-fr").getCollection("affiliates").find(new Document("corporation.registration_number", siret)).into(result);

        AffiliateResponse resp = new AffiliateResponse();
        resp.response = result;

        return resp;
    }

    @RequestMapping(method = RequestMethod.GET, value="/unknown/affiliate/{siret}")
    public AffiliateResponse getAffiliateUnknown(@PathVariable String siret) {
        List<Document> result = new ArrayList<>();
        mongoClientUnknown.getDatabase("db-ldl-fr").getCollection("affiliates").find(new Document("corporation.registration_number", siret)).into(result);

        AffiliateResponse resp = new AffiliateResponse();
        resp.response = result;

        return resp;
    }
    
    @RequestMapping(method = RequestMethod.GET, value="/erp/getAffiliates")
    public AffiliateResponse getAffiliatesErp() {
        List<Document> result = new ArrayList<>();
        mongoClientERP.getDatabase("db-ldl-fr").getCollection("affiliates").find().into(result);

        AffiliateResponse resp = new AffiliateResponse();
        resp.response = result;

        return resp;
    }
    
    @RequestMapping(method = RequestMethod.GET, value="/user/getAffiliates")
    public AffiliateResponse getAffiliatesUser() {
        List<Document> result = new ArrayList<>();
        mongoClientUser.getDatabase("db-ldl-fr").getCollection("affiliates").find().into(result);

        AffiliateResponse resp = new AffiliateResponse();
        resp.response = result;

        return resp;
    }
    
    
    @RequestMapping(method = RequestMethod.GET, value="/unknown/getAffiliates")
    public AffiliateResponse getAffiliatesUnknown() {
        List<Document> result = new ArrayList<>();
        mongoClientUnknown.getDatabase("db-ldl-fr").getCollection("affiliates").find().into(result);

        AffiliateResponse resp = new AffiliateResponse();
        resp.response = result;

        return resp;
    }
    
    
    @RequestMapping(method = RequestMethod.GET, value="/erp/affiliateByVat/{vat}")
    public AffiliateResponse getAffiliateErpByVat(@PathVariable String vat) {
        List<Document> result = new ArrayList<>();
        mongoClientERP.getDatabase("db-ldl-fr").getCollection("affiliates").find(new Document("corporation.vat_number", vat)).into(result);

        AffiliateResponse resp = new AffiliateResponse();
        resp.response = result;

        return resp;

    }
    
    @RequestMapping(method = RequestMethod.GET, value="/user/affiliateByVat/{vat}")
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
