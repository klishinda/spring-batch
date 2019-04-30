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
import ru.otus.homework.model.mongo.NewAuthor;
import ru.otus.homework.model.mongo.NewBook;
import ru.otus.homework.model.mongo.NewComment;
import ru.otus.homework.model.mongo.NewGenre;
import ru.otus.homework.model.postgresql.Book;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Set;
import java.util.stream.Collectors;

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

  @Override
  public void setDataSource(DataSource dataSource) {}

  @Bean
  public ItemReader<Book> reader(EntityManagerFactory entityManagerFactory) {
    return new JpaPagingItemReaderBuilder<Book>()
            .name("BookReader")
            .entityManagerFactory(entityManagerFactory)
            .queryString("select b from Book b")
            .build();
  }

  @Bean
  public ItemProcessor processor() {
      return (ItemProcessor<Book, NewBook>) book -> {
          String name = book.getName();
          int pages = book.getPages();
          Set<NewAuthor> newAuthors = book.getAuthors().stream().map(NewAuthor::convertAuthor).collect(Collectors.toSet());
          Set<NewGenre> newGenres = book.getGenres().stream().map(NewGenre::convertGenre).collect(Collectors.toSet());
          Set<NewComment> newComments = book.getComments().stream().map(NewComment::convertComment).collect(Collectors.toSet());

          return new NewBook(name, pages, newAuthors, newGenres, newComments);
      };
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
                logger.info("---------------------->Start job");
              }

              @Override
              public void afterJob(JobExecution jobExecution) {
                logger.info("<----------------------End job");
              }
            })
            .build();
  }

  @Bean
  public Step step1(ItemReader reader, ItemWriter writer, ItemProcessor processor) {
    return stepBuilderFactory.get("step1")
            .chunk(2)
            .reader(reader)
            .processor(processor)
            .writer(writer)
            .listener(new ItemReadListener<Book>() {
              public void beforeRead() {
                logger.info("Start read");
              }
              public void afterRead(Book book) {
                logger.info("Book " + book.getName());
              }
              public void onReadError(Exception e) {
                logger.info("Read error");
              }
            })
            .build();
  }

}
