package com.attornatus.teste.api.model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaModel {

	private Long id;
	
	private String nome;
	
	private LocalDate dataNascimento;
	
	private EnderecoModel endereco;
}
