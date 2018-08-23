package com.polex.dbperf.services;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Accessors(chain = true)
@Data
public class OperationResponse {

  private long mongoTime;
  private long jpaTime;
  private long elasticTime;

}
