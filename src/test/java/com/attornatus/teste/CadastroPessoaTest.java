package com.attornatus.teste;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import com.attornatus.teste.domain.model.Pessoa;
import com.attornatus.teste.domain.service.PessoaService;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
class CadastroPessoaTest {

	@LocalServerPort
	private int port;
	
	@BeforeEach
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/pessoas";
	}
	
	@Autowired
	private PessoaService service;
	
	@Test
	public void deveRetornarStatus201_QuandoCadastrarPessoa() {
		given()
			.body("{ \"nome\": \"João\", \"dataNascimento\": \"1981-12-13\"}")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	} 
	
	@Test
	public void deveRetornarStatus200_QuandoAtualizarPessoa() {
		given()
			.pathParam("pessoaId", 1)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body("{ \"nome\": \"João\", \"dataNascimento\": \"1981-12-13\"}")
		.when()
			.put("/{pessoaId}")
		.then()
			.statusCode(HttpStatus.OK.value());
	} 
	
	@Test
	public void deveRetornarRespostaStatus404_QuandoAtualizaPessoaInexistente() {
		given()
			.pathParam("pessoaId", 10)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body("{ \"nome\": \"João\", \"dataNascimento\": \"1981-12-13\"}")
		.when()
			.put("/{pessoaId}")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value())
			.body("message", equalTo("Não existe um cadastro de pessoa com o codigo 10"));
	}
	
	@Test
	public void deveRetornarStatus200_QuandoBuscaPessoa() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		given()
			.pathParam("pessoaId", 1)
			.contentType(ContentType.JSON)
		.when()
			.get("/{pessoaId}")
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("nome", equalTo("Kiev Maia"));
	} 
	
	@Test
	public void deveRetornarRespostaStatus404_QuandoBuscaPessoaInexistente() {
		given()
			.pathParam("pessoaId", 20)
			.accept(ContentType.JSON)
		.when()
			.get("/{pessoaId}")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value())
			.body("message", equalTo("Não existe um cadastro de pessoa com o codigo 20"));
	}
	
	@Test
	public void deveRetornarStatus200_QuandoListarPessoas() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		given()
			.contentType(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
	} 
	
	@Test
	public void deveRetornarStatus200_QuandoListarEnderecoPessoas() {
		given()
			.pathParam("pessoaId", 1)
			.contentType(ContentType.JSON)
		.when()
			.get("{pessoaId}/endereco")
		.then()
			.statusCode(HttpStatus.OK.value());
	} 
	
	@Test
	public void deveRetornarRespostaStatus404_QuandoListarEnderecoPessoaInexistente() {
		given()
			.pathParam("pessoaId", 10)
			.contentType(ContentType.JSON)
		.when()
		.get("{pessoaId}/endereco")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value())
			.body("message", equalTo("Não existe um cadastro de pessoa com o codigo 10"));
	}
	
	@Test
	public void deveRetornarStatus204_QuandoAdicionarEnderecoPrincipalPessoa() {
		given()
			.pathParam("pessoaId", 1)
			.pathParam("enderecoId", 1)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.put("{pessoaId}/endereco/{enderecoId}")
		.then()
			.statusCode(HttpStatus.NO_CONTENT.value());
	} 
	
	@Test
	public void deveRetornarRespostaStatus404_QuandoAdicionarEnderecoPrincipalPessoaInexistente() {
		given()
			.pathParam("pessoaId", 10)
			.pathParam("enderecoId", 1)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.put("{pessoaId}/endereco/{enderecoId}")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value())
			.body("message", equalTo("Não existe um cadastro de pessoa com o codigo 10"));
	}
	
	@Test
	public void deveRetornarRespostaStatus404_QuandoAdicionarEnderecoPrincipalEnderecoInexistente() {
		given()
			.pathParam("pessoaId", 1)
			.pathParam("enderecoId", 30)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.put("{pessoaId}/endereco/{enderecoId}")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value())
			.body("message", equalTo("Não existe um cadastro de endereço com o codigo 30 para a pessoa com código 1"));
	}
	
	@Test
	public void deveRetornarStatus201_QuandoCadastrarEnderecoPessoa() {
		given()
			.pathParam("pessoaId", 1)
			.body("{ \"logradouro\": \"João Nilo Morfim\", \"cep\": \"88133555\", \"numero\": \"20\", \"cidade\": \"São José\"}")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post("{pessoaId}/endereco")
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}
	
	@Test
	public void deveRetornarStatus204_QuandoCadastrarEnderecoPessoaInexistente() {
		given()
			.pathParam("pessoaId", 10)
			.body("{ \"logradouro\": \"João Nilo Morfim\", \"cep\": \"88133555\", \"numero\": \"20\", \"cidade\": \"São José\"}")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post("{pessoaId}/endereco")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value())
			.body("message", equalTo("Não existe um cadastro de pessoa com o codigo 10"));
	} 
	
	@Test
	public void deveFalhar_QuandoCadastrarPessoaSemNome() {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome(null);
		
		DataIntegrityViolationException erroEsperado =
			      Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
			         service.salvar(pessoa);
			      });
			   
			   assertThat(erroEsperado).isNotNull();
	}
	
	@Test
	public void deveFalhar_QuandoCadastrarPessoaSemDataNascimento() {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("José");
		pessoa.setDataNascimento(null);
		
		DataIntegrityViolationException erroEsperado =
			      Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
			         service.salvar(pessoa);
			      });
			   
			   assertThat(erroEsperado).isNotNull();
	}

}
