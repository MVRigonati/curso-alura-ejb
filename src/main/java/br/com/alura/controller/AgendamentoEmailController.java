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

import br.com.alura.entity.AgendamentoEmail;
import br.com.alura.service.AgendamentoEmailService;

@Path("emails")
public class AgendamentoEmailController {

	@Inject
	private AgendamentoEmailService service;
	
	@GET
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response list() {
		List<AgendamentoEmail> result = this.service.list();
		return Response.ok(result).build();
	}
	
	@POST
	@Consumes(value = MediaType.APPLICATION_JSON)
	public Response insert(AgendamentoEmail email) {
		this.service.insert(email);
		return Response.status(201).build();
	}
	
}
