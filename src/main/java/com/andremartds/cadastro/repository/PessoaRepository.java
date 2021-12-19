package com.andremartds.cadastro.repository;

import com.andremartds.cadastro.model.Pessoa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
