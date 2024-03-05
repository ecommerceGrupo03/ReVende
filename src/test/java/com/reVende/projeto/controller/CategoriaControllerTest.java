package com.reVende.projeto.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.reVende.projeto.Service.UsuarioService;
import com.reVende.projeto.model.Categoria;
import com.reVende.projeto.model.Usuario;
import com.reVende.projeto.repository.CategoriaRepository;
import com.reVende.projeto.repository.UsuarioRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CategoriaControllerTest {
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	
	
	@BeforeAll
	void start(){
		categoriaRepository.deleteAll();
		
		usuarioRepository.deleteAll();

		usuarioService.cadastrarUsuario(new Usuario(0L, "Root", "root@root.com", "rootroot", "-", "56276589742", ""));

		
		categoriaRepository.save(new Categoria(0L, "Categoria de Testes 1", "Isso é uma categoria criada para testes"));
		categoriaRepository.save(new Categoria(0L, "Categoria de Testes 2", "Isso é uma categoria criada para testes"));
		categoriaRepository.save(new Categoria(0L, "Categoria de Testes 3", "Isso é uma categoria criada para testes"));
		categoriaRepository.save(new Categoria(0L, "Categoria Qualquer 1", "Categoria aleatória"));
		categoriaRepository.save(new Categoria(0L, "Categoria Qualquer 2", "Categoria aleatória"));
		categoriaRepository.save(new Categoria(0L, "Categoria Qualquer 3", "Categoria aleatória"));
	}
	
	@Test
	@DisplayName("Criar uma categoria")
	public void deveCriarUmaCategoria() {
		HttpEntity<Categoria> corpoRequisicao = new HttpEntity<Categoria>(new Categoria(0L, "Categoria de Testes 75", "Isso é uma categoria criada para testes"));
		
		ResponseEntity<Categoria> corpoResposta = testRestTemplate.withBasicAuth("root@root.com", "rootroot")
				.exchange("/categorias", HttpMethod.POST, corpoRequisicao, Categoria.class);
		
		assertEquals(HttpStatus.CREATED, corpoResposta.getStatusCode());
	}
	
	@Test
	@DisplayName("Atualização de uma categoria")
	public void deveAtualizarCategoria() {
		Categoria categoriaExistente = categoriaRepository.save(new Categoria(0L, "Categoria de Testes 100", "Isso é uma categoria criada para testes"));
		
		Categoria categoriaNova = new Categoria(categoriaExistente.getId(), "Categoria de Testes 200", "Isso é uma categoria criada para testes");
		
		HttpEntity<Categoria> corpoRequisicao = new HttpEntity<Categoria>(categoriaNova);
		
		ResponseEntity<Categoria> corpoResposta = testRestTemplate.withBasicAuth("root@root.com", "rootroot")
				.exchange("/categorias", HttpMethod.PUT, corpoRequisicao, Categoria.class);
		
		assertEquals(HttpStatus.CREATED, corpoResposta.getStatusCode());
	}
	
	@Test
	@DisplayName("Listar todos as categorias")
	public void deveListarTodasAsCategorias() {
		categoriaRepository.save(new Categoria(0L, "Categoria de Testes 51", "Isso é uma categoria criada para testes"));
		categoriaRepository.save(new Categoria(0L, "Categoria de Testes 61", "Isso é uma categoria criada para testes"));
		
		ResponseEntity<String> resposta = testRestTemplate.withBasicAuth("root@root.com", "rootroot")
				.exchange("/categorias/listar", HttpMethod.GET, null, String.class);
		
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}
	
	@Test
	@DisplayName("Buscar categorias por nome")
	public void deveBuscarCategoriasPorNome() {
		categoriaRepository.save(new Categoria(0L, "Categoria de Testes 71", "Isso é uma categoria criada para testes"));
		categoriaRepository.save(new Categoria(0L, "Categoria Qualquer 81", "Isso é uma categoria qualquer"));
		
		ResponseEntity<String> resposta = testRestTemplate.withBasicAuth("root@root.com", "rootroot")
				.exchange("/categorias/nome/Qualquer", HttpMethod.GET, null, String.class);
		
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}
	
	@Test
	@DisplayName("Buscar categorias por descrição")
	public void deveBuscarCategoriasPorDescricao() {
		categoriaRepository.save(new Categoria(0L, "Categoria de Testes 91", "Isso é uma categoria criada para testes"));
		categoriaRepository.save(new Categoria(0L, "Categoria Qualquer 101", "Isso é uma categoria qualquer"));
		
		ResponseEntity<String> resposta = testRestTemplate.withBasicAuth("root@root.com", "rootroot")
				.exchange("/categorias/descricao/para", HttpMethod.GET, null, String.class);
		
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}
	
	@Test
	@DisplayName("Apagar categoria por ID")
	public void deveApagarCategoriaPorId() {
		Categoria categoria = categoriaRepository.save(new Categoria(0L, "Categoria de Testes 91", "Isso é uma categoria criada para testes"));
		
		ResponseEntity<String> resposta = testRestTemplate.withBasicAuth("root@root.com", "rootroot")
				.exchange("/categorias/" + categoria.getId(), HttpMethod.DELETE, null, String.class);
		
		assertEquals(HttpStatus.NO_CONTENT, resposta.getStatusCode());
	}

}
