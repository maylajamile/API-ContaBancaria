package com.ifpa.jpa.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public String depositar(@PathVariable Long id, @RequestParam float valor) {
		ContaBancaria conta = contasRepositorio.findById(id).orElse(null);

		if (conta != null) {
			conta.setSaldo(conta.getSaldo() + valor);
			contasRepositorio.save(conta);
			return "Depósito realizado com sucesso. Novo saldo: R$" + conta.getSaldo();
		} else {
			return "Conta não encontrada.";
		}
	}

	@PutMapping("/retirar/{id}")
	public String retirar(@PathVariable Long id, @RequestParam float valor) {
		ContaBancaria conta = contasRepositorio.findById(id).orElse(null);

		if (conta != null) {
			conta.setSaldo(conta.getSaldo() - valor);
			contasRepositorio.save(conta);
			return "Retirada realizada com sucesso. Novo saldo: R$" + conta.getSaldo();
		} else {
			return "Conta não encontrada.";
		}
	}

}
