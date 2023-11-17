package com.nearby.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Entity(name = "price_history")
@Table(name = "price_history")
@Getter
@Setter
public class PriceHistory {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id")
  private Product product;

  private Double price;

  @CreationTimestamp private LocalDateTime timestamp;
}
