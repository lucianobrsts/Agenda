package br.com.agenda.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.agenda.domain.Contato;
import br.com.agenda.factory.ConexaoFactory;

public class ContatoDAO {

	public void salvar(Contato c) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO contato ");
		sql.append("(nome, endereco, telefone, celular, profissao, email, empresa, datanascimento, datacadastro, observacao) ");
		sql.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setString(1, c.getNome());
		comando.setString(2, c.getEndereco());
		comando.setString(3, c.getTelefone());
		comando.setString(4, c.getCelular());
		comando.setString(5, c.getProfissao());
		comando.setString(6, c.getEmail());
		comando.setString(7, c.getEmpresa());
		comando.setString(8, c.getDataNascimento());
		comando.setString(9, c.getDataCadastro());
		comando.setString(10, c.getObservacao());

		comando.executeUpdate();
	}

	public void excluir(Contato c) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM agenda.contato ");
		sql.append("WHERE codigo = ?");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, c.getCodigo());

		comando.executeUpdate();

	}

	public void editar(Contato c) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE agenda.contato ");
		sql.append(
				"SET nome = ?, endereco = ?, telefone = ?, celular = ?, profissao = ?, email = ?, empresa = ?, dataNascimento = ?, dataCadastro = ?, observacao = ? ");
		sql.append("WHERE codigo = ?");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, c.getNome());
		comando.setString(2, c.getEndereco());
		comando.setString(3, c.getTelefone());
		comando.setString(4, c.getCelular());
		comando.setString(5, c.getProfissao());
		comando.setString(6, c.getEmail());
		comando.setString(7, c.getEmpresa());
		comando.setString(8, c.getDataNascimento());
		comando.setString(9, c.getDataCadastro());
		comando.setString(10, c.getObservacao());
		comando.setLong(11, c.getCodigo());

		comando.executeUpdate();
	}

	public Contato buscarPorCodigo(Contato c) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, nome, celular ");
		sql.append("FROM  agenda.contato ");
		sql.append("WHERE codigo = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, c.getCodigo());

		ResultSet resultado = comando.executeQuery();

		Contato retorno = null;

		if (resultado.next()) {
			retorno = new Contato();
			retorno.setCodigo(resultado.getLong("codigo"));
			retorno.setNome(resultado.getString("nome"));
			retorno.setCelular(resultado.getString("celular"));
		}
		return retorno;
	}

	public ArrayList<Contato> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"SELECT codigo, nome, endereco, telefone, celular, profissao, email, empresa, dataNascimento, datacadastro, observacao ");
		sql.append("FROM agenda.contato ");
		sql.append("ORDER BY nome ASC ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		ResultSet resultado = comando.executeQuery();

		ArrayList<Contato> lista = new ArrayList<Contato>();

		while (resultado.next()) {
			Contato c = new Contato();
			c.setCodigo(resultado.getLong("codigo"));
			c.setNome(resultado.getString("nome"));
			c.setEndereco(resultado.getString("endereco"));
			c.setTelefone(resultado.getString("telefone"));
			c.setCelular(resultado.getString("celular"));
			c.setProfissao(resultado.getString("profissao"));
			c.setEmail(resultado.getString("email"));
			c.setEmpresa(resultado.getString("empresa"));
			c.setDataNascimento(resultado.getString("dataNascimento"));
			c.setDataCadastro(resultado.getString("datacadastro"));
			c.setObservacao(resultado.getString("observacao"));

			lista.add(c);
		}
		return lista;
	}

	public ArrayList<Contato> buscarPorNome(Contato c) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"SELECT codigo, nome, endereco, telefone, celular, profissao, email, empresa, dataNascimento, dataCadastro, observacao ");
		sql.append("FROM agenda.contato ");
		sql.append("WHERE nome LIKE ?");
		sql.append("ORDER BY nome ASC ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, "%" + c.getNome() + "%");

		ResultSet resultado = comando.executeQuery();

		ArrayList<Contato> lista = new ArrayList<Contato>();

		while (resultado.next()) {
			Contato item = new Contato();
			item.setCodigo(resultado.getLong("codigo"));
			item.setNome(resultado.getString("nome"));
			item.setEndereco(resultado.getString("endereco"));
			item.setTelefone(resultado.getString("telefone"));
			item.setCelular(resultado.getString("celular"));
			item.setProfissao(resultado.getString("profissao"));
			item.setEmail(resultado.getString("email"));
			item.setEmpresa(resultado.getString("empresa"));
			item.setDataNascimento(resultado.getString("dataNascimento"));
			item.setDataCadastro(resultado.getString("dataCadastro"));
			item.setObservacao(resultado.getString("observacao"));

			lista.add(item);
		}
		return lista;
	}

}
