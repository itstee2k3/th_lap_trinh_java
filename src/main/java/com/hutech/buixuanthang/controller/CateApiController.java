package com.hutech.buixuanthang.controller;

import com.hutech.buixuanthang.model.Category;
import com.hutech.buixuanthang.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cate")
public class CateApiController {
    private final CategoryService categoryService;

    @GetMapping
    public List<Category> getAllCate() {
        return categoryService.getList();
    }

    @PostMapping
    public Category createCate(@RequestBody Category category) {
        return categoryService.addCate(category);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCateById(@PathVariable Long id) {
        Category category = categoryService.getCateById(id)
                .orElseThrow(() -> new RuntimeException("Product Cate not found on :: "
                        + id));
        return ResponseEntity.ok().body(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCate(@PathVariable Long id,
                                                  @RequestBody Category categoryDetail) {
        Category category = categoryService.getCateById(id)
                .orElseThrow(() -> new RuntimeException("Product Cate not found on :: " + id));
        category.setName(categoryDetail.getName());
        final Category updatedCateProduct = categoryService.addCate(category);
        return ResponseEntity.ok(updatedCateProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCate(@PathVariable Long id) {
        Category category = categoryService.getCateById(id)
                .orElseThrow(() -> new RuntimeException("Product Type not found on :: " + id));
        categoryService.DeleteCategory(id);
        return ResponseEntity.ok().build();
    }
}
