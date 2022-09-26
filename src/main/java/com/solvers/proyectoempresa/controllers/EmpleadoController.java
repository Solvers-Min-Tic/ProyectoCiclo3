package com.solvers.proyectoempresa.controllers;

import com.solvers.proyectoempresa.entities.Empleado;
import com.solvers.proyectoempresa.service.EmpleadoService;
import com.solvers.proyectoempresa.service.Response;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("empleados")
public class EmpleadoController {

    private EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService service) {
        this.empleadoService = service;
    }

    @GetMapping("/getempleados")
    public ArrayList<Empleado> getEmpleadoList(){
        return this.empleadoService.selectAll();
    }

    @RequestMapping("getuser/{id}")
    public Empleado getusuario(@PathVariable int id){
        return this.empleadoService.selectById(id);
    }

    @PostMapping("/crearempleados")
    public Response createEmpleado(@RequestBody Empleado request){
        return this.empleadoService.createUser(request);
    }

    @DeleteMapping("delete/{id}")
    public Response deleteEmpleado(@PathVariable int id){
        return this.empleadoService.deleteUserById(id);
    }

    @PutMapping("update")
    public Response updateEmpleado(@RequestBody Empleado request){
        return this.empleadoService.updateUser(request);
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