package com.hutech.buixuanthang.controller;

import com.hutech.buixuanthang.model.Brand;
import com.hutech.buixuanthang.model.Category;
import com.hutech.buixuanthang.service.BrandService;
import com.hutech.buixuanthang.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/brand")
public class BrandApiController {
    private final BrandService brandService;

    @GetMapping
    public List<Brand> getAllBrand() {
        return brandService.getList();
    }

    @PostMapping
    public Brand createBrand(@RequestBody Brand brand) {
        return brandService.addBrand(brand);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Brand> getBrandById(@PathVariable Long id) {
        Brand brand = brandService.getBrandById(id)
                .orElseThrow(() -> new RuntimeException("Product Brand not found on :: " + id));
        return ResponseEntity.ok().body(brand);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Brand> updateBrand(@PathVariable Long id,
                                               @RequestBody Brand brandDetail) {
        Brand brand = brandService.getBrandById(id)
                .orElseThrow(() -> new RuntimeException("Product Brand not found on :: " + id));
        brand.setName(brandDetail.getName());
        brand.setCountry(brandDetail.getCountry());
        final Brand updatedBrandProduct = brandService.addBrand(brand);
        return ResponseEntity.ok(updatedBrandProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable Long id) {
        Brand brand = brandService.getBrandById(id)
                .orElseThrow(() -> new RuntimeException("Product Brand not found on :: " + id));
        brandService.DeleteBrand(id);
        return ResponseEntity.ok().build();
    }
}
