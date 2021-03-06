package br.com.agenda.test;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import br.com.agenda.dao.UsuarioDAO;
import br.com.agenda.domain.Usuario;

public class UsuarioDAOTeste {

	@Test
	@Ignore
	public void salvar() throws SQLException {
		Usuario u = new Usuario();
		u.setNome("Tereza Cristina");
		u.setSenha("123");

		UsuarioDAO userdao = new UsuarioDAO();
		userdao.salvar(u);
	}

	@Test
	@Ignore
	public void listar() throws SQLException {
		UsuarioDAO userdao = new UsuarioDAO();

		ArrayList<Usuario> lista = userdao.listar();

		for (Usuario u : lista) {
			System.out.println("Resultado: " + u);
		}
	}

	@Test
	@Ignore
	public void excluir() throws SQLException {
		Usuario u = new Usuario();
		u.setCodigo(8L);

		UsuarioDAO userdao = new UsuarioDAO();
		userdao.excluir(u);
	}

	@Test
	public void autenticar() throws SQLException {

		UsuarioDAO dao = new UsuarioDAO();
		Usuario user = new Usuario();

		String nome = "Luciano Brito";
		String senha = "123456";

		user = dao.autenticar(nome, senha);

		System.out.println("Usu�rio: " + user);

		Assert.assertNotNull(user);
	}

}