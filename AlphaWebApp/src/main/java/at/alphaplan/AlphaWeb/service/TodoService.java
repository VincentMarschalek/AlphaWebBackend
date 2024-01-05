package at.alphaplan.AlphaWeb.service;

// @Bean
// @Repository
// @Service
// @Controller
// @RestController
// @Component

// Execute Business Use Case
// Logging
// Transaction
// Business Rules Validation
// Communicate with Repository

import com.example.todo.domain.Todo;
import com.example.todo.persistance.TodoRepository;
import com.example.todo.presentation.commands.CreateTodoCommand;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

//Application Service
@Service
@RequiredArgsConstructor
public class TodoService {

  private final Logger Logger = LoggerFactory.getLogger(TodoService.class);

  private final TodoRepository todoRepository;
  private  final DomainValidationService domainValidationService;

  // Ctor injection
  //    public TodoService(TodoRepository todoRepository)
  //    {
  //        this.todoRepository = todoRepository;
  //    }

  // Todo aus Datenbank per ID
  public Todo findTodo(String todoId) {

    Logger.info("find todo with Id {}", todoId);
    Todo todo = domainValidationService.getTodoById(todoId);
    Logger.info("successfully found todo with id {}", todoId);

    return todo;
  }

  //Domain Services helfen Application Services beim Ausführen von Business Cases
  public Todo createTodo(CreateTodoCommand command)
  {
    Logger.info("Create new todo with {}", command);

    Todo todo = new Todo(command.title(), command.completed());
    todoRepository.save(todo);

    Logger.info("Successfully created new todo");

    return todo;
  }
}
