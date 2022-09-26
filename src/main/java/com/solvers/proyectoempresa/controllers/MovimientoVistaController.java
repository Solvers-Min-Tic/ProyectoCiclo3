package com.solvers.proyectoempresa.controllers;

import com.solvers.proyectoempresa.entities.MovimientoDinero;
import com.solvers.proyectoempresa.service.MovimientoService;
import com.solvers.proyectoempresa.service.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("movimiento")
public class MovimientoVistaController {
    private MovimientoService movimientoService;
    public MovimientoVistaController(MovimientoService service){
        this.movimientoService=service;

    }
    @GetMapping("nuevo-movimiento")
    public String crearMovimiento(){
        return "movimiento/nuevo";
    }
    @PostMapping("nuevo-movimiento")
    public RedirectView createMovimiento(MovimientoDinero request){
        Response response= this.movimientoService.createMovimiento(request);
        return new RedirectView("/movimiento/nuevo-movimiento");


    }
}
