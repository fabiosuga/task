package br.com.suga.task.infrastructure.in.rest;

import br.com.suga.task.application.command.*;
import br.com.suga.task.application.query.*;
import br.com.suga.task.application.query.dto.TaskJpaResponse;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/api/taskJpas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TaskJpaResource {

    private final InserirTaskJpaUseCase inserirUseCase;
    private final AtualizarTaskJpaUseCase atualizarUseCase;
    private final ExcluirTaskJpaUseCase excluirUseCase;
    private final BuscarTaskJpaPorIdUseCase buscarPorIdUseCase;
    private final ListarTaskJpasUseCase listarUseCase;

    @Inject
    public TaskJpaResource(
            InserirTaskJpaUseCase inserirUseCase,
            AtualizarTaskJpaUseCase atualizarUseCase,
            ExcluirTaskJpaUseCase excluirUseCase,
            BuscarTaskJpaPorIdUseCase buscarPorIdUseCase,
            ListarTaskJpasUseCase listarUseCase) {
        this.inserirUseCase = inserirUseCase;
        this.atualizarUseCase = atualizarUseCase;
        this.excluirUseCase = excluirUseCase;
        this.buscarPorIdUseCase = buscarPorIdUseCase;
        this.listarUseCase = listarUseCase;
    }

    @POST
    public Response criar(InserirTaskJpaCommand command) {
        TaskJpaResponse response = inserirUseCase.execute(command);
        return Response.status(Response.Status.CREATED).entity(response).build();
    }

    @PUT
    public Response atualizar(AtualizarTaskJpaCommand command) {
        TaskJpaResponse response = atualizarUseCase.execute(command);
        return Response.ok(response).build();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") String id) {
        BuscarTaskJpaPorIdQuery query = new BuscarTaskJpaPorIdQuery(id);
        TaskJpaResponse response = buscarPorIdUseCase.execute(query);
        return Response.ok(response).build();
    }

    @GET
    public Response listarTodos() {
        ListarTaskJpasQuery query = new ListarTaskJpasQuery();
        List<TaskJpaResponse> response = listarUseCase.execute(query);
        return Response.ok(response).build();
    }

    @DELETE
    @Path("/{id}")
    public Response excluir(@PathParam("id") String id) {
        ExcluirTaskJpaCommand command = new ExcluirTaskJpaCommand(id);
        excluirUseCase.execute(command);
        return Response.noContent().build();
    }
}