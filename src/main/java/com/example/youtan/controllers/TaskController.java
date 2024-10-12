package com.example.youtan.controllers;

import com.example.youtan.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.youtan.services.TaskService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;


//    @GetMapping("/alltasks")
//    public Map<String, Object> getTasks(@RequestParam(defaultValue = "0") int page,  // Página atual (padrão: 0)
//                                        @RequestParam(defaultValue = "10") int size) {  // Tamanho da página (padrão: 10)
//
//        Pageable pageable = PageRequest.of(page, size);
//        Page<Task> taskPage = taskService.getAllTasks(pageable);
//
//        Map<String, Object> response = new HashMap<>();
//        response.put("tasks", taskPage.getContent()); // Lista de tarefas
//        response.put("currentPage", taskPage.getNumber());
//        response.put("totalItems", taskPage.getTotalElements()); // Total de tarefas
//        response.put("totalPages", taskPage.getTotalPages());
//        response.put("pageSize", taskPage.getSize());
//
//        return response;
//    }

    @GetMapping("/alltasks")
    public Map<String, Object> getTasks(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size,
                                        @RequestParam(required = false) String search,  // Filtro por texto livre
                                        @RequestParam(required = false) Boolean completed // Filtro por status booleano (true/false)
    ) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Task> taskPage = taskService.getFilteredTasks(pageable, search, completed);

        Map<String, Object> response = new HashMap<>();
        response.put("tasks", taskPage.getContent()); // Lista de tarefas
        response.put("currentPage", taskPage.getNumber());
        response.put("totalItems", taskPage.getTotalElements()); // Total de tarefas
        response.put("totalPages", taskPage.getTotalPages());
        response.put("pageSize", taskPage.getSize());

        return response;
    }


    @PostMapping("/addtasks")
    public Task createTask(@RequestBody Task task) {
        return taskService.addTask(task);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task task) {
        return taskService.updateTask(id, task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/completion-percentage")
    public Map<String, Object> getCompletionPercentage() {
        long totalTasks = taskService.getAllTasksNoPagination().size();
        long completedTasks = taskService.countCompletedTasks();
        long incompleteTasks = taskService.countIncompleteTasks();

        double completedPercentage = (totalTasks == 0) ? 0 : ((double) completedTasks / totalTasks) * 100;
        double incompletePercentage = (totalTasks == 0) ? 0 : ((double) incompleteTasks / totalTasks) * 100;

        Map<String, Object> response = new HashMap<>();
        response.put("Total tasks", totalTasks);
        response.put("Tasks completed", completedPercentage);
        response.put("Incomplete tasks", incompletePercentage);

        return response;
    }
}

