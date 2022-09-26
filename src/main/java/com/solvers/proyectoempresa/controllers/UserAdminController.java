package com.solvers.proyectoempresa.controllers;

import com.solvers.proyectoempresa.dto.updateUserDTO;
import com.solvers.proyectoempresa.entities.Empleado;
import com.solvers.proyectoempresa.entities.TipoDoc;
import com.solvers.proyectoempresa.service.Response;
import com.solvers.proyectoempresa.service.TipoDocService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;

@Controller
@RequestMapping("admin")
public class UserAdminController extends BaseController {

    //private UserService service;
    private TipoDocService docService;


    public UserAdminController(TipoDocService documentoService){
        //this.service = service;
        this.docService = documentoService;
    }

    @GetMapping("usuarios")
    public String usuariosregistrados(Model usuarios){
        ArrayList<Empleado> usersDB = this.service.selectAll();
        usuarios.addAttribute("usuarios",usersDB);
        usuarios.addAttribute("usuarioautenticado",seguridad());
        return "/useradmin/usuariosregistrados";
    }

    @GetMapping("edituser/{id}")
    public String edituser(@PathVariable Integer id, Model data){
        Empleado userinfo = this.service.selectById(id);
        ArrayList<TipoDoc> documentos = docService.selectAll();

        data.addAttribute("user",userinfo);
        data.addAttribute("misdocumentos",documentos);

        return "useradmin/edituser";
    }

    @PostMapping("edituserpost")
    public RedirectView edituserpost(updateUserDTO data){

        //Mapping de los datos
        Empleado newUser = new Empleado();
        newUser.setIdEmpleado(data.getId());
        newUser.setNombre(data.getNombre());
        Response response = this.service.updateUserName(newUser);
        return  new RedirectView("/admin/usuarios");
    }

    @GetMapping("deleteuser/{id}")
    public RedirectView deteleUser(@PathVariable int id){
        Response response = this.service.deleteUserById(id);
        return  new RedirectView("/admin/usuarios");
    }


    @GetMapping("tiposdocumento")
    public String tipodocumento(Model data)
    {
        data.addAttribute("tiposdocumento",this.docService.selectAll());
        data.addAttribute("titulopagina","Administración documentos");
        return "useradmin/tipodocumento";
    }

}
