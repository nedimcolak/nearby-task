package com.nearby.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public record CreateCategoryDTO(@NotBlank String name) {}
