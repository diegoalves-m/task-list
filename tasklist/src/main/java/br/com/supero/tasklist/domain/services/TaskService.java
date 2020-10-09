package br.com.supero.tasklist.domain.services;

import br.com.supero.tasklist.api.dtos.TaskDTO;
import br.com.supero.tasklist.domain.models.Status;
import br.com.supero.tasklist.domain.models.Task;
import br.com.supero.tasklist.domain.repositories.TaskRepository;
import br.com.supero.tasklist.domain.services.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Transactional(readOnly = true)
    public List<TaskDTO> findAll() {
        List<Task> tasks = taskRepository.findByStatusGreaterThan(Status.REMOVIDA);
        return tasks.stream().map(TaskDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public TaskDTO findById(Long taskId) {
        Task entity = findTask(taskId);
        return new TaskDTO(entity);
    }

    @Transactional
    public TaskDTO insert(TaskDTO taskDTO) {
        Task task = new Task();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setStatus(Status.ANDAMENTO);
        task.setCreationDate(LocalDateTime.now());
        return new TaskDTO(taskRepository.save(task));
    }

    @Transactional
    public TaskDTO update(Long id, TaskDTO taskDTO) {
        Task task = findTask(id);
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());

        if(!taskDTO.isChangeFields()) {

            if (taskDTO.getStatus() == Status.CONCLUÍDA) {
                task.setStatus(Status.ANDAMENTO);
                task.setConclusionDate(null);
            } else if (taskDTO.getStatus() == Status.ANDAMENTO) {
                task.setStatus(Status.CONCLUÍDA);
                task.setConclusionDate(LocalDateTime.now());
            } else if (taskDTO.getStatus() == Status.REMOVIDA) {
                task.setRemovedDate(LocalDateTime.now());
                task.setStatus(Status.REMOVIDA);
            }
        }

        task.setModificationDate(LocalDateTime.now());

        return new TaskDTO(taskRepository.save(task));
    }

    private Task findTask(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));
    }

}
