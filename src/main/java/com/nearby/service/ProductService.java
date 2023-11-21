package com.nearby.service;

import com.nearby.model.dto.ProductCreateDTO;
import com.nearby.model.dto.ProductDTO;
import com.nearby.model.dto.ProductRequestQueryDTO;
import com.nearby.model.dto.Slice;

public interface ProductService {
  Slice<ProductDTO> findAll(ProductRequestQueryDTO productRequestQueryDTO, int page, Integer pageSize);

  Slice<ProductDTO> findAll(int page);

  ProductDTO findById(String id);

  Slice<ProductDTO> findAllOrderByClosest(double latitude, double longitude, int page, int pageSize);

  ProductDTO create(ProductCreateDTO productCreateDTO);

  ProductDTO update(String id, ProductCreateDTO productCreateDTO);

  ProductDTO delete(String id);
}
