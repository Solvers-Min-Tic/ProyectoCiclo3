package com.solvers.proyectoempresa.controllers;

import com.solvers.proyectoempresa.entities.Empresa;
import com.solvers.proyectoempresa.entities.MovimientoDinero;
import com.solvers.proyectoempresa.service.EmpresaService;
import com.solvers.proyectoempresa.service.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("empresa")
public class EmpresaController {

    EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @GetMapping("/getempresa")
    public List<Empresa> getEmpresaList(){
        return this.empresaService.selectAll();
    }

    @PostMapping("/newempresa")
    public Response createEmpresa(@RequestBody Empresa empresa){
        return this.empresaService.createEmpresa(empresa);
    }

    @DeleteMapping("delete/{id}")
    public Response deleteEmpresa(@PathVariable int id){
        return this.empresaService.deleteEmpresaById(id);
    }

    @PutMapping("update")
    public Response updateEmpresa(@RequestBody Empresa request){
        return this.empresaService.updateEmpresaById(request);
    }
}
