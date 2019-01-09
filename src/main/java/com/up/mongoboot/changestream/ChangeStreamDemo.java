package com.up.mongoboot.changestream;


import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.model.changestream.ChangeStreamDocument;
import com.mongodb.client.model.changestream.FullDocument;
import org.bson.BsonDocument;
import org.bson.Document;

import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

public class ChangeStreamDemo {

    public static void main(String[] args) {

        MongoClient client = new MongoClient(new MongoClientURI("mongodb+srv://db_admin:db_admin@dev-poc-cdr-k8ga5.azure.mongodb.net/test?retryWrites=true"));
        MongoCollection<Document> collection = client.getDatabase("db-ldl-fr").getCollection("bo4C-affiliates");

        MongoCursor<ChangeStreamDocument<Document>> iterator = collection.watch(Arrays.asList(
                new Document("$match", new Document("operationType", new Document("$in", Arrays.asList("update", "insert", "replace"))))
        )).fullDocument(FullDocument.UPDATE_LOOKUP).iterator();


        BsonDocument resumeToken = null;

        MongoCollection<Document> affiliates_manual = client.getDatabase("db-ldl-fr").getCollection("affiliates_manual");

        while (iterator.hasNext()) {
            ChangeStreamDocument<Document> next = iterator.next();
            resumeToken = next.getResumeToken();
            // TODO : reprise au resumeToken en cas d'exception

            Document fullDocument = next.getFullDocument();

            String siret = "000000" + Long.toString((Long) fullDocument.get("Siret"));
            siret = siret.substring(siret.length() - 14);


            Document affiliate = new Document(
                    "sys_info", new Document(
                            "is_idempotence", false
                    ).append("process_id", "ATLAS LDL-CDL")
                    .append("version_app", "v0.1")
                    .append("version_cbl", "v0.95")
                    .append("date_creation", new Date())
                    .append("user_creation", "System")
                    .append("country", "FR")
                    .append("subsidiary", "UP FRANCE COOP")
                    .append("language", "FR")
            ).append("legislative_meal_vouchers_commission",
                    new Document("id", fullDocument.get("CodeCRT"))
                    .append("name", "CRT")
            ).append("corporation", new Document("registration_number", siret)
                    .append("company_name", fullDocument.get("RaisonSociale"))
                    .append("trade_name", fullDocument.get("NomAffilie"))
            );

            affiliates_manual.updateOne(new Document("corporation.registration_number", siret),
                    new Document("$set", affiliate).append("$setOnInsert", new Document("uuid", UUID.randomUUID().toString())),
                    new UpdateOptions().upsert(true));


        }
    }
}