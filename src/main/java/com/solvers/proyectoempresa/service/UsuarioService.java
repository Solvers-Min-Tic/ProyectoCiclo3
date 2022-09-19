package com.solvers.proyectoempresa.service;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import com.solvers.proyectoempresa.entities.User;
import com.solvers.proyectoempresa.repository.IUsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;


@Service
public class UsuarioService {


    private final IUsuarioRepository usuarioRepository;

    public UsuarioService(IUsuarioRepository rep){
        this.usuarioRepository = rep;
    }

    ///Metodo que me permite obtener todos los usuarios de la base de datos.
    public ArrayList<User> selectAll(){
        return (ArrayList<User>) this.usuarioRepository.findAll();
    }

    public Response createUser(User data){
        Response response = new Response();

        //Logica de negocio
        //Validamos datos
        if(!isValidEmailAddress(data.getMail())){
            response.setCode(500);
            response.setMessage("Error, Usuario No válido.");
            return  response;
        }

        //Validamos password
        if(data.getPassword().equals(null) || data.getPassword().equals("")){
            response.setCode(500);
            response.setMessage("Error, Contraseña Incorrecta.");
            return  response;
        }

        ArrayList<User> existe = this.usuarioRepository.existeMail(data.getMail());
        if(existe != null && existe.size() > 0){
            response.setCode(500);
            response.setMessage("Error, correo electronico en uso.");
            return  response;
        }

        this.usuarioRepository.save(data);
        response.setCode(200);
        response.setMessage("Usuario registrado exitosamente");
        return response;
    }

    public User selectById(int Id){
        Optional<User> existe = this.usuarioRepository.findById(Id);
        if(existe.isPresent()){
            return existe.get();
        }
        else {
            return null;
        }
    }

    public Response deleteUserById(int Id){
        Response response = new Response();
        try {
            this.usuarioRepository.deleteById(Id);
            response.setCode(200);
            response.setMessage("Usuario eliminado con exito");
            return response;
        }
        catch (Exception ex){
            response.setCode(500);
            response.setMessage("Error " + ex.getMessage());
            return response;
        }
    }

    public Response updateUser(User data){
        Response response = new Response();


        if(data.getId() == 0){
            response.setCode(500);
            response.setMessage("Error, el Id del usuario no es válido.");
            return response;
        }

        //Validamos si el usuario que desea actualizar existe.
        User exists = selectById(data.getId());
        if(exists == null){
            response.setCode(500);
            response.setMessage("Error, Usuario No Existe");
            return response;
        }

        if(data.getMail().equals(null) || data.getMail().equals("")){
            response.setCode(500);
            response.setMessage("Error, correo electronico inválido.");
            return response;
        }

        if(!isValidEmailAddress(data.getMail())){
            response.setCode(500);
            response.setMessage("Error, el correo electronico no tiene el formato adecuado.");
            return response;
        }

        exists.setMail(data.getMail());
        exists.setEdad(data.getEdad());

        this.usuarioRepository.save(exists);
        response.setCode(200);
        response.setMessage("Usuario modificado exitosamente");
        return  response;
    }

    public Response loginUser(User data){
        Response response = new Response();

        //Logica de negocio
        //Validamos datos
        if(!isValidEmailAddress(data.getMail())){
            response.setCode(500);
            response.setMessage("Error, Usuario No Existe");
            return  response;
        }

        //Validamos password
        if(data.getPassword().equals(null) || data.getPassword().equals("")){
            response.setCode(500);
            response.setMessage("Error, su contraseña es incorrecta.");
            return  response;
        }

        ArrayList<User> existe = this.usuarioRepository.validaUsuario(data.getMail(),data.getPassword());
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
