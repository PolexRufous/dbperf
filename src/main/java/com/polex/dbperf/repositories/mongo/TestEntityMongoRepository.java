package com.polex.dbperf.repositories.mongo;

import com.polex.dbperf.entities.TestMongoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TestEntityMongoRepository extends MongoRepository<TestMongoEntity, String> {
  TestMongoEntity findFirstByRandomNumberBetween(int from, int to);
  TestMongoEntity findFirstByRandomNumber(int num);
}
