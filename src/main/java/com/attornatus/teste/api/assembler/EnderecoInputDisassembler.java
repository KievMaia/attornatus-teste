package com.attornatus.teste.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.attornatus.teste.api.model.input.EnderecoInput;
import com.attornatus.teste.domain.model.Endereco;

@Component
public class EnderecoInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Endereco toDomainObject(EnderecoInput enderecoInput) {
		return modelMapper.map(enderecoInput, Endereco.class);
	}
	
}
