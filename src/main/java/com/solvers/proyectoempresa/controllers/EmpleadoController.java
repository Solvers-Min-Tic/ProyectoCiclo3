package com.solvers.proyectoempresa.controllers;

import com.solvers.proyectoempresa.entities.Empleado;
import com.solvers.proyectoempresa.service.EmpleadoService;
import com.solvers.proyectoempresa.service.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("empleados")
public class EmpleadoController {

    EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
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
