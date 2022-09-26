package com.solvers.proyectoempresa.service;

import com.solvers.proyectoempresa.entities.Empleado;
import com.solvers.proyectoempresa.repository.IEmpleadoRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class EmpleadoService {

    private IEmpleadoRepository userRepository;

    public EmpleadoService(IEmpleadoRepository rep){
        this.userRepository = rep;
    }

    ///Metodo que me permite obtener todos los usuarios de la base de datos.
    public ArrayList<Empleado> selectAll(){
        return (ArrayList<Empleado>) this.userRepository.findAll();
    }

    public Response createUser(Empleado data){
        Response response = new Response();

        //Logica de negocio
        //Validamos datos
        if(!isValidEmailAddress(data.getCorreoElectronico())){
            response.setCode(500);
            response.setMessage("Error, empleado inválido.");
            return  response;
        }

        //Validamos password
        if(data.getPassword().equals(null) || data.getPassword().equals("")){
            response.setCode(500);
            response.setMessage("Error, contraseña inválida.");
            return  response;
        }

        ArrayList<Empleado> existe = this.userRepository.existeCorreo(data.getCorreoElectronico());
        if(existe != null && existe.size() > 0){
            response.setCode(500);
            response.setMessage("Error, el correo electronico ya esta en uso.");
            return  response;
        }

        ///Encripto contraseña.
        BCryptPasswordEncoder encrypt = new BCryptPasswordEncoder();
        data.setPassword(encrypt.encode(data.getPassword()));

        this.userRepository.save(data);
        response.setCode(200);
        response.setMessage("Usuario registrado exitosamente");
        return response;
    }

    public Empleado selectById(int Id){
        Optional<Empleado> existe = this.userRepository.findById(Id);
        if(existe.isPresent()){
            return existe.get();
        }
        else {
            return null;
        }
    }

    public Empleado selectByUserName(String username){
        Empleado existe = this.userRepository.finByUserName(username);
        return existe;
    }

    public Response deleteUserById(int Id){
        Response response = new Response();
        try {
            this.userRepository.deleteById(Id);
            response.setCode(200);
            response.setMessage("Usuario eliminado exitosamente");
            return response;
        }
        catch (Exception ex){
            response.setCode(500);
            response.setMessage("Error " + ex.getMessage());
            return response;
        }
    }

    public Response updateUser(Empleado data){
        Response response = new Response();


        if(data.getIdEmpleado() == 0){
            response.setCode(500);
            response.setMessage("Error, Id de usuario inválido.");
            return response;
        }

        //Validamos si el usuario que desea actualizar existe.
        Empleado exists = selectById(data.getIdEmpleado());
        if(exists == null){
            response.setCode(500);
            response.setMessage("Error, el usuario no existe en la base de datos.");
            return response;
        }

        if(data.getCorreoElectronico().equals(null) || data.getCorreoElectronico().equals("")){
            response.setCode(500);
            response.setMessage("Error, el correo electronico no es válido.");
            return response;
        }

        if(!isValidEmailAddress(data.getCorreoElectronico())){
            response.setCode(500);
            response.setMessage("Error, el correo electronico no tiene el formato adecuado.");
            return response;
        }

        exists.setCorreoElectronico(data.getCorreoElectronico());

        this.userRepository.save(exists);
        response.setCode(200);
        response.setMessage("Empleado modificado exitosamente");
        return  response;
    }


    public Response updateUserName(Empleado data){
        Response response = new Response();


        if(data.getIdEmpleado() == 0){
            response.setCode(500);
            response.setMessage("Error, Id de usuario inválido.");
            return response;
        }

        //Validamos si el usuario que desea actualizar existe.
        Empleado exists = selectById(data.getIdEmpleado());
        if(exists == null){
            response.setCode(500);
            response.setMessage("Error, usuario no existe en base de datos.");
            return response;
        }

        exists.setNombre(data.getNombre());

        this.userRepository.save(exists);
        response.setCode(200);
        response.setMessage("Usuario modificado exitosamente");
        return  response;
    }

    public Response loginUser(Empleado data){
        Response response = new Response();

        //Logica de negocio
        //Validamos datos
        if(!isValidEmailAddress(data.getCorreoElectronico())){
            response.setCode(500);
            response.setMessage("Error, el usuario dado no es válido.");
            return  response;
        }

        //Validamos password
        if(data.getPassword().equals(null) || data.getPassword().equals("")){
            response.setCode(500);
            response.setMessage("Error, su contraseña no es válida.");
            return  response;
        }

        ArrayList<Empleado> existe = this.userRepository.validaCredenciales(data.getCorreoElectronico(),data.getPassword());
        if(existe != null && existe.size() > 0){
            response.setCode(200);
            response.setMessage("Usuario autenticado exitosamente.");
            return  response;
        }

        response.setCode(500);
        response.setMessage("Error, sus datos de acceso no son válidos");
        return  response;
    }

    public boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }
}
