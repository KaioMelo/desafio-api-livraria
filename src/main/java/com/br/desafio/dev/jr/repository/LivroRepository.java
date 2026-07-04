package com.br.desafio.dev.jr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.desafio.dev.jr.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long>{

}
