package com.nearby.service;

import com.nearby.model.Product;
import com.nearby.model.dto.PriceHistoryDTO;
import com.nearby.model.dto.Slice;

public interface PriceHistoryService {
  PriceHistoryDTO create(String productId);

  Slice<PriceHistoryDTO> findByProductId(String productId);
}
