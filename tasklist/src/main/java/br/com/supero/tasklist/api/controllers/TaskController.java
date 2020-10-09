package br.com.supero.tasklist.api.controllers;


import br.com.supero.tasklist.api.dtos.TaskDTO;
import br.com.supero.tasklist.domain.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public TaskDTO create(@RequestBody @Valid TaskDTO taskDTO) {
        taskDTO = taskService.insert(taskDTO);
        return taskDTO;
    }

    @PutMapping("/{taskId}")
    public TaskDTO update(@PathVariable Long taskId, @RequestBody @Valid TaskDTO taskDTO) {
        taskDTO = taskService.update(taskId, taskDTO);
        return taskDTO;
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> findAll() {
        List<TaskDTO> list = taskService.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskDTO> findById(@PathVariable Long taskId) {
        TaskDTO taskDTO = taskService.findById(taskId);
        return ResponseEntity.ok(taskDTO);
    }
}
