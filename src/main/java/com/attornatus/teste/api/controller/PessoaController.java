package com.attornatus.teste.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.attornatus.teste.api.assembler.PessoaInputDisassembler;
import com.attornatus.teste.api.assembler.PessoaModelAssembler;
import com.attornatus.teste.api.model.PessoaModel;
import com.attornatus.teste.api.model.input.PessoaInput;
import com.attornatus.teste.domain.model.Pessoa;
import com.attornatus.teste.domain.repository.PessoaRepository;
import com.attornatus.teste.domain.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

	@Autowired
	private PessoaInputDisassembler pessoaInputDisassembler;
	
	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private PessoaModelAssembler pessoaModelAssembler;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PessoaModel adicionar(@RequestBody @Valid PessoaInput pessoaInput) {
		Pessoa pessoa = pessoaInputDisassembler.toDomainObject(pessoaInput);
		
		pessoa = pessoaService.salvar(pessoa);
		
		return pessoaModelAssembler.toModel(pessoa);
	}
	
	@PutMapping("/{pessoaId}")
	public PessoaModel atualizar(@PathVariable Long pessoaId, @RequestBody @Valid PessoaInput pessoaInput) {
		Pessoa pessoaEncontrada =  pessoaService.buscar(pessoaId);
		
		pessoaInputDisassembler.copyToDomainObject(pessoaInput, pessoaEncontrada);
		
		Pessoa pessoaAtualizada = pessoaService.salvar(pessoaEncontrada);
		
		return pessoaModelAssembler.toModel(pessoaAtualizada);
	}
	
	@GetMapping("/{pessoaId}")
	public PessoaModel buscar(@PathVariable Long pessoaId) {
		Pessoa pessoa = pessoaService.buscar(pessoaId);
		
		return pessoaModelAssembler.toModel(pessoa);
	}
	
	@GetMapping
	public List<PessoaModel> listar(){
		List<Pessoa> pessoas = pessoaRepository.findAll();
		List<PessoaModel> pessoasModel = pessoaModelAssembler.toCollectionModel(pessoas);
		
		return pessoasModel;
	}
}
