package com.cadfuncionario.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cadfuncionario.domain.DetalhesErro;
import com.cadfuncionario.service.exceptions.FuncionarioNaoEncontradoException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(FuncionarioNaoEncontradoException.class)
	public ResponseEntity<DetalhesErro> handlerFuncionarioNaoEncontradoExeption
	(FuncionarioNaoEncontradoException e , HttpServletRequest request){
		
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(404l);
		erro.setTitulo("O funcionario não foi encontrado");
	
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}

}
