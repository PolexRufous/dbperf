package com.polex.dbperf.repositories.postgres;

import com.polex.dbperf.entities.TestJpaEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.math.BigInteger;

public interface TestEntityJpaRepository extends PagingAndSortingRepository<TestJpaEntity, Long> {
  TestJpaEntity findFirstByRandomNumberBetween(BigInteger from, BigInteger to);
  TestJpaEntity findFirstByRandomNumber(BigInteger num);

}
