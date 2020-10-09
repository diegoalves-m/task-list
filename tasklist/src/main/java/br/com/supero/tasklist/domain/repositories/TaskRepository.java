package br.com.supero.tasklist.domain.repositories;


import br.com.supero.tasklist.domain.models.Status;
import br.com.supero.tasklist.domain.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByStatusGreaterThan(Status status);

}
