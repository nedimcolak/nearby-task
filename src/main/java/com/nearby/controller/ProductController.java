package com.nearby.controller;

import com.nearby.model.dto.ProductCreateDTO;
import com.nearby.model.dto.ProductDTO;
import com.nearby.model.dto.ProductRequestQueryDTO;
import com.nearby.model.dto.Slice;
import com.nearby.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@Tag(name = "Products", description = "API for managing products")
@RequiredArgsConstructor
public class ProductController {
  private final ProductService productService;

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(
      summary = "Retrieve all products",
      description = "Retrieves all products, paginated. Result can be sorted & filtered.")
  @ApiResponse(responseCode = "200", description = "Returned products page successfully!")
  @ApiResponse(responseCode = "500", description = "Server error.")
  public Slice<ProductDTO> findAll(
      @RequestParam(name = "page", required = false, defaultValue = "0") int page,
      @RequestParam(name = "sortBy", required = false) String sortBy,
      @RequestParam(name = "order", required = false, defaultValue = "ASC") String order,
      @RequestParam(name = "minPrice", required = false) Double minPrice,
      @RequestParam(name = "maxPrice", required = false) Double maxPrice,
      @RequestParam(name = "searchTerm", required = false, defaultValue = "") String searchTerm) {
    ProductRequestQueryDTO productRequestQueryDTO =
        new ProductRequestQueryDTO(minPrice, maxPrice, searchTerm, sortBy, order);
    return productService.findAll(productRequestQueryDTO, page);
  }

  @GetMapping(value = "/nearby", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(
      summary = "Get nearby products",
      description = "Retrieves products paginated and sorted by distance from location provided.")
  @ApiResponse(responseCode = "200", description = "Returned products page successfully!")
  @ApiResponse(responseCode = "500", description = "Server error.")
  public Slice<ProductDTO> findClosest(
      @RequestParam(name = "page", defaultValue = "0") int page,
      @RequestParam(name = "lat") Double lat,
      @RequestParam(name = "lon") Double lon) {
    return productService.findAllOrderByClosest(lat, lon, page);
  }

  @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(summary = "Get product by id", description = "Retrieves product details by id.")
  @ApiResponse(responseCode = "200", description = "Returned product successfully!")
  @ApiResponse(responseCode = "404", description = "Product with provided id does not exist.")
  @ApiResponse(responseCode = "500", description = "Server error.")
  public ProductDTO getById(@PathVariable String id) {
    return productService.findById(id);
  }

  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiResponse(responseCode = "200", description = "Created product successfully!")
  @ApiResponse(responseCode = "500", description = "Server error.")
  public ProductDTO addNewProduct(@RequestBody @Valid ProductCreateDTO productCreateDTO) {
    return productService.create(productCreateDTO);
  }
}
