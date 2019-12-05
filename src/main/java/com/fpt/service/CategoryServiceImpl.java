package com.fpt.service;

import com.fpt.entity.Category;
import com.fpt.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findActiveCategory(Category.Status.ACTIVE.getValue());
    }

    @Override
    public List<Category> search(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public Category getById(long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Category save(Category category) {
        category.setStatus(Category.Status.ACTIVE.getValue());
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public boolean delete(Category category) {
        category.setStatus(Category.Status.DELETED.getValue());
        categoryRepository.save(category);
        return true;
    }
}
