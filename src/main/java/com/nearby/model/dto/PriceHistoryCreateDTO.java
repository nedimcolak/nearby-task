package com.nearby.model.dto;

import com.nearby.model.Product;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

public record PriceHistoryCreateDTO(Double price, Product product, LocalDateTime timestamp) {}
