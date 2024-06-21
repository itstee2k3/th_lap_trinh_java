package com.hutech.buixuanthang.controller;

import com.hutech.buixuanthang.model.Brand;
import com.hutech.buixuanthang.model.Manufacturer;
import com.hutech.buixuanthang.service.BrandService;
import com.hutech.buixuanthang.service.ManufacturerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.Normalizer;
import java.util.Locale;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/brands")
public class BrandController {
    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/listbrand")
    public String getList(Model model){
        model.addAttribute("listBrand", brandService.getList());
        return "brands/listbrand";
    }

    @GetMapping("/add")
    public String addBrand(Model model){
        model.addAttribute("brand", new Brand());
        return "brands/add";
    }

    @PostMapping("/add")
    public String confirmAdd(@ModelAttribute Brand brand, Model model){
        brandService.EditOrAddBrand(brand);
        model.addAttribute("listBrand", brandService.getList());
        return "redirect:/brands/listbrand";
    }


    @GetMapping("/edit")
    public String edit(@RequestParam Long id, Model model){
        Brand brand = brandService.GetBrand(id);
        model.addAttribute("brand", brand);
        return "brands/edit";
    }

    @PostMapping("/edit")
    public String confirmEdit (Brand brand, Model model){
        brandService.EditOrAddBrand(brand);
        model.addAttribute("listBrand", brandService.getList());
        return "redirect:/brands/listbrand";
    }
    @GetMapping("/delete")
    public String delete(@RequestParam Long id, Model model){
        Brand brand = brandService.GetBrand(id);
        model.addAttribute("brand", brand);
        return "brands/delete";
    }
    @PostMapping("/delete")
    public String confirmDelete (Brand brand, Model model){
        brandService.DeleteBrand(brand.getId());
        model.addAttribute("listBrand", brandService.getList());
        return "redirect:/brands/listbrand";
    }

    @GetMapping("/searchbrand")
    public String searchBrand(Model model, @RequestParam String name) {
        String normalizedInputName = normalizeString(name);

        model.addAttribute("listBrand", brandService.getList().stream()
                .filter(brand -> normalizeString(brand.getName()).contains(normalizedInputName))
                .collect(Collectors.toList()));

        return "brands/searchbrand";
    }

    private String normalizeString(String input) {
        if (input == null) {
            return null;
        }
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        return normalized.replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase(Locale.ROOT);
    }
}
