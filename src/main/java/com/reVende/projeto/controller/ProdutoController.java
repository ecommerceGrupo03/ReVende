package com.reVende.projeto.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.reVende.projeto.model.Produto;
import com.reVende.projeto.repository.CategoriaRepository;
import com.reVende.projeto.repository.ProdutoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@GetMapping("/listar")
	public ResponseEntity<List<Produto>> getAll() {
		return ResponseEntity.ok(produtoRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Produto> getById(@PathVariable Long id) {
		return produtoRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<List<Produto>> getAllByDescricao(@PathVariable String descricao) {
		return ResponseEntity.ok(produtoRepository.findAllByDescricaoContainingIgnoreCase(descricao));
	}

	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produto>> getAllByNome(@PathVariable String nome) {
		return ResponseEntity.ok(produtoRepository.findAllByNomeContainingIgnoreCase(nome));
	}

	@GetMapping("/preco/max/{preco}")
	public ResponseEntity<List<Produto>> getByPrecoMenorOuIgual(@PathVariable Double preco) {
		return ResponseEntity.ok(produtoRepository.findByPrecoLessThanEqual(preco));
	}

	@GetMapping("/preco/entre/{precoMin}/{precoMax}")
	public ResponseEntity<List<Produto>> getByPrecoEntre(@PathVariable Double precoMin, @PathVariable Double precoMax) {
		return ResponseEntity.ok(produtoRepository.findByPrecoBetween(precoMin, precoMax));
	}
	
	@PostMapping
	public ResponseEntity<Produto> post(@Valid @RequestBody Produto produto) {
		if (categoriaRepository.existsById(produto.getCategoria().getId()))
			return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria não existe!", null);
	}

	@PutMapping
	public ResponseEntity<Produto> put(@Valid @RequestBody Produto produto) {
		if (produtoRepository.existsById(produto.getId())) {
			if (categoriaRepository.existsById(produto.getCategoria().getId())) {
				return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(produto));
			}

			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria não existe!", null);
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Produto> produto = produtoRepository.findById(id);

		if (produto.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);

		produtoRepository.deleteById(id);
	}

}
