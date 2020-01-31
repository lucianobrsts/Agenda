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
		Contato contato = new Contato();
		contato.setNome("Luciano Brito");
		contato.setEndereco("Rua Goiás, 674");
		contato.setTelefone("85 3482-3844");
		contato.setCelular("85 988880281");
		contato.setEmail("lucianobrsts@yahoo.com.br");
		contato.setProfissao("Programador");
		contato.setEmpresa("Pague Menos");
		contato.setNascimento("20/07/1975");
		contato.setDataCadastro("06/04/2018");
		contato.setObservacao("Estudante de Arquitetura de software na Uni7");

		ContatoDAO contatodao = new ContatoDAO();
		contatodao.salvar(contato);
	}

	@Test
	@Ignore
	public void excluir() throws SQLException {
		Contato c = new Contato();
		c.setCodigo(2L);

		ContatoDAO contatodao = new ContatoDAO();
		contatodao.excluir(c);
	}

	@Test
	public void editar() throws SQLException {
		Contato c = new Contato();
		c.setCodigo(1L);
		c.setNome("Luciana Brito");
		c.setEndereco("Rua Goiás, 670");
		c.setTelefone("85 3482-3844");
		c.setCelular("85 988880281");
		c.setEmail("lucianabrsts@yahoo.com.br");
		c.setProfissao("Vendedora");
		c.setEmpresa("Z'Dannys Variedades");
		c.setNascimento("20/07/1975");
		c.setObservacao("Estudante de desenvolvimento na FIC");

		ContatoDAO contatodao = new ContatoDAO();
		contatodao.editar(c);
	}
}
