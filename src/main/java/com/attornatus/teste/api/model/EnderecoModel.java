package com.attornatus.teste.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoModel {

	private Long id;
	
	private String logradouro;

	private String cep;

	private String numero;

	private String cidade;
}
