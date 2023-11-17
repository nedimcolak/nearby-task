package com.nearby.model.dto;

import java.math.BigInteger;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Slice<T> {
  private final BigInteger totalPages;
  private final List<T> pageData;
}
