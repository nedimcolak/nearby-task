package com.nearby.model.dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public record PriceHistoryDTO(String id, Double price, LocalDateTime timestamp) {}
