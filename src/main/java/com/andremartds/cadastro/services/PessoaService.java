package com.andremartds.cadastro.services;

import com.andremartds.cadastro.model.Pessoa;
import com.andremartds.cadastro.repository.PessoaRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository rep;

	public Pessoa atualizaPessoa(Long codigo, Pessoa pessoa) {
		Pessoa pessoaSalva = this.encontraPessoaPorCodigo(codigo);
		BeanUtils.copyProperties(pessoa, pessoaSalva, "codigo");
		return this.rep.save(pessoaSalva);
	}

	public Pessoa atualizaPessoaAtivaInativa(Long codigo, Boolean ativa) {
		Pessoa pessoaSalva = this.encontraPessoaPorCodigo(codigo);
		pessoaSalva.setAtivo(ativa);
		return this.rep.save(pessoaSalva);
	}
	
	private Pessoa encontraPessoaPorCodigo(Long codigo) {
		Pessoa pessoaSalva = this.rep.findById(codigo).orElseThrow(() -> new EmptyResultDataAccessException(1));
		return pessoaSalva;
	}

}
