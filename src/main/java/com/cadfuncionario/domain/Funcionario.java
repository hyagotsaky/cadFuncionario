package com.cadfuncionario.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Funcionario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@OneToOne
	@JoinColumn(name="dadosBasicos")
	private DadosBasicos dadosBasicos;
	
	private String funcao;
	
	private String descricao;
	
	@OneToMany(mappedBy = "funcionario")
	private List<RegistroDePonto> registroDePonto;
	
	private Boolean ativo = true;
	
}
