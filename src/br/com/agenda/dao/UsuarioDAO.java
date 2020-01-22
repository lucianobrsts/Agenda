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
		sql.append("DELETE FROM agenda.usuario ");
		sql.append("WHERE idusuario = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, user.getCodigo());

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
		comando.setLong(3, user.getCodigo());

		comando.executeUpdate();
	}

	public Usuario buscarPorCodigo(Usuario user) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT idusuario, nome, senha ");
		sql.append("FROM  agenda.usuario ");
		sql.append("WHERE idusuario = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, user.getCodigo());

		ResultSet resultado = comando.executeQuery();

		Usuario retorno = null;

		if (resultado.next()) {
			retorno = new Usuario();
			retorno.setCodigo(resultado.getLong("idusuario"));
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
			u.setCodigo(resultado.getLong("idusuario"));
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
			item.setCodigo(resultado.getLong("idusuario"));
			item.setNome(resultado.getString("nome"));
			item.setSenha(resultado.getString("senha"));

			lista.add(item);
		}
		return lista;
	}

	public Usuario autenticar(String nome, String senha) throws SQLException {
		Usuario usuario = null;
		String sql = "SELECT * FROM usuario WHERE nome = ? AND senha = ? ";

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql);

		comando.setObject(1, nome);
		comando.setObject(2, senha);

		ResultSet resultado = comando.executeQuery();

		if (resultado.next()) {
			usuario = new Usuario();
			usuario.setNome(resultado.getString("nome"));
			usuario.setSenha(resultado.getString("senha"));
		}

		return usuario;
	}
}
