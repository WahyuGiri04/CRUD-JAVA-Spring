package com.example.barang.controller;

import com.example.barang.model.Barang;
import com.example.barang.service.BarangService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BarangController {

    private final BarangService barangService;

    public BarangController (BarangService barangService){
        this.barangService = barangService;
    }

    @GetMapping("/")
    public String getAll(Model model){
        List<Barang> data = barangService.getAll();
        model.addAttribute("listBarang", data);
        return "barang/index";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable int id, Model model){
        Barang barang = barangService.getById(id);
        model.addAttribute("Barang", barang);
        return "Barang/update_barang";
    }

    @GetMapping("/add")
    public String add(Model model){
        Barang barang = new Barang();
        model.addAttribute("Barang", barang);
        return "Barang/add_barang";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("Barang") Barang barang){
        barangService.save(barang);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id){
        barangService.delete(id);
        return "redirect:/";
    }
}
