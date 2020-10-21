package br.edu.unidep.ApiES.event;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

public class ObjetoCriadoEvent extends ApplicationEvent {
	private static final long serialVersionUID = 1L;
	
	private HttpServletResponse reponse;
	private Long codigo;
	
	public ObjetoCriadoEvent(Object source, HttpServletResponse response, Long codigo) {
		super(source);
		this.reponse = response;
		this.codigo = codigo;
	}

	public HttpServletResponse getReponse() {
		return reponse;
	}

	public Long getCodigo() {
		return codigo;
	}
	
}
