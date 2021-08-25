package eci.edu.ieti.lab01.lab01ParteDos.service.impl;

import eci.edu.ieti.lab01.lab01ParteDos.datos.Task;
import eci.edu.ieti.lab01.lab01ParteDos.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@Service
public class TaskServiceHashMap implements TaskService {
    private HashMap<String,Task> tasks= new HashMap<String,Task>();
    @Override
    public Task create(Task task) {
        tasks.put(task.getId(),task);
        return task;
    }

    @Override
    public Task findById(String id) {
        if(tasks.containsKey(id)){
            return tasks.get(id);
        }
        return null;
    }

    @Override
    public List<Task> all() {
        List<Task> taskList = new ArrayList<>();
        for(String id: tasks.keySet()){
            taskList.add(tasks.get(id));
        }
        return taskList;
    }

    @Override
    public void deleteById(String id) {
        tasks.remove(id);
    }

    @Override
    public Task update(Task task, String id) {
        tasks.put(id,task);
        return task;
    }
}
