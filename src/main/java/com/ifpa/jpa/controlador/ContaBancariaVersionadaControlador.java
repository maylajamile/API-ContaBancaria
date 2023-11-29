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

import com.ifpa.jpa.modelo.ContaBancariaVersionada;
import com.ifpa.jpa.repositorio.ContaBancariaVersionadaRepo;

@RestController
@RequestMapping(value = "/contas/versionadas")
public class ContaBancariaVersionadaControlador {

	@Autowired
	private ContaBancariaVersionadaRepo contasVersionadasRepositorio;

	@GetMapping
	public List<ContaBancariaVersionada> listar() {
		return contasVersionadasRepositorio.findAll();
	}

	@PutMapping("/depositar/{id}")
	public ResponseEntity<String> depositar(@PathVariable Long id, @RequestParam float valor) {
		ContaBancariaVersionada conta = contasVersionadasRepositorio.findById(id).orElse(null);

		if (conta != null) {
			conta.setSaldo(conta.getSaldo() + valor);
			try {
				contasVersionadasRepositorio.save(conta);
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body("Não foi possível realizar essa requisição: " + e.getMessage());
			}
			return ResponseEntity.ok("Depósito realizado com sucesso. Novo saldo: R$" + conta.getSaldo());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conta não encontrada.");
		}
	}

	@PutMapping("/retirar/{id}")
	public ResponseEntity<String> retirar(@PathVariable Long id, @RequestParam float valor) {
		ContaBancariaVersionada conta = contasVersionadasRepositorio.findById(id).orElse(null);

		if (conta != null) {
			if (conta.getSaldo() >= valor) {
				conta.setSaldo(conta.getSaldo() - valor);
				try {
					contasVersionadasRepositorio.save(conta);
				} catch (Exception e) {
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
							.body("Não foi possível realizar essa requisição: " + e.getMessage());
				}
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
