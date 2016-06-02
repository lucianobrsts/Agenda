package br.com.agenda.test;

import java.sql.SQLException;

import org.junit.Ignore;
import org.junit.Test;

import br.com.agenda.dao.ContatoDAO;
import br.com.agenda.domain.Contato;

public class ContatoDAOTeste {

	@Test
	@Ignore
	public void salvar() throws SQLException {
		Contato c = new Contato();
		c.setNome("Luciano Brito");
		c.setEndereco("Rua Goiás, 674");
		c.setTelefoneFixo("85 3482-3844");
		c.setCelular("85 988880281");
		c.setEmail("lucianobrsts@yahoo.com.br");
		c.setProfissao("Programador");
		c.setEmpresa("Pague Menos");
		c.setDataNascimento("20/07/1975");

		ContatoDAO contatodao = new ContatoDAO();
		contatodao.salvar(c);
	}

	@Test
	@Ignore
	public void excluir() throws SQLException {
		Contato c = new Contato();
		c.setCodigo(22L);

		ContatoDAO contatodao = new ContatoDAO();
		contatodao.excluir(c);
	}

	@Test
	public void editar() throws SQLException {
		Contato c = new Contato();
		c.setCodigo(20L);
		c.setNome("Luciana Brito");
		c.setEndereco("Rua Goiás, 670");
		c.setTelefoneFixo("85 3482-3844");
		c.setCelular("85 988880281");
		c.setEmail("lucianabrsts@yahoo.com.br");
		c.setProfissao("Vendedora");
		c.setEmpresa("Z'Dannys Variedades");
		c.setDataNascimento("20/07/1975");

		ContatoDAO contatodao = new ContatoDAO();
		contatodao.editar(c);
	}
}
