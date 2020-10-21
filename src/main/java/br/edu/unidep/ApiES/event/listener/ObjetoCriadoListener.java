package br.edu.unidep.ApiES.event.listener;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.edu.unidep.ApiES.event.ObjetoCriadoEvent;

public class ObjetoCriadoListener implements ApplicationListener<ObjetoCriadoEvent>{

	@Override
	public void onApplicationEvent(ObjetoCriadoEvent event) {
		HttpServletResponse response = event.getReponse();
		Long codigo = event.getCodigo();
		
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{codigo}").buildAndExpand(codigo).toUri();
		
		response.setHeader("Location", uri.toASCIIString());			
	}
}
