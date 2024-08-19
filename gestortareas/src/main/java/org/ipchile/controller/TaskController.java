package org.ipchile.controller;

import org.ipchile.model.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskController {

    private ArrayList<Task> tareas;

    public TaskController() {
        exampleTasks();
    }

    private void exampleTasks() {
        tareas = new ArrayList<>();
        tareas.add(new Task(1, "Completar informe", "Finalizar el informe trimestral", LocalDate.of(2024, 8, 15), false));
        tareas.add(new Task(2, "Reunión de equipo", "Preparar agenda para la reunión semanal", LocalDate.of(2024, 8, 13), true));
        tareas.add(new Task(3, "Actualizar sitio web", "Actualizar contenido de la página principal", LocalDate.of(2024, 8, 20), false));
        tareas.add(new Task(4, "Revisar presupuesto", "Analizar gastos del mes anterior", LocalDate.of(2024, 8, 18), false));
        tareas.add(new Task(5, "Llamar al cliente", "Seguimiento de la propuesta enviada", LocalDate.of(2024, 8, 14), true));
    }

    public void addTask(String nombre, String descripcion, LocalDate fecha) {
        Task tarea = new Task();
        tarea.setId(getIdMax() + 1);
        tarea.setName(nombre);
        tarea.setDescription(descripcion);
        tarea.setFecha(fecha);
        tareas.add(tarea);
    }

    public List<Task> getAllTask() {
        return tareas;
    }

    public void taskIsComplete(int id) {
        tareas.stream().filter(t -> t.getId() == id).findFirst()
                .ifPresent(t -> t.setComplete(true));
    }

    public void removeTask(int id) {
        tareas.stream().filter(t -> t.getId() == id).findFirst()
                .ifPresent(t -> tareas.remove(t));
    }

    public Task getTask(int id) {
        return tareas.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    public int getIdMax() {
        return tareas.stream()
                .mapToInt(Task::getId)
                .max()
                .orElse(-1);
    }
}
