package com.solvers.proyectoempresa.controllers;

import com.solvers.proyectoempresa.entities.Empleado;
import com.solvers.proyectoempresa.entities.Empresa;
import com.solvers.proyectoempresa.service.EmpleadoService;
import com.solvers.proyectoempresa.service.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("empleados")
public class EmpleadoController {

    EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @RequestMapping("/getempleados")
    public ArrayList<Empleado> getEmpleadoList(){
        return this.empleadoService.selectAll();
    }

    @PostMapping("/crearempleados")
    public Response createEmpleado(@RequestBody Empleado empleado){
        return this.empleadoService.createEmpleado(empleado);
    }

    @DeleteMapping("delete/{id}")
    public Response deleteEmpleado(@PathVariable int id){
        return this.empleadoService.deleteEmpleadoById(id);
    }

    @PutMapping("update")
    public Response updateEmpleado(@RequestBody Empleado request){
        return this.empleadoService.updateEmpleadoById(request);
    }
}
