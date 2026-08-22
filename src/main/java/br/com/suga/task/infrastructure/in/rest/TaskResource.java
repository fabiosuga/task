package br.com.suga.task.infrastructure.in.rest;

import java.util.List;
import jakarta.ws.rs.BeanParam;
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
import br.com.suga.task.application.usecase.delete.DeleteTaskUseCase;
import br.com.suga.task.application.usecase.find.FindTaskUseCase;
import br.com.suga.task.application.usecase.insert.InsertTaskInput;
import br.com.suga.task.application.usecase.insert.InsertTaskUseCase;
import br.com.suga.task.application.usecase.list.FilterTaskInput;
import br.com.suga.task.application.usecase.list.ListTaskUseCase;
import br.com.suga.task.application.usecase.response.TaskResponse;
import br.com.suga.task.application.usecase.update.UpdateTaskInput;
import br.com.suga.task.application.usecase.update.UpdateTaskUseCase;
import jakarta.validation.Valid;

@Path("/tasks")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TaskResource {
    
    private final InsertTaskUseCase insertTaskUseCase;
    private final FindTaskUseCase findTaskUseCase;
    private final ListTaskUseCase listTaskUseCase;
    private final UpdateTaskUseCase updateTaskUseCase;
    private final DeleteTaskUseCase deleteTaskUseCase;

    public TaskResource(
            InsertTaskUseCase insertTaskUseCase,
            FindTaskUseCase findTaskUseCase,
            ListTaskUseCase listTaskUseCase,
            UpdateTaskUseCase updateTaskUseCase,
            DeleteTaskUseCase deleteTaskUseCase) {

        this.insertTaskUseCase = insertTaskUseCase;
        this.findTaskUseCase = findTaskUseCase;
        this.listTaskUseCase = listTaskUseCase;
        this.updateTaskUseCase = updateTaskUseCase;
        this.deleteTaskUseCase = deleteTaskUseCase;
    }

    @POST
    public TaskResponse insert(@Valid InsertTaskRequest request) {
        
        InsertTaskInput input = new InsertTaskInput(
                request.title(),
                request.description()
        );
        System.out.println("================================= RESOURCE INSERT =================================");
        System.out.println(String.format("title: %s, description: %s", input.title(), input.description()));
        return insertTaskUseCase.execute(input);
    }

    @GET
    @Path("/{id}")
    public Response find(@PathParam("id") String id) {

        TaskResponse tr = findTaskUseCase.execute(id);

        if (tr == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(tr).build();
    }

    @GET
    public Response list(
            @Valid @BeanParam FilterTaskRequest request) {
        
        FilterTaskInput input = new FilterTaskInput(
                request.id(),
                request.title(),
                request.description(),
                request.completed()
        );
        
        List<TaskResponse> trList = listTaskUseCase.execute(input);

        if (trList == null || trList.isEmpty()) {
            return Response.noContent().build();
        }

        return Response.ok(trList).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(
            @PathParam("id") String id,
            @Valid UpdateTaskRequest request) {

        System.out.println("================================= RESOURCE UPDATE =================================");
        System.out.println(String.format("title: %s, description: %s", request.title(), request.description()));

        UpdateTaskInput input = new UpdateTaskInput(
                id,
                request.title(),
                request.description(),
                request.completed()
        );

        TaskResponse tr = updateTaskUseCase.execute(input);

        if (tr == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(tr).build();
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") String id) {
        deleteTaskUseCase.execute(id);
    }

}
