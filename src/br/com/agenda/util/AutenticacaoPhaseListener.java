package br.com.agenda.util;

import java.util.Map;

import javax.faces.application.Application;
import javax.faces.application.NavigationHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.com.agenda.bean.AutenticacaoBean;
import br.com.agenda.domain.Usuario;

@SuppressWarnings("serial")
public class AutenticacaoPhaseListener implements PhaseListener {

	@Override
	public void afterPhase(PhaseEvent event) {
		FacesContext facesContext = event.getFacesContext();
		UIViewRoot uiViewRoot = facesContext.getViewRoot();
		String paginaAtual = uiViewRoot.getViewId();

		boolean ehPaginaLogin = paginaAtual.contains("login.xhtml");

		if (!ehPaginaLogin) {
			ExternalContext externalContext = facesContext.getExternalContext();
			Map<String, Object> mapa = externalContext.getSessionMap();
			AutenticacaoBean autenticacaoBean = (AutenticacaoBean) mapa.get("autenticacaoBean");

			Usuario usuario = autenticacaoBean.getUsuarioLogado();

			if (usuario == null) {
				JSFUtil.adicionarMensagemErro("Usu�rio n�o autenticado. Fa�a o login.");

				Application application = facesContext.getApplication();
				NavigationHandler navigationHandler = application.getNavigationHandler();
				navigationHandler.handleNavigation(facesContext, null, "/pages/login.xhtml?faces-redirect=true");
			}
		}
	}

	@Override
	public void beforePhase(PhaseEvent event) {

	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
