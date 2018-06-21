package com.arlindo.contato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ContatoDAO {

	private static final String INSERT_SQL = "INSERT INTO CONTATO (NOME, EMAIL, TELEFONE) VALUES (?, ?, ?)";
	private Connection conexao;

	public ContatoDAO(Connection conexao) {
		this.conexao = conexao;
	}

	public void inserir(Contato contato) {
		PreparedStatement ps = null;
		try {
			ps = conexao.prepareStatement(INSERT_SQL);
			ps.setString(1, contato.getNome());
			ps.setString(2, contato.getEmail());
			ps.setString(3, contato.getTelefone());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
