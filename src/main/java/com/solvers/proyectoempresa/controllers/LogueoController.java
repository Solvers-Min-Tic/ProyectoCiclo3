package com.solvers.proyectoempresa.controllers;


import com.solvers.proyectoempresa.dto.registroDTO;
import com.solvers.proyectoempresa.entities.TipoDoc;
import com.solvers.proyectoempresa.entities.User;
import com.solvers.proyectoempresa.service.Response;
import com.solvers.proyectoempresa.service.TipoDocService;
import com.solvers.proyectoempresa.service.UsuarioService;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;

@Controller
@RequestMapping("login")

public class LogueoController {


    private UsuarioService service;
    private TipoDocService docService;

    public LogueoController(UsuarioService service, TipoDocService docService){
        this.service = service ;
        this.docService = docService;
    }

    @GetMapping("login")
    public String login(){
        return "login/login";
    }

    @GetMapping("registro")
    public String registro(Model tiposdocumento){
        //Cargamos los documentos desde la logica de negocio.
        ArrayList<TipoDoc> tiposDocumentoDB = this.docService.selectAll();
        //Pasamos la infomación al model de thymeleaf
        tiposdocumento.addAttribute("misdocumentos",tiposDocumentoDB);
        tiposdocumento.addAttribute("texto","Bienvenidos");

        return "login/registro";
    }

    @PostMapping("postlogin")
    public RedirectView postlogin(User data){
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
            System.out.println("Contraseña no valida");
            return new RedirectView("/login/error");
        }
        if(!data.getPassword().equals(data.getValidaPassword())){
            System.out.println("Las contraseñas no coinciden.");
            return new RedirectView("/login/error");
        }

        User user = new User();

        //Mapping
        user.setMail(data.getMail());
        user.setPassword(data.getPassword());
        user.setApellidos(data.getApellidos());
        user.setNombres(data.getNombres());
        user.setNumeroDocumento(data.getNumeroDocumento());
        user.setTipoDoc(data.getTipoDoc());

        Response response = this.service.createUser(user);
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
