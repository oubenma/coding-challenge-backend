package com.example.codingchallengebackend.repository;


import com.example.codingchallengebackend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

//we can use @RepositoryRestResource to expose default endpoints
public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
