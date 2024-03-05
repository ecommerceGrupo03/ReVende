package com.reVende.projeto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.reVende.projeto.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
	
	public List<Categoria> findAllByDescricaoContainingIgnoreCase(@Param("descricao") String descricao);
	
	public List <Categoria> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);

}
