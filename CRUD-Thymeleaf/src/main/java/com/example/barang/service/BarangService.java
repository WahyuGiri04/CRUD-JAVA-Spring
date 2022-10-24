package com.example.barang.service;

import com.example.barang.model.Barang;
import com.example.barang.repository.BarangRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BarangService {

    private final BarangRepo barangRepo;

    public BarangService(BarangRepo barangRepo){
        this.barangRepo = barangRepo;
    }

    public List<Barang> getAll(){
        return barangRepo.findAll();
    }

    public Barang getById(Integer id){
        return barangRepo.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public String save(Barang barang){
        barangRepo.save(barang);
        return "Ok";
    }

    public String update(Integer id, Barang barang){
        Barang data = barangRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        data.setJumlahBarang(barang.getJumlahBarang());
        data.setNamaBarang(barang.getNamaBarang());
        barangRepo.save(data);
        return "Ok";
    }

    public String delete(Integer id){
        try{
            barangRepo.deleteById(id);
            return "Ok";
        }catch (Exception e){
            return e.toString();
        }
    }

    public Page<Barang> findPage(int pageNum, int pageSize, String sortFiled, String sortDirection){
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortFiled).ascending() : Sort.by(sortFiled).descending();
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sort);
        return this.barangRepo.findAll(pageable);
    }
}
