package com.andremartds.cadastro.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.andremartds.cadastro.model.Pessoa;
import com.andremartds.cadastro.repository.PessoaRepository;
import com.andremartds.cadastro.services.PessoaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

	@Autowired
	PessoaRepository pessoaRepository;

	@Autowired
	PessoaService pessoaService;

	@GetMapping
	public List<Pessoa> todosUsuarios() {
		return pessoaRepository.findAll();
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<Pessoa> buscarPorCodigo(@PathVariable Long codigo) {
		return this.pessoaRepository.findById(codigo).map(pessoa -> ResponseEntity.ok(pessoa))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<Pessoa> addPessoa(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response) {
		Pessoa pessoaSalva = pessoaRepository.save(pessoa);
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removePessoa(@PathVariable Long codigo) {
		pessoaRepository.deleteById(codigo);
	}

	@PutMapping("/{codigo}")
	public Pessoa atualizarPessoa(@PathVariable Long codigo, @Valid @RequestBody Pessoa pessoa,
			HttpServletResponse response) {
		Pessoa pessoaAtualizada = this.pessoaService.atualizaPessoa(codigo, pessoa);
		return pessoaAtualizada;
	}

	@PatchMapping("/{codigo}/ativo")
	public Pessoa atualizaPessoaAtivaOuInativa(@PathVariable Long codigo,
			@Valid @RequestBody Boolean ativo, HttpServletResponse response) {
		Pessoa pessoaAtualizada = this.pessoaService.atualizaPessoaAtivaInativa(codigo, ativo);
		return pessoaAtualizada;
	}

}
