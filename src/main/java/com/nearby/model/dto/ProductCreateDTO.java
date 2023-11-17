package com.nearby.model.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;


public record ProductCreateDTO(
    @NotBlank String name,
    String description,
    @NotBlank String categoryId,
    @NotNull Double price,
    Double latitude,
    Double longitude,
    @NotBlank String image) {}
