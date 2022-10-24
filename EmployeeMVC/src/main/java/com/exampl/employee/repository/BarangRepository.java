package com.exampl.employee.repository;

import com.exampl.employee.model.Barang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarangRepository extends JpaRepository<Barang, Integer> {
}
