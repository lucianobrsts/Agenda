package br.com.agenda.bean;

import java.sql.SQLException;

import javax.faces.bean.ManagedBean;

import br.com.agenda.dao.UsuarioDAO;
import br.com.agenda.domain.Usuario;
import br.com.agenda.util.JSFUtil;

@ManagedBean
public class AutenticacaoBean {

	private Usuario usuarioLogado;

	public Usuario getUsuarioLogado() {
		if (usuarioLogado == null) {
			usuarioLogado = new Usuario();
		}

		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public void autenticar() {
		try {
			UsuarioDAO userdao = new UsuarioDAO();
			usuarioLogado = userdao.autenticar(usuarioLogado);
			if (usuarioLogado == null) {
				JSFUtil.adicionarMensagemErro("Nome e/ou Senha inválidos!");
			} else {
				JSFUtil.adicionarMensagemSucesso("Funcionário autenticado com sucesso!");
			}
		} catch (RuntimeException ex) {
			JSFUtil.adicionarMensagemErro("Erro ao tentar autenticar no sistema" + ex.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String sair() {
		usuarioLogado = null;

		return "/pages/login.xhtml?faces-redirect=true";
	}
}
