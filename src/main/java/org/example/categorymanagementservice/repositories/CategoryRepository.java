package org.example.categorymanagementservice.repositories;

import org.example.categorymanagementservice.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
    List<Category> findAll();
    Category save(Category category);
    Category findById(int id);
    void deleteById(int id);
}
