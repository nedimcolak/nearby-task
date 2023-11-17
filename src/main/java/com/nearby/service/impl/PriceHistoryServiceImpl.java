package com.nearby.service.impl;

import com.nearby.exception.ProductNotFoundException;
import com.nearby.model.PriceHistory;
import com.nearby.model.Product;
import com.nearby.model.dto.PriceHistoryDTO;
import com.nearby.model.dto.ProductDTO;
import com.nearby.model.dto.Slice;
import com.nearby.repository.PriceHistoryRepository;
import com.nearby.repository.ProductRepository;
import com.nearby.service.PriceHistoryService;
import com.nearby.util.DtoFactory;
import com.nearby.util.SliceFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PriceHistoryServiceImpl implements PriceHistoryService {
  private static final int ITEMS_PER_PAGE = 10;
  private final PriceHistoryRepository priceHistoryRepository;
  private final ProductRepository productRepository;
  private final DtoFactory dtoFactory;

  @Override
  public PriceHistoryDTO create(String productId) {
    Product product = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
    PriceHistory priceHistory = new PriceHistory();
    priceHistory.setPrice(product.getPrice());
    priceHistory.setProduct(product);

    return dtoFactory.toDto(priceHistoryRepository.save(priceHistory));
  }

  @Override
  public Slice<PriceHistoryDTO> findByProductId(String id) {
    return SliceFactory.toSlice(
        priceHistoryRepository.findPriceHistoriesByProductId(id, PageRequest.of(0, ITEMS_PER_PAGE)),
        dtoFactory::toDto);
  }
}
