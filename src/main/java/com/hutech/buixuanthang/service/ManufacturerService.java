package com.hutech.buixuanthang.service;

import com.hutech.buixuanthang.model.Brand;
import com.hutech.buixuanthang.model.Manufacturer;
import com.hutech.buixuanthang.repository.ManufacturerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ManufacturerService {
    @Autowired
    private final ManufacturerRepository manufacturerRepository;

    public List<Manufacturer> getList() {
        List<Manufacturer> list = manufacturerRepository.findAll().stream().toList();
        return list;
    }

    public Manufacturer GetManufacturer(Long id) {
        return manufacturerRepository.getReferenceById(id);
    }

    public void DeleteManufacturer(Long id) {
        manufacturerRepository.deleteById(id);
    }

    public void EditOrAddManufacturer(Manufacturer manufacturer) {
        manufacturerRepository.save(manufacturer);
    }

    public Manufacturer addManu(Manufacturer manu){
        return manufacturerRepository.save(manu);
    }

    public List<Manufacturer> getAllManu(){
        return manufacturerRepository.findAll();
    }

    public Optional<Manufacturer> getManuById(Long id){
        return manufacturerRepository.findById(id);
    }

    public void deleteManu(Long id){
        manufacturerRepository.deleteById(id);
    }
}
