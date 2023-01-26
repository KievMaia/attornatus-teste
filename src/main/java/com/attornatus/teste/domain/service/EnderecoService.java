package com.attornatus.teste.domain.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.attornatus.teste.api.assembler.EnderecoInputDisassembler;
import com.attornatus.teste.api.assembler.EnderecoModelAssembler;
import com.attornatus.teste.api.model.EnderecoModel;
import com.attornatus.teste.api.model.input.EnderecoInput;
import com.attornatus.teste.domain.exception.EnderecoNaoEncontradoException;
import com.attornatus.teste.domain.model.Endereco;
import com.attornatus.teste.domain.model.Pessoa;
import com.attornatus.teste.domain.repository.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private EnderecoInputDisassembler enderecoInputDisassembler;
	
	@Autowired
	private EnderecoModelAssembler enderecoModelAssembler;
	
	@Autowired
	private PessoaService pessoaService;
	
	public Endereco buscar(Long enderecoId) {
		return enderecoRepository.findById(enderecoId)
				.orElseThrow(() -> new EnderecoNaoEncontradoException(enderecoId));
	}
	
	@Transactional
	public Endereco salvar(Endereco endereco) {
		return enderecoRepository.save(endereco);
	}
	
	@Transactional
	public EnderecoModel adicionarEndereco(final Long pessoaId, @Valid final EnderecoInput enderecoInput) {
		Pessoa pessoa = pessoaService.buscar(pessoaId);
		Endereco endereco = enderecoInputDisassembler.toDomainObject(enderecoInput);
		
		
		endereco.setPessoa(pessoa);
		this.salvar(endereco);
		
		return enderecoModelAssembler.toModel(endereco);
	}

	@Transactional
	public void favoritarEndereco(Long enderecoId, Pessoa pessoa) {
		Endereco endereco = enderecoRepository.findByIdAndPessoaId(enderecoId, pessoa.getId())
				.orElseThrow(() -> new EnderecoNaoEncontradoException(enderecoId, pessoa.getId()));
		
		pessoa.favoritarEndereco(endereco);
	}
}
