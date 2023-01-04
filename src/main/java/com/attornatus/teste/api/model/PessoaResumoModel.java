package com.attornatus.teste.api.model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaResumoModel {

	private Long id;
	
	private String nome;
	
	private LocalDate dataNascimento;
}
