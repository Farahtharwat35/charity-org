package com.charity_org.demo.Models.Service;



import com.charity_org.demo.Models.Model.FurnitureType;

import com.charity_org.demo.Models.Repository.FurnitureTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FurnitureTypeService {

    @Autowired
    private FurnitureTypeRepository furnitureTypeRepository;

    public List<FurnitureType> getAllFurnitureTypes() {
        return furnitureTypeRepository.findAll();
    }

    public Optional<FurnitureType> findById(Long id) {
        return furnitureTypeRepository.findById(id);
    }

    public Optional<FurnitureType> findByName(String name) {
        return furnitureTypeRepository.findByName(name);
    }
}