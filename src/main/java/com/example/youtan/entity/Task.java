package com.example.youtan.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Boolean completed = false;

    // Getter para 'id'
    public Long getId() {
        return id;
    }

    // Setter para 'id'
    public void setId(Long id) {
        this.id = id;
    }

    // Getter para 'description'
    public String getDescription() {
        return description;
    }

    // Setter para 'description'
    public void setDescription(String description) {
        this.description = description;
    }

    // Getter para 'completed'
    public Boolean getCompleted() {
        return completed;
    }

    // Setter para 'completed'
    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}
