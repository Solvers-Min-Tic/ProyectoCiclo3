package com.solvers.proyectoempresa.controllers;

import com.solvers.proyectoempresa.dto.registroDTO;
import com.solvers.proyectoempresa.entities.Empleado;
import com.solvers.proyectoempresa.entities.TipoDoc;
import com.solvers.proyectoempresa.service.EmpleadoService;
import com.solvers.proyectoempresa.service.Response;
import com.solvers.proyectoempresa.service.TipoDocService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;

@Controller
public class LogueoController {

    private EmpleadoService service;
    private TipoDocService docService;

    public LogueoController(EmpleadoService service, TipoDocService docService){
        this.service = service ;
        this.docService = docService;
    }

    @GetMapping("/")
    public String root(){
        return "login/login";
    }

    @GetMapping("login")
    public String login(){
        return "login/login";
    }
    @GetMapping("registro")
    public String registro(Model tipodoc){
        ArrayList<TipoDoc> tipoDocDB = this.docService.selectAll();
        //thymeleaf
        tipodoc.addAttribute("misdocumentos",tipodoc);
        tipodoc.addAttribute("texto","Bienvenidos");

        return "login/registro";
    }

    @PostMapping("postlogin")
    public RedirectView postlogin(Empleado data){
        Response response = this.service.loginUser(data);
        if(response.getCode() == 200){
            return new RedirectView("/inicio");
        }
        else{
            return new RedirectView("/login/error");
        }
    }

    @PostMapping("postregistro")
    public RedirectView postregisto(registroDTO data){

        if(data.getPassword().equals(null) || data.getPassword().equals("")){
            System.out.println("Contraseña invalida");
            return new RedirectView("/login/error");
        }
        if(!data.getPassword().equals(data.getValidaPassword())){
            System.out.println("Las contraseñas no coinciden.");
            return new RedirectView("/login/error");
        }

        Empleado empleado = new Empleado();

        //Mapping
        empleado.setCorreoElectronico(data.getCorreoElectronico());
        empleado.setPassword(data.getPassword());
        empleado.setNombre(data.getNombre());
        empleado.setNumeroDocumento(data.getNumeroDocumento());


        Response response = this.service.createUser(empleado);
        System.out.println(response.getMessage());
        if(response.getCode() == 200){
            return new RedirectView("/inicio");
        }
        else{
            return new RedirectView("/login/error");
        }
    }

    @GetMapping("error")
    public String error(){
        return "login/error";
    }



}
