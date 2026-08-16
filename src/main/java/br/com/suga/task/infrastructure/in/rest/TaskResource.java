package br.com.suga.task.infrastructure.in.rest;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
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

@RestController
@RequestMapping("/tasks")
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

    @PostMapping
    public TaskResponse insert(@Valid @RequestBody InsertTaskRequest request) {
        
        InsertTaskInput input = new InsertTaskInput(
                request.title(),
                request.description()
        );
        System.out.println("================================= RESOURCE INSERT =================================");
        System.out.println(String.format("title: %s, description: %s", input.title(), input.description()));
        return insertTaskUseCase.execute(input);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> find(@PathVariable String id) {

        TaskResponse tr = findTaskUseCase.execute(id);

        if (tr == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(tr);
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> list(
            @Valid FilterTaskRequest request) {
        
        FilterTaskInput input = new FilterTaskInput(
                request.id(),
                request.title(),
                request.description(),
                request.completed()
        );
        
        List<TaskResponse> trList = listTaskUseCase.execute(input);

        if (trList == null || trList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(trList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> update(
            @PathVariable String id,
            @Valid @RequestBody UpdateTaskRequest request) {

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
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(tr);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        deleteTaskUseCase.execute(id);
    }

}
