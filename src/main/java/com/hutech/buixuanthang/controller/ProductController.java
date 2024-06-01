package com.hutech.buixuanthang.controller;

import com.hutech.buixuanthang.model.Product;
import com.hutech.buixuanthang.service.CategoryService;
import com.hutech.buixuanthang.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;


@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    public ProductController(ProductService productService, CategoryService categoryService){
        this.productService = productService;
        this.categoryService = categoryService;
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
        return "products/add";
    }

    @PostMapping("/add")
    public String confirmAdd(@ModelAttribute Product product, Model model){
        productService.EditOrAddProduct(product);
        model.addAttribute("listProduct", productService.getList());
        return "redirect:/products/listproduct";
    }


    @GetMapping("/edit")
    public String edit(@RequestParam Long id, Model model){
        Product prod = productService.GetProduct(id);
        model.addAttribute("product", prod);
        model.addAttribute("categories", categoryService.getList());
        return "products/edit";
    }

    @PostMapping("/edit")
    public String confirmEdit (Product product, Model model){
        productService.EditOrAddProduct(product);
        model.addAttribute("listProduct", productService.getList());
        return "redirect:/products/listproduct";
    }
    @GetMapping("/delete")
    public String delete(@RequestParam Long id, Model model){
        Product prod = productService.GetProduct(id);
        model.addAttribute("product", prod);
        return "products/delete";
    }
    @PostMapping("/delete")
    public String confirmDelete (Product product, Model model){
        productService.DeleteProduct(product.getId());
        model.addAttribute("listProduct", productService.getList());
        return "redirect:/products/listproduct";
    }
    @GetMapping("/searchbook")
    public String searchProductByName(String name, Model model){
        model.addAttribute("listProduct",productService.getList().stream().filter(product -> product.getName().contains(name)).collect(Collectors.toList()));
        return "products/searchbook";
    }
}