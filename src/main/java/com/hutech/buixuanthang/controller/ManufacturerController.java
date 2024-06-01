package com.hutech.buixuanthang.controller;

import com.hutech.buixuanthang.model.Manufacturer;
import com.hutech.buixuanthang.service.ManufacturerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/manufacturers")
public class ManufacturerController {
    private final ManufacturerService manufacturerService;

    public ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }
    @GetMapping("/listmanufacturer")
    public String getList(Model model){
        model.addAttribute("listManufacturer", manufacturerService.getList());
        return "manufacturers/listmanufacturer";
    }

    @GetMapping("/add")
    public String addManufacturer(Model model){
        model.addAttribute("manufacturer", new Manufacturer());
        return "manufacturers/add";
    }

    @PostMapping("/add")
    public String confirmAdd(@ModelAttribute Manufacturer manufacturer, Model model){
        manufacturerService.EditOrAddManufacturer(manufacturer);
        model.addAttribute("listManufacturer", manufacturerService.getList());
        return "redirect:/manufacturers/listmanufacturer";
    }


    @GetMapping("/edit")
    public String edit(@RequestParam Long id, Model model){
        Manufacturer manufacturer = manufacturerService.GetManufacturer(id);
        model.addAttribute("manufacturer", manufacturer);
        return "manufacturers/edit";
    }

    @PostMapping("/edit")
    public String confirmEdit (Manufacturer manufacturer, Model model){
        manufacturerService.EditOrAddManufacturer(manufacturer);
        model.addAttribute("listManufacturer", manufacturerService.getList());
        return "redirect:/manufacturers/listmanufacturer";
    }
    @GetMapping("/delete")
    public String delete(@RequestParam Long id, Model model){
        Manufacturer manufacturer = manufacturerService.GetManufacturer(id);
        model.addAttribute("manufacturer", manufacturer);
        return "manufacturers/delete";
    }
    @PostMapping("/delete")
    public String confirmDelete (Manufacturer manufacturer, Model model){
        manufacturerService.DeleteManufacturer(manufacturer.getId());
        model.addAttribute("listManufacturer", manufacturerService.getList());
        return "redirect:/manufacturers/listmanufacturer";
    }

    @GetMapping("/searchmanufacturer")
    public String searchManufacturer(Model model, @RequestParam String name){
        model.addAttribute("listManufacturer", manufacturerService.getList().stream().filter(manufacturer -> manufacturer.getName().contains(name)).collect(Collectors.toList()));
        return "manufacturers/searchmanufacturer";
    }
}
