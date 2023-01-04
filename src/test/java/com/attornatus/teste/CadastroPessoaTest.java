package com.attornatus.teste;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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
			.pathParam("pessoaId", 1L)
			.body("{ \"nome\": \"João\", \"dataNascimento\": \"1981-12-13\"}")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.put()
		.then()
			.statusCode(HttpStatus.OK.value());
	} 

}
