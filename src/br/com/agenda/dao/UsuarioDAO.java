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
		sql.append("INSERT INTO agenda.usuario ");
		sql.append("(nome, senha) ");
		sql.append("VALUES (?, ?) ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, usuer.getNome());
		comando.setString(2, usuer.getSenha());

		comando.executeUpdate();
	}

	public void excluir(Usuario user) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM agenda.login ");
		sql.append("WHERE idusuario = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, user.getIdUsuario());

		comando.executeUpdate();

	}

	public void editar(Usuario user) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE agenda.usuario ");
		sql.append("SET nome = ?, senha = ? ");
		sql.append("WHERE idusuario  = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, user.getNome());
		comando.setString(2, user.getSenha());
		comando.setLong(3, user.getIdUsuario());

		comando.executeUpdate();
	}

	public Usuario buscarPorCodigo(Usuario user) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT idusuario, nome, senha ");
		sql.append("FROM  agenda.usuario ");
		sql.append("WHERE idusuario = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, user.getIdUsuario());

		ResultSet resultado = comando.executeQuery();

		Usuario retorno = null;

		if (resultado.next()) {
			retorno = new Usuario();
			retorno.setIdUsuario(resultado.getLong("idusuario"));
			retorno.setNome(resultado.getString("nome"));
			retorno.setSenha(resultado.getString("senha"));
		}
		return retorno;
	}

	public ArrayList<Usuario> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT idusuario, nome, senha ");
		sql.append("FROM agenda.usuario ");
		sql.append("ORDER BY nome ASC ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		ResultSet resultado = comando.executeQuery();

		ArrayList<Usuario> lista = new ArrayList<Usuario>();

		while (resultado.next()) {
			Usuario u = new Usuario();
			u.setIdUsuario(resultado.getLong("idusuario"));
			u.setNome(resultado.getString("nome"));
			u.setSenha(resultado.getString("senha"));

			lista.add(u);
		}

		return lista;
	}

	public ArrayList<Usuario> buscarPorNome(Usuario user) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT idusuario, nome, senha ");
		sql.append("FROM agenda.usuario ");
		sql.append("WHERE nome LIKE ?");
		sql.append("ORDER BY nome ASC ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, "%" + user.getNome() + "%");

		ResultSet resultado = comando.executeQuery();

		ArrayList<Usuario> lista = new ArrayList<Usuario>();

		while (resultado.next()) {
			Usuario item = new Usuario();
			item.setIdUsuario(resultado.getLong("idusuario"));
			item.setNome(resultado.getString("nome"));
			item.setSenha(resultado.getString("senha"));

			lista.add(item);
		}
		return lista;
	}

	public Usuario autenticar(Usuario user) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT nome, senha ");
		sql.append("FROM  agenda.usuario ");
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

}
