package com.polex.dbperf.services;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class OperationFindRequest {

  private int numFrom;
  private int numTo;
  private int searchCount;
}
