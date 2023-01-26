package com.attornatus.teste.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.attornatus.teste.domain.model.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

	@Query(value = "SELECT * FROM Pessoa p JOIN Endereco e on p.endereco_id = e.id WHERE p.id = :pessoaId", nativeQuery = true)
	Pessoa findPessoaEnderecoFavorito(Long pessoaId);
}
