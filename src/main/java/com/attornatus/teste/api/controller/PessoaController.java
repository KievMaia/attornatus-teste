package com.attornatus.teste.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

	@GetMapping
	public String HelloWorld() {
		return "Hello World!";
	}
}
