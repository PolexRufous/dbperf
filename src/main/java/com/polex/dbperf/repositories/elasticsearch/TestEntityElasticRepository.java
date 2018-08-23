package com.polex.dbperf.repositories.elasticsearch;

import com.polex.dbperf.entities.TestElasticsearchEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TestEntityElasticRepository extends PagingAndSortingRepository<TestElasticsearchEntity, String> {
  TestElasticsearchEntity findFirstByRandomNumberBetween(int from, int to);
  TestElasticsearchEntity findFirstByRandomNumber(int num);
}
