package eci.edu.ieti.lab01.lab01ParteDos.controller;

import eci.edu.ieti.lab01.lab01ParteDos.datos.Task;
import eci.edu.ieti.lab01.lab01ParteDos.dto.TaskDto;
import eci.edu.ieti.lab01.lab01ParteDos.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("v2/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody TaskDto taskDto){
        try{
            taskService.create(new Task(Integer.toString(taskService.all().size()+1),taskDto.getName(),taskDto.getDescription(),taskDto.getStatus(),taskDto.getAssignedTo(),taskDto.getDueDate(),taskDto.getCreated()));
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception ex){
            Logger.getLogger(TaskController.class.getName()).log(Level.SEVERE,null,ex);
            return new ResponseEntity<>("No se a podido crear la tarea",HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping(method = RequestMethod.GET,path = {"/{id}"})
    public ResponseEntity<Task>findById(@PathVariable("id") String id){
        try{
            return new ResponseEntity<>(taskService.findById(id),HttpStatus.CREATED);
        }catch (Exception ex){
            Logger.getLogger(TaskController.class.getName()).log(Level.SEVERE,null,ex);
            return new ResponseEntity("No se encontro tarea por el id : "+id,HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Task>all(){
        try{
            return new ResponseEntity(taskService.all(),HttpStatus.CREATED);
        }catch (Exception ex){
            Logger.getLogger(TaskController.class.getName()).log(Level.SEVERE,null,ex);
            return new ResponseEntity("No se encontraron tareas",HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping(method = RequestMethod.PUT,path = {"/id"})
    public ResponseEntity<Task>update(@RequestBody TaskDto taskDto,@PathVariable("id")String id ){
        try{
            taskService.update(new Task(id,taskDto.getName(),taskDto.getDescription(),taskDto.getStatus(),taskDto.getAssignedTo(), LocalDateTime.now(),true),id);
            return new ResponseEntity(HttpStatus.CREATED);
        }catch (Exception ex){
            Logger.getLogger(TaskController.class.getName()).log(Level.SEVERE,null,ex);
            return new ResponseEntity("No se a podido actulizar la tarea",HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping(method = RequestMethod.DELETE,path = {"/id"})
    public ResponseEntity<Task>delete(@PathVariable("id")String id ){
        try{
            taskService.deleteById(id);
            return new ResponseEntity(HttpStatus.CREATED);
        }catch (Exception ex){
            Logger.getLogger(TaskController.class.getName()).log(Level.SEVERE,null,ex);
            return new ResponseEntity("No se a podido eliminar la tarea",HttpStatus.NOT_FOUND);
        }
    }

}
