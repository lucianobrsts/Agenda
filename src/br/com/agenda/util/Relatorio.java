package br.com.agenda.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import br.com.agenda.domain.Contato;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

public class Relatorio {
	private HttpServletResponse response;
	private FacesContext context;
	private ByteArrayOutputStream baos;
	private InputStream stream;

	public Relatorio() {
		this.context = FacesContext.getCurrentInstance();
		this.response = (HttpServletResponse) context.getExternalContext().getResponse();
	}

	public void getRelatorioContato(List<Contato> lista) {
		stream = this.getClass().getResourceAsStream("relatorios/contato.jasper");
		Map<String, Object> params = new HashMap<String, Object>();
		baos = new ByteArrayOutputStream();

		try {
			JasperReport report = (JasperReport) JRLoader.loadObject(stream);
			JasperPrint print = JasperFillManager.fillReport(report, params, new JRBeanCollectionDataSource(lista));
			JasperExportManager.exportReportToPdfStream(print, baos);

			response.reset();
			response.setContentType("application/pdf");
			response.setContentLengthLong(baos.size());
			response.setHeader("Content-disposition", "inline: filename=relatorio.pdf");
			response.getOutputStream().write(baos.toByteArray());
			response.getOutputStream().flush();
			response.getOutputStream().close();

			context.getResponseComplete();
		} catch (JRException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
