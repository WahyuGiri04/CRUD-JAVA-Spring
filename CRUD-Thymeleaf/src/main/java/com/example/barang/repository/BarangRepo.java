package com.example.barang.repository;

import com.example.barang.model.Barang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarangRepo extends JpaRepository<Barang, Integer> {
}
