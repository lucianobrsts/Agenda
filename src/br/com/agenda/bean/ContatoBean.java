package br.com.agenda.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.agenda.dao.ContatoDAO;
import br.com.agenda.domain.Contato;
import br.com.agenda.util.JSFUtil;

@ManagedBean(name = "MBContato")
@ViewScoped
public class ContatoBean {
	private Contato contato;
	private ArrayList<Contato> itens;
	private ArrayList<Contato> itensFiltrados;

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public ArrayList<Contato> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Contato> itens) {
		this.itens = itens;
	}

	public ArrayList<Contato> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Contato> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	@PostConstruct
	public void prepararPesquisa() {
		try {
			ContatoDAO dao = new ContatoDAO();
			itens = dao.listar();
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	public void prepararNovo() {
		contato = new Contato();
	}

	public void novo() {
		try {
			ContatoDAO contatodao = new ContatoDAO();
			contatodao.salvar(contato);

			itens = contatodao.listar();

			JSFUtil.adicionarMensagemSucesso("Contato Salvo com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	public void excluir() {
		try {
			ContatoDAO contatodao = new ContatoDAO();
			contatodao.excluir(contato);

			itens = contatodao.listar();

			JSFUtil.adicionarMensagemSucesso("Contato removido com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	public void editar() {
		try {
			ContatoDAO contatodao = new ContatoDAO();
			contatodao.editar(contato);

			itens = contatodao.listar();

			JSFUtil.adicionarMensagemSucesso("Contato editado com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}
}