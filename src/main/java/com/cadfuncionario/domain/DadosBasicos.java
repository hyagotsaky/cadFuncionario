package com.cadfuncionario.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

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
public class DadosBasicos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "Nome não pode ser nulo")
	private String nome;
	
	@NotNull(message = "sobrenome não pode ser nulo")
	private String sobrenome;
	
	@NotNull(message = "cpf não pode ser nulo")
	private String cpf;
	
	@NotNull(message = "contato não pode ser nulo")
	private String contato;
	
	@OneToOne(mappedBy = "dadosBasicos", fetch = FetchType.LAZY, cascade = CascadeType.ALL , orphanRemoval = true)
	@JsonIgnore
	private Funcionario funcionario;
}
