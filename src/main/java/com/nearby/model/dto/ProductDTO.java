package com.nearby.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


public record ProductDTO(
    String id, String name, String description, Double price, int views, String categoryName, String categoryId, String image, Double latitude, Double longitude) {}
