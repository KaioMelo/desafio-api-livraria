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
	
	public Optional<Livro> alterarLivro(Long id, Livro livro) {
		return livroRepository.findById(id).map(livroExistente -> {

	        livroExistente.setTitulo(livro.getTitulo());
	        livroExistente.setAutor(livro.getAutor());
	        livroExistente.setAnoPublicacao(livro.getAnoPublicacao());
	        
	        return livroRepository.save(livroExistente);
	    });
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
