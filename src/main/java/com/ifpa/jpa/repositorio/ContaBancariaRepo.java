package com.ifpa.jpa.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ifpa.jpa.modelo.ContaBancaria;

public interface ContaBancariaRepo extends JpaRepository<ContaBancaria, Long>{

}