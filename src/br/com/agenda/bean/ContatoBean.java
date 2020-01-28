package br.com.agenda.bean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.StreamedContent;

import br.com.agenda.dao.ContatoDAO;
import br.com.agenda.domain.Contato;
import br.com.agenda.util.JSFUtil;
import br.com.agenda.util.RelatorioUtil;

@ManagedBean(name = "MBContato")
@ViewScoped
public class ContatoBean {
	private Contato contato;
	private ArrayList<Contato> itens;
	private ArrayList<Contato> itensFiltrados;
	private StreamedContent arquivoRetorno;
	private int tipoRelatorio;

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
			ContatoDAO dao = new ContatoDAO();
			dao.salvar(contato);

			itens = dao.listar();

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

	@SuppressWarnings("rawtypes")
	public StreamedContent getArquivoRetorno() {
		FacesContext context = FacesContext.getCurrentInstance();
		String nomeRelatorioJasper = "Contato";
		String nomeRelatorioSaida = "_contato";
		RelatorioUtil relatorioUtil = new RelatorioUtil();
		HashMap parametroRelatorio = new HashMap();

		try {
			this.arquivoRetorno = relatorioUtil.gerarRelatorio(parametroRelatorio, nomeRelatorioJasper,
					nomeRelatorioSaida, this.tipoRelatorio);
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(e.getCause().getMessage()));
			return null;
		}
		return this.arquivoRetorno;
	}

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

	public int getTipoRelatorio() {
		return tipoRelatorio;
	}

	public void setTipoRelatorio(int tipoRelatorio) {
		this.tipoRelatorio = tipoRelatorio;
	}

	public void setArquivoRetorno(StreamedContent arquivoRetorno) {
		this.arquivoRetorno = arquivoRetorno;
	}

}