package com.nearby.model;

import jakarta.persistence.*;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "product")
@Table(name = "product")
@Getter
@Setter
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  private String name;
  private String description;

  @ManyToOne
  @JoinColumn(name = "category_id", nullable = false)
  private Category category;

  private Double price;
  private Double latitude;
  private Double longitude;
  private int views;
  private String image;

  @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
  private List<PriceHistory> priceHistoryList;
}
