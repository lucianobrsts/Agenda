package br.com.agenda.test;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Ignore;
import org.junit.Test;

import br.com.agenda.factory.ConexaoFactory;

public class ConexaoFactoryTest {

	@SuppressWarnings("unused")
	@Test
	@Ignore
	public void testaConexao() {
		try {
			Connection conexao = ConexaoFactory.conectar();
			// System.out.println("Conexao realizada com sucesso...");
		} catch (SQLException ex) {
			ex.printStackTrace();
			// System.out.println("Impossível estabelecer a conexao...");
		}
	}
}
