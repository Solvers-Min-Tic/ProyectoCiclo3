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
public class LoginController {

    private EmpleadoService service;
    private TipoDocService docService;

    public LoginController(EmpleadoService service, TipoDocService docService){
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
    public String registro(Model tiposdocumento){
        //Cargamos los documentos desde la logica de negocio.
        ArrayList<TipoDoc> tiposDocumentoDB = this.docService.selectAll();
        //Pasamos la infomación al model de thymeleaf
        tiposdocumento.addAttribute("misdocumentos",tiposDocumentoDB);
        tiposdocumento.addAttribute("texto","Bienvenidos");

        return "login/registro";
    }

    @PostMapping("postlogin")
    public RedirectView postlogin(Empleado data){
        Response response = this.service.loginUser(data);
        if(response.getCode() == 200){
            return new RedirectView("/inicio");
        }
        else{
            return new RedirectView("/error");
        }
    }

    @PostMapping("postregistro")
    public RedirectView postregisto(registroDTO data){

        if(data.getPassword().equals(null) || data.getPassword().equals("")){
            System.out.println("Contraseña no valida");
            return new RedirectView("/error");
        }
        if(!data.getPassword().equals(data.getValidaPassword())){
            System.out.println("Las contraseñas no coinciden.");
            return new RedirectView("/error");
        }

        Empleado user = new Empleado();

        //Mapping
        user.setCorreoElectronico(data.getCorreoElectronico());
        user.setDireccion(data.getDireccion());
        user.setPassword(data.getPassword());
        user.setNombre(data.getNombre());
        user.setNumeroDocumento(data.getNumeroDocumento());
        user.setTipoDoc(data.getTipoDoc());
        user.setRol(data.getRol());

        Response response = this.service.createUser(user);
        System.out.println(response.getMessage());
        if(response.getCode() == 200){
            return new RedirectView("/login");
        }
        else{
            return new RedirectView("/error");
        }
    }

    @GetMapping("error")
    public String error(){
        return "login/error";
    }

}
