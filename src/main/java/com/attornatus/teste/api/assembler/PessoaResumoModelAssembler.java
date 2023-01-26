package com.attornatus.teste.api.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.attornatus.teste.api.model.PessoaResumoModel;
import com.attornatus.teste.domain.model.Pessoa;

@Component
public class PessoaResumoModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public PessoaResumoModel toModel(Pessoa pessoa) {
		return modelMapper.map(pessoa, PessoaResumoModel.class);
	}
	
	public List<PessoaResumoModel> toCollectionModel(Collection<Pessoa> pessoas) {
		return pessoas.stream()
				.map(pessoa -> toModel(pessoa))
				.collect(Collectors.toList());
	}
}
