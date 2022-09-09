package com.solvers.proyectoempresa.controllers;

import com.solvers.proyectoempresa.entities.Empleado;
import com.solvers.proyectoempresa.service.EmpleadoService;
import com.solvers.proyectoempresa.service.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class EmpleadoController {

    EmpleadoService empleadoService;
    public EmpleadoController(){
        this.empleadoService=empleadoService;
    }

    @GetMapping("/Empleados")
    public List<Empleado> getEmpleadoList(){
        return this.empleadoService.selectAll();
    }

    @PostMapping("/Empleados")
    public Response createEmpleado(@RequestBody Empleado empleado){
        return this.empleadoService.createEmpleado(empleado);
    }

}
