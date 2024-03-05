package com.reVende.projeto.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

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
import com.reVende.projeto.model.Produto;
import com.reVende.projeto.model.Usuario;
import com.reVende.projeto.repository.CategoriaRepository;
import com.reVende.projeto.repository.ProdutoRepository;
import com.reVende.projeto.repository.UsuarioRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProdutoControllerTest {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private UsuarioRepository usuarioRepository;

	private Categoria c1, c2, c3;

	private Optional<Usuario> usuarioTeste;

	@BeforeAll
	void start() {
		produtoRepository.deleteAll();
		usuarioRepository.deleteAll();

		usuarioTeste = usuarioService
				.cadastrarUsuario(new Usuario(0L, "Root", "root@root.com", "rootroot", "-", "56276589742", ""));

		c1 = categoriaRepository
				.save(new Categoria(0L, "Categoria de testes 1", "Isso está aqui para testar o programa"));
		c2 = categoriaRepository
				.save(new Categoria(0L, "Categoria de testes 2", "Isso está aqui para testar o programa"));
		c3 = categoriaRepository.save(new Categoria(0L, "Categoria Qualquer 3", "Categoria aleatória"));

		produtoRepository.save(new Produto(0L, "Produto de Testes 1", "Isso é um produto criado para testes", 10L,
				12.99, "-", c1, usuarioTeste.get()));
		produtoRepository.save(new Produto(0L, "Produto de Testes 2", "Isso é um produto criado para testes", 20L,
				15.99, "-", c3, usuarioTeste.get()));
		produtoRepository.save(new Produto(0L, "Produto de Testes 3", "Isso é um produto criado para testes", 30L,
				13.99, "-", c2, usuarioTeste.get()));
		produtoRepository.save(
				new Produto(0L, "Produto Qualquer 1", "Produto aleatório", 40L, 11.99, "-", c2, usuarioTeste.get()));
		produtoRepository.save(
				new Produto(0L, "Produto Qualquer 2", "Produto aleatório", 50L, 14.99, "-", c3, usuarioTeste.get()));
		produtoRepository.save(
				new Produto(0L, "Produto Qualquer 3", "Produto aleatório", 60L, 16.99, "-", c1, usuarioTeste.get()));
	}

	@Test
	@DisplayName("Criar um produto")
	public void deveCriarUmProduto() {
		HttpEntity<Produto> corpoRequisicao = new HttpEntity<Produto>(new Produto(0L, "Produto de Testes 75",
				"Isso é um produto criado para testes", 10L, 12.99, "-", c1, usuarioTeste.get()));

		ResponseEntity<Produto> corpoResposta = testRestTemplate.withBasicAuth("root@root.com", "rootroot")
				.exchange("/produtos", HttpMethod.POST, corpoRequisicao, Produto.class);

		assertEquals(HttpStatus.CREATED, corpoResposta.getStatusCode());
	}

	@Test
	@DisplayName("Atualização de um produto")
	public void deveAtualizarProduto() {
		Produto produtoExistente = produtoRepository.save(new Produto(0L, "Produto de Testes 100",
				"Isso é um produto criado para testes", 10L, 2.99, "-", c2, usuarioTeste.get()));

		Produto produtoNovo = new Produto(produtoExistente.getId(), "Produto de Testes 200",
				"Isso é um produto criado para testes", 10L, 2.99, "-", c2, usuarioTeste.get());
		HttpEntity<Produto> corpoRequisicao = new HttpEntity<Produto>(produtoNovo);

		ResponseEntity<Produto> corpoResposta = testRestTemplate.withBasicAuth("root@root.com", "rootroot")
				.exchange("/produtos", HttpMethod.PUT, corpoRequisicao, Produto.class);

		assertEquals(HttpStatus.OK, corpoResposta.getStatusCode());
	}

	@Test
	@DisplayName("Listar todos os produtos")
	public void deveListarTodasOsProdutos() {
		produtoRepository.save(new Produto(0L, "Produto de Testes 51", "Isso é um produto criado para testes", 10L,
				12.99, "-", c3, usuarioTeste.get()));
		produtoRepository.save(new Produto(0L, "Produto de Testes 61", "Isso é um produto criado para testes", 10L,
				12.99, "-", c3, usuarioTeste.get()));

		ResponseEntity<String> resposta = testRestTemplate.withBasicAuth("root@root.com", "rootroot")
				.exchange("/produtos/listar", HttpMethod.GET, null, String.class);

		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}

	@Test
	@DisplayName("Buscar produtos por nome")
	public void deveBuscarProdutosPorNome() {
		produtoRepository.save(new Produto(0L, "Produto de Testes 71", "Isso é um produto criado para testes", 10L,
				12.99, "-", c2, usuarioTeste.get()));
		produtoRepository.save(new Produto(0L, "Produto Qualquer 81", "Isso é um produto qualquer", 10L, 12.99, "-", c2,
				usuarioTeste.get()));

		ResponseEntity<String> resposta = testRestTemplate.withBasicAuth("root@root.com", "rootroot")
				.exchange("/produtos/nome/Qualquer", HttpMethod.GET, null, String.class);

		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}

	@Test
	@DisplayName("Buscar produtos por descricao")
	public void deveBuscarProdutosPorDescricao() {
		produtoRepository.save(new Produto(0L, "Produto de Testes 91", "Isso é um produto criado para testes", 10L,
				92.99, "-", c1, usuarioTeste.get()));
		produtoRepository.save(new Produto(0L, "Produto Qualquer 101", "Isso é um produto qualquer", 10L, 92.99, "-",
				c1, usuarioTeste.get()));

		ResponseEntity<String> resposta = testRestTemplate.withBasicAuth("root@root.com", "rootroot")
				.exchange("/produtos/descricao/criado", HttpMethod.GET, null, String.class);

		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}

	@Test
	@DisplayName("Apagar produto por ID")
	public void deveApagarProdutoPorId() {
		Produto produto = produtoRepository.save(new Produto(0L, "Produto de Testes 256",
				"Isso é um produto criado para testes", 10L, 52.99, "-", c3, usuarioTeste.get()));

		ResponseEntity<String> resposta = testRestTemplate.withBasicAuth("root@root.com", "rootroot")
				.exchange("/produtos/" + produto.getId(), HttpMethod.DELETE, null, String.class);

		assertEquals(HttpStatus.NO_CONTENT, resposta.getStatusCode());
	}

	@Test
	@DisplayName("Buscar produtos por preço máximo")
	public void deveBuscarProdutosPorPrecoMax() {
		produtoRepository.save(new Produto(0L, "Produto de Testes 91", "Isso é um produto criado para testes", 10L,
				12.99, "-", c3, usuarioTeste.get()));
		produtoRepository.save(new Produto(0L, "Produto Qualquer 101", "Isso é um produto qualquer", 10L, 12.99, "-",
				c3, usuarioTeste.get()));

		ResponseEntity<String> resposta = testRestTemplate.withBasicAuth("root@root.com", "rootroot")
				.exchange("/produtos/preco/max/11", HttpMethod.GET, null, String.class);

		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}

	@Test
	@DisplayName("Buscar produtos por preço entre dois valores")
	public void deveBuscarProdutosPorPrecoEntre() {
		produtoRepository.save(new Produto(0L, "Produto de Testes 91", "Isso é um produto criado para testes", 10L,
				19.99, "-", c1, usuarioTeste.get()));
		produtoRepository.save(new Produto(0L, "Produto Qualquer 101", "Isso é um produto qualquer", 10L, 19.99, "-",
				c1, usuarioTeste.get()));

		ResponseEntity<String> resposta = testRestTemplate.withBasicAuth("root@root.com", "rootroot")
				.exchange("/produtos/preco/entre/11/21", HttpMethod.GET, null, String.class);

		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}
}
