package com.hutech.buixuanthang.controller;

import com.hutech.buixuanthang.model.Product;
import com.hutech.buixuanthang.model.ProductImages;
import com.hutech.buixuanthang.service.BrandService;
import com.hutech.buixuanthang.service.CategoryService;
import com.hutech.buixuanthang.service.ManufacturerService;
import com.hutech.buixuanthang.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.Normalizer;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final ManufacturerService manufacturerService;
    private final BrandService brandService;

    public ProductController(ProductService productService, CategoryService categoryService, ManufacturerService manufacturerService, BrandService brandService){
        this.productService = productService;
        this.categoryService = categoryService;
        this.manufacturerService = manufacturerService;
        this.brandService = brandService;
    }

    @GetMapping("/listproduct")
    public String getList(Model model){
        model.addAttribute("listProduct", productService.getList());
        return "products/listproduct";
    }

    @GetMapping("/add")
    public String addProduct(Model model){
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.getList());
        model.addAttribute("manufacturers", manufacturerService.getList());
        model.addAttribute("brands", brandService.getList());
        return "products/add";
    }

    @PostMapping("/add")
    public String addProduct(@Valid Product product,
                             @RequestParam("imageProduct") MultipartFile imageProduct    ) {
//                             @RequestParam("imageProducts") MultipartFile[] imageProducts

        // Save the product and the main image
        productService.editOrAddProduct(product, imageProduct);
        // If you need to handle multiple images, add the logic here
        return "redirect:/products/listproduct";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam Long id, Model model){
        Product prod = productService.getProduct(id);
        model.addAttribute("product", prod);
        model.addAttribute("categories", categoryService.getList());
        model.addAttribute("manufacturers", manufacturerService.getList());
        model.addAttribute("brands", brandService.getList());
        model.addAttribute("imageProduct", "/imgproduct/" + prod.getMainImage()); // Populate the file input field with the main image URL
        return "products/edit";
    }

    @PostMapping("/edit")
    public String confirmEdit(@Valid Product product,
                              @RequestParam("imageProduct") MultipartFile imageProduct,
                              @RequestParam(name = "keepOldImage", required = false) boolean keepOldImage) {
        if (!imageProduct.isEmpty() || !keepOldImage) {
            // Nếu có ảnh mới hoặc người dùng không muốn giữ ảnh cũ
            productService.editOrAddProduct(product, imageProduct);
        } else {
            // Nếu người dùng muốn giữ ảnh cũ và không chọn ảnh mới
            productService.editProductKeepOldImage(product);
        }
        return "redirect:/products/listproduct";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Long id, Model model) {
        Product prod = productService.getProduct(id);
        model.addAttribute("product", prod);
        return "products/delete";
    }

    @PostMapping("/delete")
    public String confirmDelete(Product product) {
        productService.deleteProduct(product.getId());
        return "redirect:/products/listproduct";
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