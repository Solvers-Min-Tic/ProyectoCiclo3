package com.solvers.proyectoempresa.controllers;

import com.solvers.proyectoempresa.entities.Empleado;
import com.solvers.proyectoempresa.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController {

    @Autowired
    protected EmpleadoService service;
    protected Empleado seguridad(){
        //ingreso a la información de spring security
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //Mail guardado spring security
        String currentPrincipalName = authentication.getName();
        return this.service.selectByUserName(currentPrincipalName);
    }
}
