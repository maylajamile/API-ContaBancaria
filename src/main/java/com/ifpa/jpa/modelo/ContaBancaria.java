package com.ifpa.jpa.modelo;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@Table(name = "contas")
public class ContaBancaria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nomeCliente;
	private float saldo;
	
/*	Para verificar data de última alteração
 * 
 *	@Version
 *	private Date ultimaAlteracao;
 */
	
	public ContaBancaria () {}

	public ContaBancaria(Long id, String nomeCliente, float saldo) {
		this.setId(id);
		this.setNomeCliente(nomeCliente);
		this.setSaldo(saldo);
	}
	
//	public Date getUltimaAlteracao() {
//		return ultimaAlteracao;
//	}
//
//	public void setUltimaAlteracao(Date ultimaAlteracao) {
//		this.ultimaAlteracao = ultimaAlteracao;
//	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

}
