package br.com.alura.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.alura.entidade.AgendamentoEmail;
import br.com.alura.servico.AgendamentoEmailServico;

@Path("emails")
public class AgendamentoEmailController {

	@Inject
	private AgendamentoEmailServico servico;
	
	@GET
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response listar() {
		List<AgendamentoEmail> result = this.servico.list();
		return Response.ok(result).build();
	}
	
	@POST
	@Consumes(value = MediaType.APPLICATION_JSON)
	public Response inserir(AgendamentoEmail email) {
		this.servico.inserir(email);
		return Response.status(201).build();
	}
	
}
