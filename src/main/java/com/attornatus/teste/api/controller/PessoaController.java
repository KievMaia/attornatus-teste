package com.attornatus.teste.api.controller;

import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.attornatus.teste.api.assembler.PessoaResumoModelAssembler;
import com.attornatus.teste.api.model.PessoaResumoModel;
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
	
	@Autowired
	private PessoaResumoModelAssembler pessoaResumoModelAssembler;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PessoaResumoModel adicionar(@RequestBody @Valid PessoaInput pessoaInput) {
		Pessoa pessoa = pessoaInputDisassembler.toDomainObject(pessoaInput);
		
		pessoa = pessoaService.salvar(pessoa);
		
		return pessoaResumoModelAssembler.toModel(pessoa);
	}
	
	@PutMapping("/{pessoaId}")
	public PessoaResumoModel atualizar(@PathVariable Long pessoaId, @RequestBody @Valid PessoaInput pessoaInput) {
		Pessoa pessoaEncontrada =  pessoaService.buscar(pessoaId);
		
		pessoaInputDisassembler.copyToDomainObject(pessoaInput, pessoaEncontrada);
		
		Pessoa pessoaAtualizada = pessoaService.salvar(pessoaEncontrada);
		
		return pessoaResumoModelAssembler.toModel(pessoaAtualizada);
	}
	
	@GetMapping("/{pessoaId}")
	public ResponseEntity<?> buscar(@PathVariable Long pessoaId) {
		Pessoa pessoa = pessoaService.buscarComEnderecoFavorito(pessoaId);
		
		if (Objects.isNull(pessoa.getEndereco())) {
			return ResponseEntity.ok(pessoaResumoModelAssembler.toModel(pessoa));
		}
		
		return ResponseEntity.ok(pessoaModelAssembler.toModel(pessoa));
	}
	
	@GetMapping
	public List<PessoaResumoModel> listar(){
		List<Pessoa> pessoas = pessoaRepository.findAll();
		List<PessoaResumoModel> pessoasResumoModel = pessoaResumoModelAssembler.toCollectionModel(pessoas);
		
		return pessoasResumoModel;
	}
}
