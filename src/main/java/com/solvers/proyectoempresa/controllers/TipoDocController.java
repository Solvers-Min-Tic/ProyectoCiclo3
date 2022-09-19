package com.solvers.proyectoempresa.controllers;


import com.solvers.proyectoempresa.entities.TipoDoc;
import com.solvers.proyectoempresa.service.Response;
import com.solvers.proyectoempresa.service.TipoDocService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("tipodoc")
public class TipoDocController {

    private TipoDocService service;

    public TipoDocController(TipoDocService ser){
        this.service = ser;
    }

    @RequestMapping("get")
    public ArrayList<TipoDoc> get(){
        return this.service.selectAll();
    }

    @PostMapping("create")
    public Response create(@RequestBody TipoDoc data){
        return this.service.createDocumento(data);
    }

}
