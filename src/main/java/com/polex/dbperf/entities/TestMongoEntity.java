package com.polex.dbperf.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@Document(collection = "testentity")
@Accessors(chain = true)
public class TestMongoEntity {

  @Id
  private String id;
  private String randomText;
  private BigInteger randomNumber;
  private LocalDateTime randomDate;

  public static TestMongoEntity of(BaseTestEntity baseTestEntity) {
    TestMongoEntity result = new TestMongoEntity();
    result.setRandomDate(baseTestEntity.getRandomDate());
    result.setRandomNumber(baseTestEntity.getRandomNumber());
    result.setRandomText(baseTestEntity.getRandomText());
    return result;
  }

}
