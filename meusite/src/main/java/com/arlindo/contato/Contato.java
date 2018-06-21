package com.arlindo.contato;

import java.io.Serializable;

public class Contato implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4791999145625129962L;

	private long id;
	private String nome;
	private String email;
	private String telefone;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public String toString() {
		return "{Contato: nome = '" + nome + "', email = '" + email + "', telefone = '" + telefone + "'}";
	}
}
