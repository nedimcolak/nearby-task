package com.nearby.util;

import com.nearby.model.dto.Slice;
import java.math.BigInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

import lombok.Builder;
import org.springframework.data.domain.Page;

@Builder
public class SliceFactory {
  private SliceFactory() {}

  public static <T, R> Slice<R> toSlice(final Page<T> source, Function<T, R> transform) {
    return Slice.<R>builder()
        .totalPages(BigInteger.valueOf(source.getTotalPages()))
            .totalElements(source.getTotalElements())
        .pageData(source.stream().map(transform).collect(Collectors.toList()))
        .build();
  }
}
