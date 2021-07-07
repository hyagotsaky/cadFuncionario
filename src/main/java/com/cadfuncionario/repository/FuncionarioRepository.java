package com.cadfuncionario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cadfuncionario.domain.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

}
