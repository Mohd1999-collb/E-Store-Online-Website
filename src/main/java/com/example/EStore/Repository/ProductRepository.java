package com.example.EStore.Repository;

import com.example.EStore.Enum.Category;
import com.example.EStore.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByCategoryAndPrice(Category category, Integer price);

    @Query(value = "SELECT product.name FROM product order by price desc limit 5", nativeQuery = true)
    List<String> top5CostliestProduct();

    @Query(value = "SELECT product.name FROM product order by price limit 5", nativeQuery = true)
    List<String> top5CheapestProduct();
}
