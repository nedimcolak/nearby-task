package com.nearby.repository;

import com.nearby.model.Product;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
  @Query(
      value =
          "select p from product p order by ACOS(SIN(RADIANS(:lat)) * SIN(RADIANS(p.latitude)) + COS(RADIANS (:lat)) * COS(RADIANS (p.latitude)) * COS(RADIANS (:lon - p.longitude))) asc")
  Page<Product> getClosestProducts(
      @Param(value = "lat") double latitude,
      @Param(value = "lon") double longitude,
      Pageable pageable);

  @Query(
      "SELECT p FROM product p WHERE (:searchTerm = '' OR p.name ILIKE concat('%', :searchTerm, '%')) AND ((:#{#categories.size() == 0} = true) OR p.category.id IN :categories) AND ((:minPrice is null OR p.price > :minPrice) AND (:maxPrice is null OR p.price < :maxPrice))")
  Page<Product> findAllFilterBy(
      @Param(value = "categories") List<String> categories,
      @Param(value = "minPrice") Double minPrice,
      @Param(value = "maxPrice") Double maxPrice,
      @Param(value = "searchTerm") String searchTerm,
      Pageable pageable);
}
