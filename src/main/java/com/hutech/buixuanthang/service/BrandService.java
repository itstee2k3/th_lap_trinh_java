package com.hutech.buixuanthang.service;

import com.hutech.buixuanthang.model.Brand;
import com.hutech.buixuanthang.model.Category;
import com.hutech.buixuanthang.model.Manufacturer;
import com.hutech.buixuanthang.repository.BrandRepository;
import com.hutech.buixuanthang.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BrandService {
    @Autowired
    private final BrandRepository brandRepository;

    public List<Brand> getList() {
        List<Brand> list = brandRepository.findAll().stream().toList();
        return list;
    }

    public Brand GetBrand(Long id) {
        return brandRepository.getReferenceById(id);
    }

    public void DeleteBrand(Long id) {
        brandRepository.deleteById(id);
    }

    public void EditOrAddBrand(Brand brand) {
        brandRepository.save(brand);
    }

    public Brand addBrand(Brand brand){
        return brandRepository.save(brand);
    }

    public List<Brand> getAllBrand(){
        return brandRepository.findAll();
    }

    public Optional<Brand> getBrandById(Long id){
        return brandRepository.findById(id);
    }

    public void deleteBrand(Long id){
        brandRepository.deleteById(id);
    }
}
