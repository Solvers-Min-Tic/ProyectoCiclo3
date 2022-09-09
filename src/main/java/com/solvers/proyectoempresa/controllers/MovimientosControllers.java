package com.solvers.proyectoempresa.controllers;

import com.solvers.proyectoempresa.entities.MovimientoDinero;
import com.solvers.proyectoempresa.service.MovimientoService;
import com.solvers.proyectoempresa.service.Response;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("movimientos")
public class MovimientosControllers {
    private MovimientoService movimientoService;
    public MovimientosControllers(MovimientoService service){
        this.movimientoService=service;

    }
    @RequestMapping("/movimiento")
    public String movimientos(){
        return "controlador movimiento";
    }

    @RequestMapping("getmovimientosdinero")
    public ArrayList<MovimientoDinero> selectAll(){
        return this.movimientoService.selectAll();
    }

    @RequestMapping("getmovimientodinero/{id}")
    public MovimientoDinero selectId(@PathVariable int id){
        return this.movimientoService.selectByID(id);
    }

    @PostMapping("create")
    public Response createMovimiento(@RequestBody MovimientoDinero request){
        return this.movimientoService.createMovimiento(request);


    }

    @DeleteMapping("delete/{id}")
    public Response deleteMovimiento(@PathVariable int id){
        return this.movimientoService.deleteMovimientoById(id);
    }

    @PutMapping("update")
    public Response updateMovimiento(@RequestBody MovimientoDinero request){
        return this.movimientoService.updateMovimientoById(request);
    }
}
