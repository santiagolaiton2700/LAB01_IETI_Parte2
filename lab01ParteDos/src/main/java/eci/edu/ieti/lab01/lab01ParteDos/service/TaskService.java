package eci.edu.ieti.lab01.lab01ParteDos.service;

import eci.edu.ieti.lab01.lab01ParteDos.datos.Task;

import java.util.List;

public interface TaskService
{
    public Task create(Task task );

    Task findById( String id );

    List<Task> all();

    void deleteById( String id );

    Task update( Task task, String id );
}
