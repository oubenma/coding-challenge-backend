package com.example.codingchallengebackend.repository;

import com.example.codingchallengebackend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

//we can use @RepositoryRestResource to expose default endpoints
public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findAllProductsByCategoryId(@Param("id")int id);
}
