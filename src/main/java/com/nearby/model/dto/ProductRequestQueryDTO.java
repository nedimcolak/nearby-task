package com.nearby.model.dto;

public record ProductRequestQueryDTO(
    Double minPrice, Double maxPrice, String searchTerm, String sortBy, String order) {}
