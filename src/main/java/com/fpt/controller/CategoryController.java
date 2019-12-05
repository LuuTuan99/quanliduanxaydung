package com.fpt.controller;

import com.fpt.entity.Category;
import com.fpt.service.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {
    @Autowired
    CategoryServiceImpl categoryService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Category>> list() {
        List<Category> categoryList = categoryService.findAll();
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Category> store(@RequestBody Category category) {
        try {
            Category createCategory = categoryService.save(category);
            return new ResponseEntity<>(createCategory, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/search")
    public ResponseEntity<List<Category>> search(@RequestParam String name) {
        List<Category> categoryList = categoryService.search(name);
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Category> detail(@PathVariable long id) {
        Category category = categoryService.getById(id);
        if (category == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(category, HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<Category> update(@PathVariable long id, @RequestBody Category category) {
        Category existCategory = categoryService.getById(id);
        if (existCategory == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            existCategory.setName(category.getName());
            existCategory.setStartDate(category.getStartDate());
            existCategory.setEndDate(category.getEndDate());
            existCategory.setEstimatedDate(category.getEstimatedDate());
            existCategory.setDescription(category.getDescription());
            existCategory.setStatus(category.getStatus());
            existCategory.setConstruction(category.getConstruction());
            categoryService.save(existCategory);
            return new ResponseEntity<>(existCategory, HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Category> delete(@PathVariable long id) {
        Category existCategory = categoryService.getById(id);
        if (existCategory == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        categoryService.delete(existCategory);
        return new ResponseEntity<>(existCategory, HttpStatus.OK);
    }
}
