package com.nearby.util;

import com.nearby.model.Category;
import com.nearby.model.Product;
import com.nearby.model.dto.CategoryDTO;
import com.nearby.model.dto.CreateCategoryDTO;
import com.nearby.model.dto.ProductCreateDTO;
import org.springframework.stereotype.Component;

@Component
public class EntityFactory {
  public Category toEntity(CategoryDTO categoryDTO) {
    Category category = new Category();
    category.setId(categoryDTO.id());
    category.setName(category.getName());

    return category;
  }

  public Category toEntity(CreateCategoryDTO createCategoryDTO) {
    Category category = new Category();
    category.setName(createCategoryDTO.name());

    return category;
  }

  public Product toEntity(ProductCreateDTO productCreateDTO, Category category) {
    Product product = new Product();
    product.setName(productCreateDTO.name());
    product.setDescription(productCreateDTO.description());
    product.setViews(0);
    product.setLatitude(productCreateDTO.latitude());
    product.setLongitude(productCreateDTO.longitude());
    product.setPrice(productCreateDTO.price());
    product.setImage(productCreateDTO.image());
    product.setCategory(category);

    return product;
  }
}
