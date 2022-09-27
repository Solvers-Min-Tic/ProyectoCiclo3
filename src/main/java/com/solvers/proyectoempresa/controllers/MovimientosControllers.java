package com.solvers.proyectoempresa.controllers;

import com.solvers.proyectoempresa.entities.MovimientoDinero;
import com.solvers.proyectoempresa.service.IServiceMovimientoDinero;
import com.solvers.proyectoempresa.service.MovimientoService;
import com.solvers.proyectoempresa.service.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

//@RestController //significa que va a responder en formato Json (responde datos, no una vista)
@Controller  //significa que va a responder en formato HTML (responde una vista)
//@RequestMapping("movimientos")
public class MovimientosControllers {
    @Autowired
    private IServiceMovimientoDinero movimientoService;

    // Mostrar los movimientos de una empresa específica por id
    @GetMapping("/empresa/movimientos/{id}") //Presenta la lista de movimientos realizados por una empresa
    public String getAllByEmpresa(@PathVariable int id, Model model){  //PathVariable porque cambia conforme al id
        model.addAttribute("movimientos", movimientoService.findAllByEmpresa(id));
        model.addAttribute("total", movimientoService.montoTotal(id));
        return "movimientosPorEmpresa"; //retorna el archivo HTML con la vista de movimientos
    }


    /*
    // Crear un movimiento de dinero
    @RequestMapping(value = "/movimientos/", method = RequestMethod.POST, produces = "application/json")
    public MovimientoDinero create(@RequestBody MovimientoDinero movimientoDinero) {
        return movimientoService.create(movimientoDinero);
    }

    @RequestMapping("getmovimientosdinero")  //Estos métodos devuelven Json, se cambian por los que devuelven un HTML
    public ArrayList<MovimientoDinero> selectAll(){
        return this.movimientoService.selectAll();


    public MovimientosControllers(MovimientoService service){
        this.movimientoService=service;

    }
    @RequestMapping("/movimiento")
    public String movimientos(){
        return "controlador movimiento";
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
    */
}
