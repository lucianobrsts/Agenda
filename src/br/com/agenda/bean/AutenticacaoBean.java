package br.com.agenda.bean;

import java.io.IOException;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.agenda.dao.UsuarioDAO;
import br.com.agenda.domain.Usuario;

@ManagedBean(name = "MBAutenticar")
@SessionScoped
public class AutenticacaoBean {

	private Usuario usuario;
	private Usuario usuarioLogado;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	@PostConstruct
	public void iniciar() {
		usuario = new Usuario();
	}

	public void autenticar() {

		try {
			UsuarioDAO dao = new UsuarioDAO();

			usuarioLogado = dao.autenticar(usuario.getNome(), usuario.getSenha());

			if (usuarioLogado == null) {
				Messages.addGlobalError("Usuário, senha ou tipo incorretos!");
				return;
			}

			Faces.redirect("/pages/principal.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
			Messages.addGlobalError(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
			Messages.addGlobalError(e.getMessage());
		}
	}

	public void sair() {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("MBAutenticar");
	}

}
