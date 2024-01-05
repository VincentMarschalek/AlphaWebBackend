package at.alphaplan.AlphaWeb.service;

import com.example.todo.domain.Todo;
import com.example.todo.persistance.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DomainValidationService //todo
{
    private final TodoRepository todoRepository;

    public Todo getTodoById(String todoId)
    {
        Optional<Todo> todoOptional = todoRepository.findById(new ObjectId(todoId));
        return todoOptional.orElseThrow(() -> new IllegalArgumentException("Todo with id" + todoId + " does not exist"));
    }
}
