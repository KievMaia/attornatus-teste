package com.attornatus.teste.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.attornatus.teste.api.assembler.EnderecoModelAssembler;
import com.attornatus.teste.api.model.EnderecoModel;
import com.attornatus.teste.api.model.input.EnderecoInput;
import com.attornatus.teste.domain.model.Endereco;
import com.attornatus.teste.domain.model.Pessoa;
import com.attornatus.teste.domain.repository.EnderecoRepository;
import com.attornatus.teste.domain.service.EnderecoService;
import com.attornatus.teste.domain.service.PessoaService;

@RestController
@RequestMapping("/pessoas/{pessoaId}/endereco")
public class PessoaEnderecoController {

	@Autowired
	private EnderecoService enderecoService;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private EnderecoModelAssembler enderecoModelAssembler;
	
	@PostMapping
	public EnderecoModel adicionar(@PathVariable Long pessoaId, @RequestBody @Valid EnderecoInput enderecoInput) {
		return enderecoService.adicionarEndereco(pessoaId, enderecoInput);
	}
	
	@GetMapping
	public List<EnderecoModel> listar(@PathVariable Long pessoaId) {
		Pessoa pessoa = pessoaService.buscar(pessoaId);
		
		List<Endereco> enderecoList = enderecoRepository.findTodosByPessoa(pessoa);
		List<EnderecoModel> enderecoModels = enderecoModelAssembler.toCollectionModel(enderecoList);
		
		return  enderecoModels;
	}
}
