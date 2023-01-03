package com.attornatus.teste.api.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoInput {

	@NotBlank
	private String logradouro;

	@NotBlank
	private String cep;

	@NotBlank
	private String numero;

	@NotBlank
	private String cidade;
}
