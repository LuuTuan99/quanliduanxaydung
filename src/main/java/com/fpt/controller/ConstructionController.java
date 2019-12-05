package com.fpt.controller;

import com.fpt.entity.Construction;
import com.fpt.service.ConstructionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/constructions")
public class ConstructionController {
    @Autowired
    ConstructionServiceImpl constructionService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Construction>> list() {
        List<Construction> constructionList = constructionService.findAll();
        return new ResponseEntity<>(constructionList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Construction> store(@RequestBody Construction construction) {
        try {
            Construction createConstruction = constructionService.save(construction);
            return new ResponseEntity<>(createConstruction, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/search")
    public ResponseEntity<List<Construction>> search(@RequestParam String name) {
        List<Construction> constructionList = constructionService.search(name);
        return new ResponseEntity<>(constructionList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Construction> detail(@PathVariable long id) {
        Construction construction = constructionService.getById(id);
        if (construction == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(construction, HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<Construction> update(@PathVariable long id, @RequestBody Construction construction) {
        Construction existConstruction = constructionService.getById(id);
        if (existConstruction == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            existConstruction.setName(construction.getName());
            existConstruction.setStartDate(construction.getStartDate());
            existConstruction.setEndDate(construction.getEndDate());
            existConstruction.setEstimatedDate(construction.getEstimatedDate());
            existConstruction.setStatus(construction.getStatus());
            existConstruction.setReason(construction.getReason());
            existConstruction.setCategories(construction.getCategories());
            constructionService.save(existConstruction);
            return new ResponseEntity<>(existConstruction, HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Construction> delete(@PathVariable long id) {
        Construction existConstruction = constructionService.getById(id);
        if (existConstruction == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        constructionService.delete(existConstruction);
        return new ResponseEntity<>(existConstruction, HttpStatus.OK);
    }
}
