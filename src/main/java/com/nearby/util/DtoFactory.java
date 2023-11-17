package com.nearby.util;

import com.nearby.model.Category;
import com.nearby.model.PriceHistory;
import com.nearby.model.Product;
import com.nearby.model.dto.CategoryDTO;
import com.nearby.model.dto.PriceHistoryDTO;
import com.nearby.model.dto.ProductDTO;
import org.springframework.stereotype.Component;

@Component
public class DtoFactory {
  public CategoryDTO toDto(Category category) {
    return new CategoryDTO(category.getId(), category.getName());
  }

  public ProductDTO toDto(Product product) {
    return new ProductDTO(
        product.getId(),
        product.getName(),
        product.getPrice(),
        product.getViews(),
        product.getCategory().getName(),
        product.getImage());
  }

  public PriceHistoryDTO toDto(PriceHistory priceHistory) {
    return new PriceHistoryDTO(
        priceHistory.getId(), priceHistory.getPrice(), priceHistory.getTimestamp());
  }
}
