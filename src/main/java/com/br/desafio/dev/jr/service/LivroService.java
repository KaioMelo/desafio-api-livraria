package com.br.desafio.dev.jr.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.br.desafio.dev.jr.model.Livro;
import com.br.desafio.dev.jr.repository.LivroRepository;

@Service
public class LivroService {

	private final LivroRepository livroRepository;

	public LivroService(LivroRepository livroRepository) {
		this.livroRepository = livroRepository;
	}
	
	public Livro salvarLivro(Livro livro) {
		return livroRepository.save(livro);
	}
	
	public List<Livro> buscarTodosLivros(){
		return livroRepository.findAll();
	}
	
	public Optional<Livro> buscarLivro(Long id) {
		return livroRepository.findById(id);
	}
	
	public void deletarLivro(Long id) {
		livroRepository.deleteById(id);
	}
	
}
