package com.attornatus.teste.api.model.input;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaInput {

	@NotBlank
	private String nome;

	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;
}
