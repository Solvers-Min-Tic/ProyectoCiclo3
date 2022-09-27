package com.solvers.proyectoempresa.controllers;

import com.solvers.proyectoempresa.entities.Empleado;
import com.solvers.proyectoempresa.entities.Empresa;
import com.solvers.proyectoempresa.entities.MovimientoDinero;
import com.solvers.proyectoempresa.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;

//@RestController //significa que va a responder en formato Json (responde datos, no una vista)
@Controller  //significa que va a responder en formato HTML (responde una vista)
//@RequestMapping("movimientos")
public class MovimientosControllers {
    @Autowired
    private IServiceMovimientoDinero movimientoService;
    @Autowired
    private EmpleadoService empleadoService;
    @Autowired
    private EmpresaService empresaService;

    // Mostrar los movimientos de una empresa específica por id
    @GetMapping("/empresa/movimientos/{id}") //Presenta la lista de movimientos realizados por una empresa
    public String getAllByEmpresa(@PathVariable int id, Model model){  //PathVariable porque cambia conforme al id
        model.addAttribute("movimientos", movimientoService.findAllByEmpresa(id));
        model.addAttribute("total", movimientoService.montoTotal(id));
        return "movimientosPorEmpresa"; //retorna el archivo HTML con la vista de movimientos
    }
    @GetMapping("/empresa/nuevo-movimiento")
    public String crearMovimiento(Model empleado, Model empresa){

        ArrayList<Empleado> empleadoDB = this.empleadoService.selectAll();
        ArrayList<Empresa> empresasDB = this.empresaService.selectAll();
        empleado.addAttribute("listaEmpleados",empleadoDB);
        empresa.addAttribute("listaEmpresas",empresasDB);
        return "movimiento/nuevo";
    }
    @PostMapping("/empresa/nuevo-movimiento")
    public RedirectView createMovimiento(MovimientoDinero request){
        Response response= this.movimientoService.createMovimiento(request);
        if (response.getCode()==200) {
            return new RedirectView("/empresa/nuevo-movimiento");
        } else {
            return new RedirectView("/empresa/nuevo-movimiento");
        }


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
