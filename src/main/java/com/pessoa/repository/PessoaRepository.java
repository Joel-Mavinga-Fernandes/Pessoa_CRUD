package com.pessoa.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pessoa.model.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository <Pessoa, Long> {
	public List<Pessoa> findAllByNomeContainingIgnoreCase (String nome);

}
