package com.example.youtan.services;

import com.example.youtan.entity.Task;
import com.example.youtan.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.youtan.repositories.TaskRepository;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

//    public Page<Task> getAllTasks(Pageable pageable) {
//        return taskRepository.findAll(pageable);
//    }

    public List<Task> getAllTasksNoPagination() {
        return taskRepository.findAll();
    }

    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task updatedTask) {
        return taskRepository.findById(id)
                .map(task -> {
                    task.setDescription(updatedTask.getDescription());
                    task.setCompleted(updatedTask.getCompleted());
                    return taskRepository.save(task);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public long countCompletedTasks() {
        return taskRepository.countByCompletedTrue();
    }

    public long countIncompleteTasks() {
        return taskRepository.countByCompletedFalse();
    }

    public Page<Task> getFilteredTasks(Pageable pageable, String search, Boolean completed) {
        if (search == null) search = ""; // Se não houver texto para buscar, defina como vazio

        if (completed == null) {
            // Se completed for null, retorna todas as tarefas
            return taskRepository.findByDescriptionContaining(search, pageable);
        } else if (completed) {
            // Retorna apenas as tarefas completadas (true)
            return taskRepository.findByDescriptionContainingAndCompletedTrue(search, pageable);
        } else {
            // Retorna apenas as tarefas não completadas (false)
            return taskRepository.findByDescriptionContainingAndCompletedFalse(search, pageable);
        }
    }

}
