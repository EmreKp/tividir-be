package com.emrekp.tividir;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.session.data.mongo.config.annotation.web.http.EnableMongoHttpSession;

@Configuration
@EnableMongoRepositories(basePackageClasses = TividRepository.class)
@EnableMongoHttpSession(maxInactiveIntervalInSeconds = 24 * 60 * 60) // auto logout 1 days after last activity
public class MongoClientConfig extends AbstractMongoClientConfiguration {

  private static final String DB_NAME = "tividir";

  @Value("${MONGODB_URL}")
  private String DB_URL;

  @Override
  protected String getDatabaseName() {
    return DB_NAME;
  }

  @Override
  public MongoClient mongoClient() {
    MongoClientSettings clientSettings = MongoClientSettings.builder()
        .applyConnectionString(new ConnectionString(DB_URL))
        .build();

    return MongoClients.create(clientSettings);
  }

  @Override
  public MongoCustomConversions customConversions() {
    return new MongoCustomConversions(
        List.of(new MongoTimeReadConverter(), new MongoTimeWriteConverter())
    );
  }
}
