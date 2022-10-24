package com.exampl.employee.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Barang", schema = "dbo")
public class Barang {

    @Id
    private Integer id;

    private String namaBarang;

    private Integer jumlahBarang;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public Integer getJumlahBarang() {
        return jumlahBarang;
    }

    public void setJumlahBarang(Integer jumlahBarang) {
        this.jumlahBarang = jumlahBarang;
    }
}
