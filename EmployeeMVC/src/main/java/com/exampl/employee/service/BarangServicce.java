package com.exampl.employee.service;


import com.exampl.employee.model.Barang;
import com.exampl.employee.repository.BarangRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.Id;

@Service
public class BarangServicce {

    private final BarangRepository barangRepository;


    public BarangServicce(BarangRepository barangRepository){
        this.barangRepository = barangRepository;
    }

    public String save(Barang barang){
        barangRepository.save(barang);
        return "Success";
    }

    public Barang getById(Integer id){
        return barangRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
