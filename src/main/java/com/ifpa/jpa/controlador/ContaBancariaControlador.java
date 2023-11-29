package com.ifpa.jpa.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ifpa.jpa.modelo.ContaBancaria;
import com.ifpa.jpa.repositorio.ContaBancariaRepo;

@RestController
@RequestMapping(value = "/contas")
public class ContaBancariaControlador {

	@Autowired
	private ContaBancariaRepo contasRepositorio;

	@GetMapping
	public List<ContaBancaria> listar() {
		return contasRepositorio.findAll();
	}

	@PutMapping("/depositar/{id}")
	public ResponseEntity<String> depositar(@PathVariable Long id, @RequestParam float valor) {
		ContaBancaria conta = contasRepositorio.findById(id).orElse(null);

		if (conta != null) {
			conta.setSaldo(conta.getSaldo() + valor);
			contasRepositorio.save(conta);
			return ResponseEntity.ok("Depósito realizado com sucesso. Novo saldo: R$" + conta.getSaldo());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conta não encontrada.");
		}
	}

	@PutMapping("/retirar/{id}")
	public ResponseEntity<String> retirar(@PathVariable Long id, @RequestParam float valor) {
		ContaBancaria conta = contasRepositorio.findById(id).orElse(null);

		if (conta != null) {
			if (conta.getSaldo() >= valor) {
				conta.setSaldo(conta.getSaldo() - valor);
				contasRepositorio.save(conta);
				return ResponseEntity.ok("Retirada realizada com sucesso. Novo saldo: R$" + conta.getSaldo());
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body("Saldo insuficiente para realizar a retirada.");
			}
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conta não encontrada.");
		}
	}

}
