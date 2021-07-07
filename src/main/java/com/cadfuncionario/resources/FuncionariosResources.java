package com.cadfuncionario.resources;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ResourceProperties.Cache.Cachecontrol;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cadfuncionario.domain.DadosBasicos;
import com.cadfuncionario.domain.Funcionario;
import com.cadfuncionario.domain.RegistroDePonto;
import com.cadfuncionario.service.FuncionarioService;

@Controller
@RequestMapping("/funcionarios")
public class FuncionariosResources {

	@Autowired
	FuncionarioService funcionarioService;

	@GetMapping("/lista")
	@ResponseBody
	public ResponseEntity<List<Funcionario>> listar() {
//		Date d1 = new Date(02/01/2010);
//		Date d2 = new Date(03/01/2010);
//		Date d3 = new Date(04/01/2010);
//		Date d4 = new Date(05/01/2010);
//		
//		RegistroDePonto r1 = new RegistroDePonto(1, d1, d2);
//		RegistroDePonto r2 = new RegistroDePonto(2, d3, d4);
//		
//		List <RegistroDePonto> registros = new ArrayList<>();
//		registros.add(r1);
//		registros.add(r2);
//		DadosBasicos d = new DadosBasicos("teste","fulzs","222.666.222-50","9898-9898");
//		
//		Funcionario f1 = new Funcionario(1L,d, "analista tester" ,"descricao do cargo",registros);
//
//		
//		List<Funcionario> funcionarios = new ArrayList<>();
//		funcionarios.add(f1);
//		funcionarios.add(f2);
//		return funcionarios;

		return ResponseEntity.ok(funcionarioService.listar());
	}

	@PostMapping()
	@ResponseBody
	public ResponseEntity<Void> salvar(@Valid @RequestBody Funcionario funcionario) {
		funcionarioService.salvar(funcionario);
		return ResponseEntity.created(null).build();
	}

	@GetMapping("/lista/{id}")
	@ResponseBody
	public ResponseEntity<Funcionario> buscar(@PathVariable Long id) {

		Funcionario funcionario = funcionarioService.buscar(id);
		return ResponseEntity.ok(funcionario);
	}

	@DeleteMapping("/{id}")
	@ResponseBody
	public ResponseEntity<Void> deletar(@PathVariable Long id) {

		funcionarioService.deletar(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	@ResponseBody
	public ResponseEntity<Void> atualizar(@RequestBody Funcionario funcionario, @PathVariable Long id) {
		funcionario.setId(id);

		funcionarioService.atualizar(funcionario);
		return ResponseEntity.noContent().build();
	}
	
	
	@PostMapping("/{funcionarioId}/registroDePonto")
	@ResponseBody
	public ResponseEntity<Void> adicinaRegristroDePonto(@PathVariable("funcionarioId") Long funcionarioId  ,
			@RequestBody RegistroDePonto registroDePonto) {
		 funcionarioService.salvarRegristroDePonto(funcionarioId, registroDePonto);
		 return ResponseEntity.created(null).build();
	}
	
	@GetMapping("/{funcionarioId}/registroDePonto")
	public ResponseEntity<List<RegistroDePonto>> listarRegistroDePonto(
			@PathVariable("funcionarioId") Long FuncionarioId){
		
		List<RegistroDePonto> registroDePontos = funcionarioService.listarRegriDePontos(FuncionarioId);
		
		return ResponseEntity.status(HttpStatus.OK).body(registroDePontos);
	}
	
	
	@PostMapping("/{funcionarioId}/dadosBasicos")
	@ResponseBody
	public ResponseEntity<Void> adicinaDadosBasicos(@PathVariable("funcionarioId") Long funcionarioId  ,
			@RequestBody DadosBasicos dadosBasicos) {
		 funcionarioService.salvarDadosBasicos(funcionarioId,dadosBasicos);
		 return ResponseEntity.created(null).build();
	}
	
}
