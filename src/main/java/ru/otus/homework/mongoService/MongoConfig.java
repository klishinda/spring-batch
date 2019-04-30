package ru.otus.homework.mongoService;

import com.github.mongobee.Mongobee;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.homework.mongoService.changelog.Changelog;

@Configuration
public class MongoConfig {

  private final MongoClient mongo;
  private final MongoTemplate mongoTemplate;

  @Autowired
  public MongoConfig(MongoClient mongo, MongoTemplate mongoTemplate) {
    this.mongo = mongo;
    this.mongoTemplate = mongoTemplate;
  }

  @Bean
  public Mongobee mongobee(Environment environment) {
    Mongobee runner = new Mongobee(mongo);
    runner.setDbName("library");
    runner.setChangeLogsScanPackage(Changelog.class.getPackage().getName());
    runner.setSpringEnvironment(environment);
    runner.setMongoTemplate(mongoTemplate);
    return runner;
  }
}