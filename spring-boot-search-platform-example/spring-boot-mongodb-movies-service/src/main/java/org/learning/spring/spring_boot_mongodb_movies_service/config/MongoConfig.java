package org.learning.spring.spring_boot_mongodb_movies_service.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.lang.NonNull;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableMongoRepositories(basePackages = "org.learning.spring.spring_boot_mongodb_movies_service.repository")
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Value("${spring.data.mongodb.uri}")
    private String mongoUri;

    @Value("${spring.data.mongodb.database}")
    private String databaseName;

    @Override
    protected String getDatabaseName() {
        return databaseName;
    }

    @Override
    protected void configureClientSettings(MongoClientSettings.Builder builder) {

        if (mongoUri == null || mongoUri.trim().isEmpty() || databaseName == null || databaseName.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Missing mongouri or databasename"
            );
        }


        ConnectionString connectionString = new ConnectionString(mongoUri);


        builder.applyConnectionString(connectionString)
                .applicationName("sample-app-java-mflix")
                .applyToConnectionPoolSettings(poolBuilder ->
                    poolBuilder.maxSize(200)
                           .minSize(20)
                           .maxConnectionIdleTime(5, TimeUnit.MINUTES)
                           .maxWaitTime(5, TimeUnit.SECONDS)
                );
    }


    @Bean
    @NonNull
    public MongoDatabase mongoDatabase() {
        MongoClient client = mongoClient();
        if (client == null) {
            throw new IllegalStateException(
                "MongoClient is not initialized."
            );
        }

        return client.getDatabase(databaseName);
    }

    @Bean
    MongoTransactionManager transactionManager(MongoDatabaseFactory dbFactory) {
        return new MongoTransactionManager(dbFactory);
    }
}
