package com.attornatus.teste;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

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
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body("{ \"nome\": \"João\", \"dataNascimento\": \"1981-12-13\"}")
		.when()
			.put("/1")
		.then()
			.statusCode(HttpStatus.OK.value());
	} 
	
	@Test
	public void deveRetornarStatus200_QuandoBuscaPessoa() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		given()
			.contentType(ContentType.JSON)
		.when()
			.get("/1")
		.then()
			.statusCode(HttpStatus.OK.value());
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
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		given()
			.contentType(ContentType.JSON)
		.when()
			.get("1/endereco")
		.then()
			.statusCode(HttpStatus.OK.value());
	} 
	
	@Test
	public void deveRetornarStatus204_QuandoAdicionarEnderecoPrincipalPessoa() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.put("1/endereco/1")
		.then()
			.statusCode(HttpStatus.NO_CONTENT.value());
	} 

}
