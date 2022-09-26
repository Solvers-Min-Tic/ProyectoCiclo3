package com.solvers.proyectoempresa.controllers;

import com.solvers.proyectoempresa.entities.Empleado;
import com.solvers.proyectoempresa.service.Response;
import com.solvers.proyectoempresa.service.EmpleadoService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("index")
public class IndexController {

    //Se coloca una propiedad del Tipo UserSevice, para poder trabajar con la logica de negocio de la aplicacion
    private EmpleadoService empleadoService;
    //Por medio de la inyección de dependencias, se inicializa el sevicio.
    public IndexController(EmpleadoService service){
        this.empleadoService = service;
    }

    @GetMapping("getusuarios")
    public ArrayList<Empleado> getUsuarios(){
        return this.empleadoService.selectAll();
    }

    @RequestMapping("getuser/{id}")
    public Empleado getusuario(@PathVariable int id){
        return this.empleadoService.selectById(id);
    }

    @PostMapping("create")
    public Response createEmpleado(@RequestBody Empleado request){ return this.empleadoService.createUser(request);
    }

    @DeleteMapping("delete/{id}")
    public Response deleteUserById(@PathVariable int id){
        return this.empleadoService.deleteUserById(id);
    }

    @PutMapping("update")
    public Response updateUser(@RequestBody Empleado request){
         return   this.empleadoService.updateUserName(request);
    }

    @PostMapping("auth")
    public Response auth(@RequestBody Empleado request){
        return this.empleadoService.loginUser(request);
    }


    @PatchMapping("update")
    public Response otherUpdate(@RequestBody Empleado request){
        return   this.empleadoService.updateUser(request);
    }
}
