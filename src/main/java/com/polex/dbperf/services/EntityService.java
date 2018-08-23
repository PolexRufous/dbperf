package com.polex.dbperf.services;

import com.github.javafaker.Faker;
import com.polex.dbperf.entities.BaseTestEntity;
import com.polex.dbperf.entities.TestElasticsearchEntity;
import com.polex.dbperf.entities.TestJpaEntity;
import com.polex.dbperf.entities.TestMongoEntity;
import com.polex.dbperf.repositories.elasticsearch.TestEntityElasticRepository;
import com.polex.dbperf.repositories.mongo.TestEntityMongoRepository;
import com.polex.dbperf.repositories.postgres.TestEntityJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Random;

@Service
public class EntityService {

  private final Random random = new Random();
  private final String textTemplate =
      "Hello %s! My name is %s. I like book %s written by %s. I love city %s placed in %s.";
  private final Faker faker = new Faker();
  private final static int MAX_EPOCH_DAYS = 1_095_000;

  private final TestEntityMongoRepository mongo;
  private final TestEntityJpaRepository postgres;
  private final TestEntityElasticRepository elastic;
  @Autowired
  public EntityService(
      TestEntityMongoRepository testEntityMongoRepository,
      TestEntityJpaRepository testEntityJpaRepository,
      TestEntityElasticRepository testEntityElasticRepository
  ) {
    this.mongo = testEntityMongoRepository;
    this.postgres = testEntityJpaRepository;
    this.elastic = testEntityElasticRepository;
  }

  public OperationResponse createRandomEntities(final int count) {
    long mongoTime = 0L;
    long elasticTime = 0L;
    long jpaTime = 0L;

    for (int i = 0; i < count; i++) {
      BaseTestEntity entity = randomEntity();
      long start = System.currentTimeMillis();
      mongo.save(TestMongoEntity.of(entity));
      mongoTime += System.currentTimeMillis() - start;
      start = System.currentTimeMillis();
      postgres.save(TestJpaEntity.of(entity));
      jpaTime += System.currentTimeMillis() - start;
      start = System.currentTimeMillis();
      elastic.save(TestElasticsearchEntity.of(entity));
      elasticTime += System.currentTimeMillis() - start;
    }
    return new OperationResponse()
        .setElasticTime(elasticTime)
        .setJpaTime(jpaTime)
        .setMongoTime(mongoTime);
  }

  private BaseTestEntity randomEntity() {
    return BaseTestEntity.builder()
        .randomNumber(BigInteger.valueOf(random.nextInt()))
        .randomDate(LocalDateTime.of(getRandomDate(), getRandomTime()))
        .randomText(someText())
        .build();
  }

  private LocalDate getRandomDate() {
    return LocalDate.ofEpochDay(random.nextInt() % MAX_EPOCH_DAYS);
  }

  private LocalTime getRandomTime() {
    return LocalTime.ofNanoOfDay(Math.abs(random.nextInt()));
  }

  private String someText() {
    return String.format(textTemplate,
        faker.name().fullName(),
        faker.name().fullName(),
        faker.book().title(),
        faker.book().author(),
        faker.address().cityName(),
        faker.address().country());
  }

  public OperationResponse find(OperationFindRequest operationFindRequest) {
    long mongoTime = 0L;
    long elasticTime = 0L;
    long jpaTime = 0L;
    for (int i = 0; i < operationFindRequest.getSearchCount(); i++) {
      long start = System.currentTimeMillis();
      mongo.findFirstByRandomNumberBetween(operationFindRequest.getNumFrom(), operationFindRequest.getNumTo());
      mongoTime += System.currentTimeMillis() - start;
      start = System.currentTimeMillis();
      postgres.findFirstByRandomNumberBetween(BigInteger.valueOf(operationFindRequest.getNumFrom()), BigInteger.valueOf(operationFindRequest.getNumTo()));
      jpaTime += System.currentTimeMillis() - start;
      start = System.currentTimeMillis();
      elastic.findFirstByRandomNumberBetween(operationFindRequest.getNumFrom(), operationFindRequest.getNumTo());
      elasticTime += System.currentTimeMillis() - start;
    }
    return new OperationResponse()
        .setElasticTime(elasticTime)
        .setJpaTime(jpaTime)
        .setMongoTime(mongoTime);
  }

  public OperationResponse findByNum(OperationFindRequest operationFindRequest) {
    long mongoTime = 0L;
    long elasticTime = 0L;
    long jpaTime = 0L;
    for (int i = 0; i < operationFindRequest.getSearchCount(); i++) {
      long start = System.currentTimeMillis();
      mongo.findFirstByRandomNumber(operationFindRequest.getNumFrom());
      mongoTime += System.currentTimeMillis() - start;
      start = System.currentTimeMillis();
      postgres.findFirstByRandomNumber(BigInteger.valueOf(operationFindRequest.getNumFrom()));
      jpaTime += System.currentTimeMillis() - start;
      start = System.currentTimeMillis();
      elastic.findFirstByRandomNumber(operationFindRequest.getNumFrom());
      elasticTime += System.currentTimeMillis() - start;
    }
    return new OperationResponse()
        .setElasticTime(elasticTime)
        .setJpaTime(jpaTime)
        .setMongoTime(mongoTime);
  }
}
