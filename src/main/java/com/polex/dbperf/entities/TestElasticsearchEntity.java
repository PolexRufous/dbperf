package com.polex.dbperf.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Document(indexName = "testentity")
@Accessors(chain = true)
public class TestElasticsearchEntity {

  @Id
  private Long id;
  private String randomText;
  private BigInteger randomNumber;
  private LocalDateTime randomDate;

  public static TestElasticsearchEntity of(BaseTestEntity baseTestEntity) {
    TestElasticsearchEntity result = new TestElasticsearchEntity();
    result.setRandomDate(baseTestEntity.getRandomDate());
    result.setRandomNumber(baseTestEntity.getRandomNumber());
    result.setRandomText(baseTestEntity.getRandomText());
    return result;
  }
}
