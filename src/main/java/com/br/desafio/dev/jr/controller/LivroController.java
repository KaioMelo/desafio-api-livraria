package com.br.desafio.dev.jr.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.desafio.dev.jr.model.Livro;
import com.br.desafio.dev.jr.service.LivroService;

@RestController
@RequestMapping("/api")
public class LivroController {

	private final LivroService livroService;

	public LivroController(LivroService livroService) {
		this.livroService = livroService;
	}
	
	@PostMapping("/livro")
	public ResponseEntity<Livro> salvarLivro(@RequestBody Livro livro){
		
		Livro livroSalvo = livroService.salvarLivro(livro);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(livroSalvo);
	}
		
}
