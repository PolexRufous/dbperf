package com.polex.dbperf.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Table(name = "TEST_ENTITY", indexes = {
    @Index(columnList = "id", name = "user_id_hidx"),
    @Index(columnList = "randomNumber", name = "randomNumber_hidx")})

@Getter
@Setter
@NoArgsConstructor
public class TestJpaEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String randomText;
  private BigInteger randomNumber;
  private LocalDateTime randomDate;

  public static TestJpaEntity of(BaseTestEntity baseTestEntity) {
    TestJpaEntity result = new TestJpaEntity();
    result.setRandomDate(baseTestEntity.getRandomDate());
    result.setRandomNumber(baseTestEntity.getRandomNumber());
    result.setRandomText(baseTestEntity.getRandomText());
    return result;
  }
}
