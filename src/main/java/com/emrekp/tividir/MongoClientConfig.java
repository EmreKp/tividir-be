package com.emrekp.tividir;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackageClasses = TividRepository.class)
public class MongoClientConfig extends AbstractMongoClientConfiguration {

  private static final String DB_NAME = "tividir";
  private static final String DB_URL = "mongodb+srv://tividir-master:pE2qhNXAHVfE6d7@tividir-stag.6ln70ia.mongodb.net/?retryWrites=true&w=majority";

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
  protected Collection<String> getMappingBasePackages() {
    return Collections.singleton("com.emrekp.tividir");
  }

  @Override
  public MongoCustomConversions customConversions() {
    List<Converter<?, ?>> converters = List.of(
        new MongoTimeReadConverter(),
        new MongoTimeWriteConverter()
    );

    return new MongoCustomConversions(converters);
  }
}
