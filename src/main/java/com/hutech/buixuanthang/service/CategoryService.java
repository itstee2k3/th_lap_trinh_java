package com.hutech.buixuanthang.service;

import com.hutech.buixuanthang.model.Category;
import com.hutech.buixuanthang.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {
    @Autowired
    private final CategoryRepository categoryRepository;

    public List<Category> getList() {
        List<Category> list = categoryRepository.findAll().stream().toList();
        return list;
    }

    public Category GetCategory(Long id) {
        return categoryRepository.getReferenceById(id);
    }

    public void DeleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    public void EditOrAddCategory(Category category) {
        categoryRepository.save(category);
    }
}