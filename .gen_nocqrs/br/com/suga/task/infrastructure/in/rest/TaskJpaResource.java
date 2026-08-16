package br.com.suga.task.infrastructure.in.rest;

import br.com.suga.task.application.dto.AtualizarTaskJpaRequest;
import br.com.suga.task.application.dto.CriarTaskJpaRequest;
import br.com.suga.task.application.dto.TaskJpaResponse;
import br.com.suga.task.application.service.TaskJpaService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/api/tasks")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TaskJpaResource {
    private final TaskJpaService service;

    @Inject
    public TaskJpaResource(TaskJpaService service) {
        this.service = service;
    }

    @POST
    public Response criar(CriarTaskJpaRequest request) {
        TaskJpaResponse response = service.criar(request);
        return Response.status(Response.Status.CREATED).entity(response).build();
    }

    @PUT
    @Path("/{id}")
    public TaskJpaResponse atualizar(@PathParam("id") String id, AtualizarTaskJpaRequest request) {
        return service.atualizar(id, request);
    }

    @GET
    @Path("/{id}")
    public TaskJpaResponse buscarPorId(@PathParam("id") String id) {
        return service.buscarPorId(id);
    }

    @GET
    public List<TaskJpaResponse> listar() {
        return service.listar();
    }

    @DELETE
    @Path("/{id}")
    public Response excluir(@PathParam("id") String id) {
        service.excluir(id);
        return Response.noContent().build();
    }
}
