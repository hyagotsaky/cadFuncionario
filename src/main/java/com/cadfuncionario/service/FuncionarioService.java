package com.cadfuncionario.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.cadfuncionario.domain.DadosBasicos;
import com.cadfuncionario.domain.Funcionario;
import com.cadfuncionario.domain.RegistroDePonto;
import com.cadfuncionario.repository.DadosBasicosRepository;
import com.cadfuncionario.repository.FuncionarioRepository;
import com.cadfuncionario.repository.RegristroDePontoRepository;
import com.cadfuncionario.service.exceptions.FuncionarioNaoEncontradoException;

@Service
public class FuncionarioService {
	@Autowired
	FuncionarioRepository funcionarioRepository;
	
	@Autowired
	RegristroDePontoRepository regristroDePontoRepository;
	
	@Autowired
	DadosBasicosRepository dadosBasicosRepository;
	
	public List<Funcionario> listar(){
		return funcionarioRepository.findAll();
		
	}
	
	public Funcionario buscar(Long id) {
		Funcionario funcionario = funcionarioRepository.findById(id)
				.orElse(null);
		
		if(funcionario == null) {
			throw new FuncionarioNaoEncontradoException("funcionario nao encontrado");
		}
		return funcionario;
	}
	
	@Transactional
	public Funcionario salvar(Funcionario funcionario) {
		funcionario.setId(null);
		 funcionarioRepository.save(funcionario);
		  dadosBasicosRepository.save(funcionario.getDadosBasicos());
		  return funcionario;
	}
	
	public void deletar(Long id) {
		try {		
			funcionarioRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new FuncionarioNaoEncontradoException("funcionario nao encontrado");
		}
	}
	
	public void atualizar(Funcionario funcionario) {
		verificarExistenciaFuncionario(funcionario);
		funcionarioRepository.save(funcionario);
	}
	
	private void verificarExistenciaFuncionario(Funcionario funcionario) {
		buscar(funcionario.getId());
	}
	
	public RegistroDePonto salvarRegristroDePonto(Long funcionarioId,RegistroDePonto registroDePonto) {
		Funcionario funcionario = buscar(funcionarioId);
		registroDePonto.setFuncionario(funcionario);
		
		return regristroDePontoRepository.save(registroDePonto);
	}
	
	public List<RegistroDePonto> listarRegriDePontos(Long funcionarioId){
		Funcionario funcionario = buscar(funcionarioId);
		return funcionario.getRegistroDePonto();
	}
	
	public DadosBasicos salvarDadosBasicos(Long funcionarioId,DadosBasicos dadosBasicos) {
		Funcionario funcionario = buscar(funcionarioId);
		funcionario.setDadosBasicos(dadosBasicos);
		
		return dadosBasicosRepository.save(dadosBasicos);
	}
		
}
