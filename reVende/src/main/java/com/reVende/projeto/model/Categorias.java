package com.reVende.projeto.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_categorias")
public class Categorias {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message = "Atributo nome é obrigatório")
	@Size(min = 5, max = 255, message = "Minimo 5 e no máximo 255 caracteres para o atributo nome")
	private String nome;
	
	@Size(max = 255, message = "Máximo de 255 caracteres para descrição")
	private String descricao;
}
