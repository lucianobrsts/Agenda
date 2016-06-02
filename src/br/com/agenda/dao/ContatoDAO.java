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
		sql.append("INSERT INTO agenda.contato ");
		sql.append("(nome, endereco, fonefixo, celular, profissao, email, empresa, dataNascimento) ");
		sql.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, c.getNome());
		comando.setString(2, c.getEndereco());
		comando.setString(3, c.getTelefoneFixo());
		comando.setString(4, c.getCelular());
		comando.setString(5, c.getProfissao());
		comando.setString(6, c.getEmail());
		comando.setString(7, c.getEmpresa());
		comando.setString(8, c.getDataNascimento());

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
				"SET nome = ?, endereco = ?, fonefixo = ?, celular = ?, profissao = ?, email = ?, empresa = ?, dataNascimento = ? ");
		sql.append("WHERE codigo = ?");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, c.getNome());
		comando.setString(2, c.getEndereco());
		comando.setString(3, c.getTelefoneFixo());
		comando.setString(4, c.getCelular());
		comando.setString(5, c.getProfissao());
		comando.setString(6, c.getEmail());
		comando.setString(7, c.getEmpresa());
		comando.setString(8, c.getDataNascimento());
		comando.setLong(9, c.getCodigo());

		comando.executeUpdate();
	}

	public Contato buscarPorCodigo(Contato c) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, nome, celular ");
		sql.append("FROM  agenda.contato ");
		sql.append("WHERE codigo = ?");

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
		sql.append("SELECT codigo, nome, endereco, fonefixo, celular, profissao, email, empresa, dataNascimento ");
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
			c.setTelefoneFixo(resultado.getString("fonefixo"));
			c.setCelular(resultado.getString("celular"));
			c.setProfissao(resultado.getString("profissao"));
			c.setEmail(resultado.getString("email"));
			c.setEmpresa(resultado.getString("empresa"));
			c.setDataNascimento(resultado.getString("dataNascimento"));

			lista.add(c);
		}
		return lista;
	}

	public ArrayList<Contato> buscarPorNome(Contato c) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, nome, endereco, fonefixo, celular, profissao, email, empresa, dataNascimento ");
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
			item.setTelefoneFixo(resultado.getString("fonefixo"));
			item.setCelular(resultado.getString("celular"));
			item.setProfissao(resultado.getString("profissao"));
			item.setEmail(resultado.getString("email"));
			item.setEmpresa(resultado.getString("empresa"));
			item.setDataNascimento(resultado.getString("dataNascimento"));

			lista.add(item);
		}
		return lista;
	}

	// -----------------------------------------------------------------------------
	// Método buscar por nome
	// public static void main(String[] args) {
	// Contato contato = new Contato();
	// contato.setNome("Jor");
	//
	// ContatoDAO contatodao = new ContatoDAO();
	//
	// try {
	// ArrayList<Contato> lista = contatodao.buscarPorNome(contato);
	//
	// for (Contato c : lista) {
	// System.out.println("Resultado: " + c);
	// }
	//
	// } catch (SQLException ex) {
	// System.out.println("Ocorreu um erro ao tentar listar por Nome...");
	// ex.printStackTrace();
	// }
	// }

	// -----------------------------------------------------------------------------
	//
	// Método listar
	// public static void main(String[] args) {
	// ContatoDAO contatodao = new ContatoDAO();
	//
	// try {
	// ArrayList<Contato> lista = contatodao.listar();
	//
	// for (Contato c : lista) {
	// System.out.println("Resultado: " + c);
	// System.out.println("------------------");
	// }
	// } catch (SQLException e) {
	// System.out.println("Ocorreu um erro ao tentar listar os Contatos..");
	// e.printStackTrace();
	// }
	// }

	// -----------------------------------------------------------------------------
	// Método buscar por código
	public static void main(String[] args) {
		Contato c = new Contato();
		ContatoDAO contatodao = new ContatoDAO();

		c.setCodigo(1L);

		try {
			Contato c2 = contatodao.buscarPorCodigo(c);

			System.out.println("Contato: " + c2);
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro!");
			e.printStackTrace();
		}
	}

	// -----------------------------------------------------------------------------
	// Método editar
	// public static void main(String[] args) {
	//
	// Contato c = new Contato();
	// ContatoDAO contatodao = new ContatoDAO();
	//
	// c.setCodigo(1L);
	//
	// c.setNome("Elano Diniz");
	// c.setEndereco("Rua Goiás, 670");
	// c.setTelefoneFixo("85 3482-3844");
	// c.setCelular("85 98812-5241");
	// c.setProfissao("Arquiteto");
	// c.setEmail("jorge@yahoo.com.br");
	// c.setEmpresa("Unifor");
	// c.setDataNascimento("20/06/1980");
	//
	// try {
	// contatodao.editar(c);
	// System.out.println("Contato editado com sucesso..");
	// } catch (SQLException e) {
	// System.out.println("Falha ao editar o contato..");
	// e.printStackTrace();
	// }
	//
	// }

	// -----------------------------------------------------------------------------

	// Método excluir
	// public static void main(String[] args) {
	// Contato c = new Contato();
	// ContatoDAO contatodao = new ContatoDAO();
	//
	// c.setCodigo(13L);
	//
	// try {
	// contatodao.excluir(c);
	// System.out.println("Contato excluído com sucesso...");
	// } catch (SQLException e) {
	// System.out.println("Contato não excluído...");
	// e.printStackTrace();
	// }
	// }

	// -----------------------------------------------------------------------------
	// public static void main(String[] args) {
	//
	// // método salvar
	// Contato c = new Contato();
	// c.setNome("Lucas Monteiro");
	// c.setEndereco("Rua 13, 674");
	// c.setTelefoneFixo("85 3482-3844");
	// c.setCelular("85 98888-0281");
	// c.setProfissao("Desempregado");
	// c.setEmail("lukinha@yahoo.com.br");
	// c.setEmpresa("Casa");
	// c.setDataNascimento("20/06/1990");
	//
	// ContatoDAO contatodao = new ContatoDAO();
	// try {
	// contatodao.salvar(c);
	// System.out.println("Contato salvo com sucesso..");
	// } catch (SQLException e) {
	// System.out.println("Contato não foi salvo..");
	// e.printStackTrace();
	// }
	// }
}
