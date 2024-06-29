package com.hutech.buixuanthang.controller;

import com.hutech.buixuanthang.model.Brand;
import com.hutech.buixuanthang.model.Manufacturer;
import com.hutech.buixuanthang.service.BrandService;
import com.hutech.buixuanthang.service.ManufacturerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/manu")
public class ManuApiController {
    private final ManufacturerService manufacturerService;

    @GetMapping
    public List<Manufacturer> getAllManufacturers() {
        return manufacturerService.getList();
    }

    @PostMapping
    public Manufacturer createManu(@RequestBody Manufacturer manu) {
        return manufacturerService.addManu(manu);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Manufacturer> getManuById(@PathVariable Long id) {
        Manufacturer manu = manufacturerService.getManuById(id)
                .orElseThrow(() -> new RuntimeException("Product Manu not found on :: " + id));
        return ResponseEntity.ok().body(manu);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Manufacturer> updateManu(@PathVariable Long id,
                                             @RequestBody Manufacturer manuDetail) {
        Manufacturer manu = manufacturerService.getManuById(id)
                .orElseThrow(() -> new RuntimeException("Product Manu not found on :: " + id));
        manu.setName(manuDetail.getName());
        manu.setAddress(manuDetail.getAddress());
        final Manufacturer updatedManuProduct = manufacturerService.addManu(manu);
        return ResponseEntity.ok(updatedManuProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteManu(@PathVariable Long id) {
        Manufacturer manu = manufacturerService.getManuById(id)
                .orElseThrow(() -> new RuntimeException("Product Manu not found on :: " + id));
        manufacturerService.deleteManu(id);
        return ResponseEntity.ok().build();
    }
}
