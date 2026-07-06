package com.br.desafio.dev.jr.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class LivroControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void deveCadastrarLivroComSucesso() throws Exception{
		
		String livroJson = "{\"titulo\":\"O Senhor dos Anéis\",\"autor\":\"J.R.R. Tolkien\",\"anoPublicacao\":1954}";
		
		mockMvc.perform(post("/api/livros")
                .contentType(MediaType.APPLICATION_JSON)
                .content(livroJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.titulo").value("O Senhor dos Anéis"));
	}
	
	@Test
    void deveRetornarNotFoundExceptionAoBuscarLivroInexistente() throws Exception {
        
        mockMvc.perform(get("/api/livros/999")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.erro").exists());
    }
	
	@Test
    void deveListarTodosOsLivrosComSucesso() throws Exception {
        mockMvc.perform(get("/api/livros")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
	
	@Test
    void deveAtualizarLivroComSucesso() throws Exception {

        String livroInicialJson = "{\"titulo\":\"Livro Antigo\",\"autor\":\"Autor Antigo\",\"anoPublicacao\":2000}";
        
        String resultadoJson = mockMvc.perform(post("/api/livros")
                .contentType(MediaType.APPLICATION_JSON)
                .content(livroInicialJson))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();
                
        String idGerado = resultadoJson.substring(resultadoJson.indexOf("\"id\":") + 5).split("[,}]")[0].trim();

        String livroAtualizadoJson = "{\"titulo\":\"Livro Novo\",\"autor\":\"Autor Novo\",\"anoPublicacao\":2026}";

        mockMvc.perform(put("/api/livros/" + idGerado)
                .contentType(MediaType.APPLICATION_JSON)
                .content(livroAtualizadoJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Livro Novo"));
    }
	
	
	@Test
    void deveDeletarLivroComSucesso() throws Exception {
      
		String livroJson = "{\"titulo\":\"O Senhor dos Anéis\",\"autor\":\"J.R.R. Tolkien\",\"anoPublicacao\":1954}";
        
        mockMvc.perform(post("/api/livros")
                .contentType(MediaType.APPLICATION_JSON)
                .content(livroJson))
                .andExpect(status().isCreated());

        mockMvc.perform(delete("/api/livros/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Livro deletado com sucesso!"));
    }
               
	
	@Test
    void deveRetornarNotFoundAoPassarIdInvalidoParaDeletar() throws Exception {

        mockMvc.perform(get("/api/livros/abc")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()) 
                .andExpect(jsonPath("$.erro").exists());
    }
	
	
}
