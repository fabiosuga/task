# Task api

Simple example to make a PoC (Proof of Concept) working with some concepts like DDD, Hexagonal and Clean. I'm also using Java 21 and Spring Boot.

The implementation uses Java and  SpringBoot with the single purpose of agility to deliver something functional, but since it's a concept, it could be build with any other languages.




| Implementation                                        | Key Concepts             |
| ----------------------------------------------------- | ------------------------ |
| `Task`database independent                          | DDD + Clean              |
| `TaskRepository`as interface                        | DDD + Hexagonal          |
| Implementation outside repository domain              | Hexagonal + Clean        |
| `InsertTaskUseCase`                                 | DDD/Application + Clean  |
| `InsertTaskInput`                                   | Application/Clean        |
| `TaskResponse`disconnected from Domain              | Clean                    |
| Controller segregated from Use Case                   | Hexagonal + Clean        |
| Spring framework isolated as infrastructure          | Clean                    |
| PostgreSQL isolado atrás do Repository               | Hexagonal                |
| Factory to choose between Repository                 | Design Pattern           |
| Strategy for behavior and alternative implementations | Design Pattern           |
| Flyway                                                | Infraestrutura           |
| Bean Validation on Request                            | Interface/Infrastructure |
| Dependency Injection                                  | IoC/Dependency Inversion |
| Domain dependencies in abstractions                   | Clean + Hexagonal        |


## Using curl to make some requests

Following a couple of calls using curl to check the Rest Service and layers ahead working.

### Insert command (POST)

```
curl -X POST http://localhost:8080/tasks \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Factory",
    "description": "Minha tarefa",
    "completed": false
  }'
```

### List command (GET)

```
curl -G http://localhost:8080/tasks \
  --data-urlencode "title=Factory" \
  --data-urlencode "completed=false"
```

### Update command (PUT)

```
curl -X PUT http://localhost:8080/tasks/d6b97983-01ec-440a-aef8-753d55e6de4a \
  -H "Content-Type: application/json" \
  -d '{
    "id": "d6b97983-01ec-440a-aef8-753d55e6de4a",
    "title": "Estudar Factory Pattern",
    "description": "Implementar Factory + Strategy",
    "completed": true
  }'
```

### Find command (GET)

```
curl -X GET http://localhost:8080/tasks/1
```

### Remove command (DELETE)

```
curl -X DELETE http://localhost:8080/tasks/d6b97983-01ec-440a-aef8-753d55e6de4a
```
