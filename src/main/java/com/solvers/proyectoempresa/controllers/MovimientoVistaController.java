package com.solvers.proyectoempresa.controllers;

import com.solvers.proyectoempresa.entities.Empleado;
import com.solvers.proyectoempresa.entities.MovimientoDinero;
import com.solvers.proyectoempresa.service.EmpleadoService;
import com.solvers.proyectoempresa.service.MovimientoService;
import com.solvers.proyectoempresa.service.Response;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;

@Controller
@RequestMapping("movimiento")
public class MovimientoVistaController {
    private MovimientoService movimientoService;
    private EmpleadoService empleadoService;
    public MovimientoVistaController(MovimientoService service, EmpleadoService serviceEmpleado){
        this.movimientoService=service;
        this.empleadoService=serviceEmpleado;

    }
    @GetMapping("nuevo-movimiento")
    public String crearMovimiento(Model empleado){

        ArrayList<Empleado> empleadoDB = this.empleadoService.selectAll();
        empleado.addAttribute("listaEmpleados",empleadoDB);
        return "movimiento/nuevo";
    }
    @PostMapping("nuevo-movimiento")
    public RedirectView createMovimiento(MovimientoDinero request){
        Response response= this.movimientoService.createMovimiento(request);
        if (response.getCode()==200) {
            return new RedirectView("/movimiento/nuevo-movimiento");
        } else {
            return new RedirectView("/movimiento/nuevo-movimiento");
        }


    }
}
