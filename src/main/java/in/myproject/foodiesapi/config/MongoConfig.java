package in.myproject.foodiesapi.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PreDestroy;

@Configuration
public class MongoConfig {

    private MongoClient mongoClient;

    @Bean
    public MongoClient mongoClient() {
        // Read URL from application.properties
        String mongoUri = System.getenv("MONGODB_URL");
        if (mongoUri == null || mongoUri.isEmpty()) {
            mongoUri = "mongodb+srv://rajsidd5534:Ballia%40786@foodapp.j6gxwhr.mongodb.net/?retryWrites=true&w=majority";
        }

        mongoClient = MongoClients.create(
                MongoClientSettings.builder()
                        .applyConnectionString(new ConnectionString(mongoUri))
                        .retryWrites(true)
                        .build()
        );
        return mongoClient;
    }

    // Cleanly close MongoClient on shutdown
    @PreDestroy
    public void closeMongoClient() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }
}
