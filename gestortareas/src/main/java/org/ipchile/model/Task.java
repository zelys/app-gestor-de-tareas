package org.ipchile.model;

import java.time.LocalDate;

public class Task {

    private int id;
    private String name;
    private String description;
    private LocalDate deadline;
    private Boolean complete = false;

    public Task() {
    }

    public Task(int id, String name, String description, LocalDate deadline, Boolean complete) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.complete = complete;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getFecha() {
        return deadline;
    }

    public void setFecha(LocalDate fechaLimite) {
        this.deadline = fechaLimite;
    }

    public String getEstado() {
        return this.complete ? "Completa" : "Pendiente";
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }

    public boolean getComplete() {
        return complete;
    }
}

