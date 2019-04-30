package ru.otus.homework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.homework.model.mongo.NewBook;
import ru.otus.homework.model.postgresql.Book;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashSet;

@Configuration
@EnableBatchProcessing
public class BatchConfig extends DefaultBatchConfigurer {

  private final Logger logger = LoggerFactory.getLogger("Log");
  private JobBuilderFactory jobBuilderFactory;
  private StepBuilderFactory stepBuilderFactory;

  @Autowired
  public BatchConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
    this.jobBuilderFactory = jobBuilderFactory;
    this.stepBuilderFactory = stepBuilderFactory;
  }

  @Bean
  public ItemReader<Book> reader(EntityManagerFactory entityManagerFactory) {
    return new JpaPagingItemReaderBuilder<Book>()
            .name("BookReader")
            .entityManagerFactory(entityManagerFactory)
            .queryString("select * from Book b")
            .build();
  }

  @Bean
  public void processor() {
    // доделать
  }

  @Bean
  public ItemWriter<NewBook> writer (MongoTemplate mongoTemplate) {
    return new MongoItemWriterBuilder<NewBook>()
            .collection("library")
            .template(mongoTemplate)
            .build();
  }

  @Bean
  public Job importUserJob(Step step1) {
    return jobBuilderFactory.get("importBookJob")
            .incrementer(new RunIdIncrementer())
            .flow(step1)
            .end()
            .listener(new JobExecutionListener() {
              @Override
              public void beforeJob(JobExecution jobExecution) {
                logger.info("Start job");
              }

              @Override
              public void afterJob(JobExecution jobExecution) {
                logger.info("End job");
              }
            })
            .build();
  }

  @Bean
  public Step step1(ItemReader reader, ItemWriter writer, ItemProcessor itemProcessor) {
    return stepBuilderFactory.get("step1")
            .chunk(1)
            .reader(reader)
            .processor(itemProcessor)
            .writer(writer)
            .listener(new ItemReadListener<Book>() {
              @Override
              public void beforeRead() {
                logger.info("Start read");
              }

              @Override
              public void afterRead(Book book) {
                logger.info("Book " + book.getName());
              }

              @Override
              public void onReadError(Exception e) {
                logger.info("Read error");
              }
            })
            .build();
  }

}
