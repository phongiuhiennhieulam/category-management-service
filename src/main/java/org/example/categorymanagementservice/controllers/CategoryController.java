package org.example.categorymanagementservice.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.categorymanagementservice.dtos.CategoryDTO;
import org.example.categorymanagementservice.entities.Category;
import org.example.categorymanagementservice.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    private Logger logger = LogManager.getLogger(Controller.class);

    @GetMapping("")
    public ResponseEntity<List<Category>> getCategories() {
        try{
            logger.info("Get All Category");
            List<Category> categories = categoryService.getAllCategories();
            if(categories.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            else
                return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<Category> createCategory(@RequestBody CategoryDTO categoryRequest) {
        try{
            logger.info("Create new Category");
            Category newCategory = categoryService.createCategory(new Category(0,categoryRequest.getName(),categoryRequest.getDate()));
            return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable int id, @RequestBody CategoryDTO categoryRequest) {
        try{
            logger.info("Update Category id is "+id);
            if(categoryService.findByID(id) == null){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(categoryService.updateCategory(categoryRequest,id), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> updateCategory(@PathVariable int id) {

        try{
            logger.info("Delete Category id is "+id);
            if(categoryService.findByID(id) == null){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            categoryService.deleteCategory(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
