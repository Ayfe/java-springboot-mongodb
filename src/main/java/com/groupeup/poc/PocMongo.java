package com.groupeup.poc;


import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PocMongo {

    public static void main(String[] args) {
        SpringApplication.run(PocMongo.class);
    }

    @Bean
    @Qualifier("ERP")
    MongoClient mongoClientERP() {
        // ERP
        return new MongoClient(new MongoClientURI("mongodb://_:V2xdXcjzQICzZSE2VeEYppslivr6Cgjkh6zVTxDDzX4f5hzrzBj51FkzC2As8xw9@stitch.mongodb.com:27020/?authMechanism=PLAIN&authSource=%24external&ssl=true&appName=poccdr-sjnpm:mongodb-atlas:api-key"));
    }

    @Bean
    @Qualifier("User")
    MongoClient mongoClientUser() {
        return new MongoClient(new MongoClientURI("mongodb://_:WNEHxJHl334LF7S2xSou5SlJMHMaxhy2pA0kX3jnVHg7wonJ17lvP9idsGIj557J@stitch.mongodb.com:27020/?authMechanism=PLAIN&authSource=%24external&ssl=true&appName=poccdr-sjnpm:mongodb-atlas:api-key"));
    }


    @Bean
    @Qualifier("Unknown")
    MongoClient mongoClientTest() {
        return new MongoClient(new MongoClientURI("mongodb://_:t2UREzG10541qkt3grwF7eOtsdy3eBYQiIUnKRDJ7bMWTawlBT5m0WcLexVVMDc7@stitch.mongodb.com:27020/?authMechanism=PLAIN&authSource=%24external&ssl=true&appName=poccdr-sjnpm:mongodb-atlas:api-key"));
    }
    
    @Bean
    @Qualifier("PBU")
    MongoClient mongoClientPbu() {
        return new MongoClient(new MongoClientURI("mongodb://_:2iwkKddeFmSrX5mquQfF8QSvQ6sOJecpwjHdXCsooxiE5hwsK7JDzc6Zy4IAoOtR@stitch.mongodb.com:27020/?authMechanism=PLAIN&authSource=%24external&ssl=true&appName=poccdr-sjnpm:mongodb-atlas:api-key"));
    }
}
