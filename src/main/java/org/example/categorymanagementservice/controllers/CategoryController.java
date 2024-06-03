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

    @PostMapping("create")
    public ResponseEntity<Category> createCategory(@RequestBody CategoryDTO categoryRequest) {
        try{
            logger.info("Create new Category");
            Category newCategory = categoryService.createCategory(new Category(0,categoryRequest.getName(),categoryRequest.isStatus()));
            return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("update")
    public ResponseEntity<Category> updateCategory(@RequestBody Category categoryRequest) {

        try{
            logger.info("Update Category id is "+categoryRequest.getId());
            if(categoryRequest.getId() == null){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(categoryService.updateCategory(categoryRequest), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("delete")
    public ResponseEntity<HttpStatus> updateCategory(@RequestParam("id") int id) {

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
