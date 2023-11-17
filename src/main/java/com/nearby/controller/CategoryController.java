package com.nearby.controller;

import com.nearby.model.dto.CategoryDTO;
import com.nearby.model.dto.CreateCategoryDTO;
import com.nearby.model.dto.Slice;
import com.nearby.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@Tag(
    name = "Category",
    description = "Category API providing creating, retrieving, updating and deleting category.")
public class CategoryController {
  private final CategoryService categoryService;

  @GetMapping
  @Operation(summary = "Retrieve categories.", description = "Retrieves paginated categories")
  @ApiResponse(responseCode = "200", description = "Successfully retrieved categories page.")
  @ApiResponse(responseCode = "500", description = "Server error.")
  public Slice<CategoryDTO> getAllCategories(@RequestParam(name = "page", defaultValue = "0") int page) {
    return categoryService.findAll(page);
  }

  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(
      summary = "Create new category.",
      description = "Creates new category in the database.")
  @ApiResponse(responseCode = "200", description = "Successfully created category.")
  @ApiResponse(responseCode = "500", description = "Server error.")
  public CategoryDTO createCategory(@RequestBody @Valid CreateCategoryDTO category) {
    return categoryService.create(category);
  }

  @PutMapping(
      value = "{id}",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(summary = "Update category.", description = "Updates existing category.")
  @ApiResponse(responseCode = "200", description = "Successfully updated category.")
  @ApiResponse(responseCode = "404", description = "Category with provided id does not exist.")
  @ApiResponse(responseCode = "500", description = "Server error.")
  public CategoryDTO updateCategory(
      @PathVariable String id, @RequestBody @Valid CreateCategoryDTO createCategoryDTO) {
    return categoryService.update(id, createCategoryDTO);
  }

  @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(summary = "Delete a category.", description = "Deletes existing category.")
  @ApiResponse(responseCode = "200", description = "Successfully deleted category.")
  @ApiResponse(responseCode = "404", description = "Category with provided id does not exist.")
  @ApiResponse(responseCode = "500", description = "Server error.")
  public CategoryDTO deleteCategory(@PathVariable String id) {
    return categoryService.delete(id);
  }
}
