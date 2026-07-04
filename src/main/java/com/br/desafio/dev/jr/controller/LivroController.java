package com.br.desafio.dev.jr.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.desafio.dev.jr.model.Livro;
import com.br.desafio.dev.jr.service.LivroService;

@RestController
@RequestMapping("/api/livros")
public class LivroController {

	private final LivroService livroService;

	public LivroController(LivroService livroService) {
		this.livroService = livroService;
	}
	
	@PostMapping
	public ResponseEntity<Livro> salvarLivro(@RequestBody Livro livro){
		
		Livro livroSalvo = livroService.salvarLivro(livro);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(livroSalvo);
	}
	
	@GetMapping
	public ResponseEntity<List<Livro>> buscarTodosLivros(){
		
		List<Livro> listaLivros = livroService.buscarTodosLivros();
		
		return ResponseEntity.status(HttpStatus.OK).body(listaLivros);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Livro> buscarLivroPorId(@PathVariable("id") Long id){
		
		return livroService.buscarLivro(id)
	            .map(livro -> ResponseEntity.ok(livro))
	            .orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Livro> atualizarLivro(@PathVariable("id") Long id, @RequestBody Livro livro){
		
		return livroService.alterarLivro(id, livro)
	            .map(livroSalvo -> ResponseEntity.ok(livroSalvo))
	            .orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deletarLivroPorId(@PathVariable("id") Long id){
		
		livroService.deletarLivro(id);
		
		return ResponseEntity.ok("Livro deletado com sucesso!");
	}
		
}
