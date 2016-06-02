package br.com.agenda.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.agenda.dao.UsuarioDAO;
import br.com.agenda.domain.Usuario;
import br.com.agenda.util.JSFUtil;

@ManagedBean(name = "MBUsuario")
@ViewScoped
public class UsuarioBean {
	private Usuario usuario;
	private ArrayList<Usuario> itens;
	private ArrayList<Usuario> itensFiltrados;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public ArrayList<Usuario> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Usuario> itens) {
		this.itens = itens;
	}

	public ArrayList<Usuario> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Usuario> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	@PostConstruct
	public void preparPesquisa() {
		try {
			UsuarioDAO dao = new UsuarioDAO();
			itens = dao.listar();
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	public void prepararNovo() {
		usuario = new Usuario();
	}

	public void novo() {
		try {
			UsuarioDAO userdao = new UsuarioDAO();
			userdao.salvar(usuario);
			JSFUtil.adicionarMensagemSucesso("Usuário salvo com sucesso!");
			itens = userdao.listar();
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	public void excluir() {
		try {
			UsuarioDAO userdao = new UsuarioDAO();
			userdao.excluir(usuario);

			itens = userdao.listar();

			JSFUtil.adicionarMensagemSucesso("Usuário Excluído com sucesso!");
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	public void editar() {
		try {
			UsuarioDAO userdao = new UsuarioDAO();
			userdao.editar(usuario);

			itens = userdao.listar();

			JSFUtil.adicionarMensagemSucesso("Usuário Editado com sucesso!");
		} catch (Exception e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

}
