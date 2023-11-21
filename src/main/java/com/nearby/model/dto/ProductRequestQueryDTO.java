package com.nearby.model.dto;

public record ProductRequestQueryDTO(
    String categories, Double minPrice, Double maxPrice, String searchTerm, String sortBy, String order) {}
