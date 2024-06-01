package com.hutech.buixuanthang.service;

import com.hutech.buixuanthang.model.Brand;
import com.hutech.buixuanthang.model.Category;
import com.hutech.buixuanthang.repository.BrandRepository;
import com.hutech.buixuanthang.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

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
}
