package com.pessoa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pessoa.model.Pessoa;
import com.pessoa.repository.PessoaRepository;

@RestController
@RequestMapping("/pessoa")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class PessoaController {

	@Autowired
	private PessoaRepository Repository;
	private Pessoa pessoa;
	
	@GetMapping
	public ResponseEntity<List<Pessoa>>GetAll(){
		return ResponseEntity.ok(Repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pessoa> GetById(@PathVariable long id ){
		return Repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Pessoa>> GetByNome(@PathVariable String nome ){
		return ResponseEntity.ok(Repository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	@PostMapping
	public ResponseEntity<Pessoa> post (@RequestBody Pessoa pessoa ){
		return ResponseEntity.status(HttpStatus.CREATED).body(Repository.save(pessoa));
	}
	
	@PutMapping
	public ResponseEntity<Pessoa> put (@RequestBody Pessoa pessoa ){
		return ResponseEntity.status(HttpStatus.OK).body(Repository.save(pessoa));
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		Repository.deleteById(id);
	}
	
}
