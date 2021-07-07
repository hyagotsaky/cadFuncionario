package com.cadfuncionario.service.exceptions;

public class FuncionarioNaoEncontradoException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public FuncionarioNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public FuncionarioNaoEncontradoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
