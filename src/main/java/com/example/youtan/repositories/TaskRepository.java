package com.example.youtan.repositories;

import com.example.youtan.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByCompleted(boolean completed);

    long countByCompletedTrue();

    long countByCompletedFalse();

    Page<Task> findByDescriptionContaining(String description, Pageable pageable); // Para todas as tarefas
    Page<Task> findByDescriptionContainingAndCompletedTrue(String description, Pageable pageable); // Somente concluídas
    Page<Task> findByDescriptionContainingAndCompletedFalse(String description, Pageable pageable); // Somente não concluídas
}

