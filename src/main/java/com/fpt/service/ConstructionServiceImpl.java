package com.fpt.service;

import com.fpt.entity.Construction;
import com.fpt.repository.ConstructionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ConstructionServiceImpl implements ConstructionService {
    @Autowired
    ConstructionRepository constructionRepository;

    @Override
    public List<Construction> findAll() {
        return constructionRepository.findActiveConstruction(Construction.Status.ACTIVE.getValue());
    }

    @Override
    public List<Construction> search(String name) {
        return constructionRepository.findByName(name);
    }

    @Override
    public Construction getById(long id) {
        return constructionRepository.findById(id).orElse(null);
    }

    @Override
    public Construction save(Construction construction) {
        construction.setStatus(Construction.Status.ACTIVE.getValue());
        return constructionRepository.save(construction);
    }

    @Override
    public Construction update(Construction construction) {
        return constructionRepository.save(construction);
    }

    @Override
    public boolean delete(Construction construction) {
        construction.setStatus(Construction.Status.DELETED.getValue());
        constructionRepository.save(construction);
        return true;
    }
}
