package com.nearby.service.impl;

import com.nearby.exception.CategoryNotFoundException;
import com.nearby.model.Category;
import com.nearby.model.dto.CategoryDTO;
import com.nearby.model.dto.CreateCategoryDTO;
import com.nearby.model.dto.Slice;
import com.nearby.repository.CategoryRepository;
import com.nearby.service.CategoryService;
import com.nearby.util.DtoFactory;
import com.nearby.util.EntityFactory;
import com.nearby.util.SliceFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
  private final static int DEFAULT_PAGE_SIZE = 20;

  private final DtoFactory dtoFactory;
  private final EntityFactory entityFactory;
  private final CategoryRepository categoryRepository;

  @Override
  public Slice<CategoryDTO> findAll(int page) {
    return SliceFactory.toSlice(
        categoryRepository.findAll(PageRequest.of(page, DEFAULT_PAGE_SIZE)), dtoFactory::toDto);
  }

  @Override
  public CategoryDTO findById(String categoryId) {
    return dtoFactory.toDto(
        categoryRepository.findById(categoryId).orElseThrow(CategoryNotFoundException::new));
  }

  @Override
  public CategoryDTO create(CreateCategoryDTO createCategoryDTO) {
    return dtoFactory.toDto(categoryRepository.save(entityFactory.toEntity(createCategoryDTO)));
  }

  @Override
  public CategoryDTO update(String id, CreateCategoryDTO createCategoryDTO) {
    categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);

    return dtoFactory.toDto(categoryRepository.save(entityFactory.toEntity(createCategoryDTO)));
  }

  @Override
  public CategoryDTO delete(String id) {
    Category category = categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
    CategoryDTO categoryDTO = dtoFactory.toDto(category);
    categoryRepository.delete(category);

    return categoryDTO;
  }
}
