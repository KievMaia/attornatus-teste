package com.attornatus.teste.domain.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.attornatus.teste.domain.exception.PessoaNaoEncontradaException;
import com.attornatus.teste.domain.model.Pessoa;
import com.attornatus.teste.domain.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Transactional
	public Pessoa salvar(final Pessoa pessoa) {
		return pessoaRepository.save(pessoa);
	}

	public Pessoa buscar(final Long pessoaId) {
		return pessoaRepository.findById(pessoaId)
				.orElseThrow(() -> new PessoaNaoEncontradaException(pessoaId));
	}
	
	public Pessoa buscarComEnderecoFavorito(final Long pessoaId) {
		Pessoa pessoa = this.buscar(pessoaId);
		
		Pessoa pessoaComEnderecoFavorito = pessoaRepository.findPessoaEnderecoFavorito(pessoaId);
		
		if (Objects.isNull(pessoaComEnderecoFavorito)) {
			return pessoa;
		}
		
		return pessoaComEnderecoFavorito;
	}

}
