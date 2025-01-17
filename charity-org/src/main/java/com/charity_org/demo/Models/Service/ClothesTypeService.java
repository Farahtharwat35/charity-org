package com.charity_org.demo.Models.Service;


import com.charity_org.demo.Models.Model.ClothesType;

import com.charity_org.demo.Models.Repository.ClothesTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClothesTypeService {

    @Autowired
    private ClothesTypeRepository clothesTypeRepository;

    public List<ClothesType> getAllClothesTypes() {
        return clothesTypeRepository.findAll();
    }

    public Optional<ClothesType> findById(Long id) {
        return clothesTypeRepository.findById(id);
    }

    public Optional<ClothesType> findByName(String name) {
        return clothesTypeRepository.findByName(name);
    }
}