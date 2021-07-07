package com.cadfuncionario.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RegistroDePonto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="dd-MM-yyyy HH:mm:ss", locale = "pt-BR", timezone="America/Sao_Paulo")
	private Date entrada;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="dd-MM-yyyy HH:mm:ss", locale = "pt-BR", timezone="America/Sao_Paulo")
	private Date saida;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FUNCIONARIO_ID")
	@JsonIgnore
	private Funcionario funcionario;

}
