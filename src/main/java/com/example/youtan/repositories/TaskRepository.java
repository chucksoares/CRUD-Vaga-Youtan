package com.example.youtan.repositories;

import com.example.youtan.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByCompleted(boolean completed);

    long countByCompletedTrue();

    long countByCompletedFalse();
}

