package com.attornatus.teste.api.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.attornatus.teste.api.model.EnderecoModel;
import com.attornatus.teste.domain.model.Endereco;

@Component
public class EnderecoModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public EnderecoModel toModel(Endereco endereco) {
		return modelMapper.map(endereco, EnderecoModel.class);
	}
	
	public List<EnderecoModel> toCollectionModel(Collection<Endereco> enderecos) {
		return enderecos.stream()
				.map(endereco -> toModel(endereco))
				.collect(Collectors.toList());
	}
}
