package com.attornatus.teste.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.attornatus.teste.domain.model.Endereco;
import com.attornatus.teste.domain.model.Pessoa;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long>{

	List<Endereco> findTodosByPessoa(Pessoa pessoa);

}
