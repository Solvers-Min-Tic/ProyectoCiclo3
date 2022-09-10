package com.solvers.proyectoempresa.controllers;

import com.solvers.proyectoempresa.entities.Empresa;
import com.solvers.proyectoempresa.service.EmpresaService;
import com.solvers.proyectoempresa.service.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class EmpresaController {

    EmpresaService empresaService;
    public EmpresaController(){
        this.empresaService=empresaService;
    }

    @GetMapping("/Empresa")
    public List<Empresa> getEmpresaList(){
        return this.empresaService.selectAll();
    }

    @PostMapping("/Empresa")
    public Response createEmpresa(@RequestBody Empresa empresa){
        return this.empresaService.createEmpresa(empresa);
    }

}
