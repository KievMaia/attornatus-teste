package com.attornatus.teste.domain.exception;

public class EnderecoNaoEncontradoException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;

	public EnderecoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public EnderecoNaoEncontradoException(Long enderecoId) {
		this(String.format("Não existe um cadastro de endereço com o codigo %d", enderecoId));
	}
	
	public EnderecoNaoEncontradoException(Long enderecoId, Long pessoaId) {
		this(String.format("Não existe um cadastro de endereço com o codigo %d para a pessoa com código %d", enderecoId, pessoaId));
	}
}
