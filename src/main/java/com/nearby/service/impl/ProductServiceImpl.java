package com.nearby.service.impl;

import com.nearby.exception.CategoryNotFoundException;
import com.nearby.exception.ProductNotFoundException;
import com.nearby.model.Category;
import com.nearby.model.Product;
import com.nearby.model.dto.ProductCreateDTO;
import com.nearby.model.dto.ProductDTO;
import com.nearby.model.dto.ProductRequestQueryDTO;
import com.nearby.model.dto.Slice;
import com.nearby.repository.CategoryRepository;
import com.nearby.repository.ProductRepository;
import com.nearby.service.PriceHistoryService;
import com.nearby.service.ProductService;
import com.nearby.util.DtoFactory;
import com.nearby.util.EntityFactory;
import com.nearby.util.SliceFactory;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
  private static final int PRODUCTS_PER_PAGE = 15;

  private final ProductRepository productRepository;
  private final CategoryRepository categoryRepository;
  private final PriceHistoryService priceHistoryService;
  private final DtoFactory dtoFactory;
  private final EntityFactory entityFactory;

  @Transactional(readOnly = true)
  public Slice<ProductDTO> findAll(ProductRequestQueryDTO productRequestQueryDTO, int page) {
    return SliceFactory.toSlice(
        productRepository.findAllFilterBy(
            null,
            productRequestQueryDTO.minPrice(),
            productRequestQueryDTO.maxPrice(),
            productRequestQueryDTO.searchTerm(),
            PageRequest.of(
                page,
                PRODUCTS_PER_PAGE,
                Sort.by(
                    Sort.Direction.fromOptionalString(productRequestQueryDTO.order())
                        .orElse(Sort.Direction.ASC),
                    Optional.ofNullable(productRequestQueryDTO.sortBy()).orElse("name")))),
        dtoFactory::toDto);
  }

  @Override
  public Slice<ProductDTO> findAllOrderByClosest(double latitude, double longitude, int page) {
    return SliceFactory.toSlice(
        productRepository.getClosestProducts(latitude, longitude, PageRequest.of(page, PRODUCTS_PER_PAGE)),
        dtoFactory::toDto);
  }

  @Override
  public Slice<ProductDTO> findAll(int page) {
    return SliceFactory.toSlice(
        productRepository.findAll(PageRequest.of(page, PRODUCTS_PER_PAGE)), dtoFactory::toDto);
  }

  @Override
  public ProductDTO findById(String productId) {
    Product product =
        productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
    product.setViews(product.getViews() + 1);

    return dtoFactory.toDto(productRepository.save(product));
  }

  @Override
  public ProductDTO create(ProductCreateDTO productCreateDTO) {
    Category category = categoryRepository.getReferenceById(productCreateDTO.categoryId());
    Product product = entityFactory.toEntity(productCreateDTO, category);

    ProductDTO createdProductDto = dtoFactory.toDto(productRepository.save(product));
    priceHistoryService.create(createdProductDto.id());

    return createdProductDto;
  }

  @Override
  public ProductDTO update(String id, ProductCreateDTO productCreateDTO) {
    Product product = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
    Category category = categoryRepository.findById(productCreateDTO.categoryId()).orElseThrow(CategoryNotFoundException::new);

    product.setName(productCreateDTO.name());
    product.setDescription(productCreateDTO.description());
    product.setViews(0);
    product.setLatitude(productCreateDTO.latitude());
    product.setLongitude(productCreateDTO.longitude());
    product.setPrice(productCreateDTO.price());
    product.setImage(productCreateDTO.image());
    product.setCategory(category);

    return dtoFactory.toDto(productRepository.save(product));
  }

  @Override
  public ProductDTO delete(String id) {
    Product product = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
    ProductDTO productDTO = dtoFactory.toDto(product);
    productRepository.delete(product);

    return productDTO;
  }
}
