package com.nearby.service;

import com.nearby.model.dto.CategoryDTO;
import com.nearby.model.dto.CreateCategoryDTO;
import com.nearby.model.dto.Slice;

public interface CategoryService {
  Slice<CategoryDTO> findAll(int page);

  CategoryDTO findById(String id);

  CategoryDTO create(CreateCategoryDTO createCategoryDTO);

  CategoryDTO update(String id, CreateCategoryDTO createCategoryDTO);

  CategoryDTO delete(String id);
}
