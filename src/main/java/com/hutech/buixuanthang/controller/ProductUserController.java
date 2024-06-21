package com.hutech.buixuanthang.controller;

import com.hutech.buixuanthang.service.BrandService;
import com.hutech.buixuanthang.service.CategoryService;
import com.hutech.buixuanthang.service.ManufacturerService;
import com.hutech.buixuanthang.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.Normalizer;
import java.util.Locale;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/productusers")
public class ProductUserController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final ManufacturerService manufacturerService;
    private final BrandService brandService;

    public ProductUserController(ProductService productService, CategoryService categoryService, ManufacturerService manufacturerService, BrandService brandService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.manufacturerService = manufacturerService;
        this.brandService = brandService;
    }


    @GetMapping("/index")
    public String getList(Model model){
        model.addAttribute("listProduct", productService.getList());
        return "productusers/index";
    }

    @GetMapping("/searchproduct")
    public String searchProduct(Model model, @RequestParam String name) {
        String normalizedInputName = normalizeString(name);

        model.addAttribute("listProduct", productService.getList().stream()
                .filter(product -> normalizeString(product.getName()).contains(normalizedInputName))
                .collect(Collectors.toList()));

        return "products/searchproduct";
    }

    private String normalizeString(String input) {
        if (input == null) {
            return null;
        }
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        return normalized.replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase(Locale.ROOT);
    }
}
