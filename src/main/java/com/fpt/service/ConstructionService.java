package com.fpt.service;

import com.fpt.entity.Construction;

import java.util.List;

public interface ConstructionService {
    List<Construction> findAll();

    List<Construction> search(String name);

    Construction getById(long id);

    Construction save(Construction construction);

    Construction update(Construction construction);

    boolean delete(Construction construction);
}
