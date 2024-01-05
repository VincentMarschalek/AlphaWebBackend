// package at.alphaplan.AlphaWeb.presentation;
//
// import com.example.todo.domain.Todo;
// import com.example.todo.presentation.commands.CreateTodoCommand;
// import com.example.todo.service.TodoService;
// import lombok.RequiredArgsConstructor;
// import org.springframework.http.HttpStatus;
// import org.springframework.web.bind.annotation.*;
//
// @RestController
// @RequestMapping("/api/todo")
// @RequiredArgsConstructor
// public class TodoController {
//
//  private final TodoService todoService;
//
//  // Get Request
//  // Status: 200
//  @GetMapping("/{id}")
//  public Todo findTodo(@PathVariable("id") String todoId) {
//    return todoService.findTodo(todoId);
//  }
//
//  // HTTP Request
//  // Post /api/todo
//  // Body: {Todo Inhalt}
//  // HTTP Response
//  // HTTP Status: 201
//  // Body: {Todo Inhalt}
//
//  @PostMapping
//  @ResponseStatus(HttpStatus.CREATED)
//  public Todo createTodo(@RequestBody CreateTodoCommand command) {
//    return todoService.createTodo(command);
//  }
// }
