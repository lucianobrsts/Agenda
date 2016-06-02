package br.com.agenda.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.agenda.domain.Usuario;
import br.com.agenda.factory.ConexaoFactory;

public class UsuarioDAO {

	public void salvar(Usuario usuer) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO agenda.login ");
		sql.append("(nome, senha) ");
		sql.append("VALUES (?, ?)");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, usuer.getNome());
		comando.setString(2, usuer.getSenha());

		comando.executeUpdate();
	}

	public void excluir(Usuario user) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM agenda.login ");
		sql.append("WHERE codigo = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, user.getCodigo());

		comando.executeUpdate();

	}

	public void editar(Usuario user) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE agenda.login ");
		sql.append("SET nome = ?, senha = ? ");
		sql.append("WHERE codigo  = ?");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, user.getNome());
		comando.setString(2, user.getSenha());
		comando.setLong(3, user.getCodigo());

		comando.executeUpdate();
	}

	public Usuario buscarPorCodigo(Usuario user) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, nome, senha ");
		sql.append("FROM  agenda.login ");
		sql.append("WHERE codigo = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, user.getCodigo());

		ResultSet resultado = comando.executeQuery();

		Usuario retorno = null;

		if (resultado.next()) {
			retorno = new Usuario();
			retorno.setCodigo(resultado.getLong("codigo"));
			retorno.setNome(resultado.getString("nome"));
			retorno.setSenha(resultado.getString("senha"));
		}
		return retorno;
	}

	public ArrayList<Usuario> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, nome, senha ");
		sql.append("FROM agenda.login ");
		sql.append("ORDER BY nome ASC");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		ResultSet resultado = comando.executeQuery();

		ArrayList<Usuario> lista = new ArrayList<Usuario>();

		while (resultado.next()) {
			Usuario u = new Usuario();
			u.setCodigo(resultado.getLong("codigo"));
			u.setNome(resultado.getString("nome"));
			u.setSenha(resultado.getString("senha"));

			lista.add(u);
		}

		return lista;
	}

	public ArrayList<Usuario> buscarPorNome(Usuario user) {

		return null;
	}

	public Usuario autenticar(Usuario user) throws SQLException {
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT nome, senha ");
		sql.append("FROM  agenda.login ");
		sql.append("WHERE nome = ? AND senha = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, user.getNome());
		comando.setString(2, user.getSenha());

		ResultSet resultado = comando.executeQuery();

		Usuario retorno = null;

		if (resultado.next()) {
			retorno = new Usuario();
			retorno.setNome(resultado.getString("nome"));
			retorno.setSenha(resultado.getString("senha"));
		}
		
		return retorno;
	}

	// public static void main(String[] args) {
	// Usuario u = new Usuario();
	// UsuarioDAO userdao = new UsuarioDAO();
	//
	// u.setNome("lucianobrsts");
	// u.setSenha("123456");;
	//
	// try {
	// boolean u2 = userdao.autenticar(u);
	//
	// System.out.println("Usuario: " + u2);
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// }
}
