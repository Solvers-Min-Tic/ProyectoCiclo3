package com.solvers.proyectoempresa.service;

import com.solvers.proyectoempresa.entities.Empleado;
import com.solvers.proyectoempresa.repository.IEmpleadoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class EmpleadoService {

    private IEmpleadoRepository empleadoRepository;

    public EmpleadoService (IEmpleadoRepository rep){
        this.empleadoRepository = rep;
    }

    public ArrayList<Empleado> selectAll(){

        return (ArrayList<Empleado>) this.empleadoRepository.findAll();
    }

    public Response createEmpleado(Empleado data){
        Response response = new Response();
        this.empleadoRepository.save(data);
        response.setCode(200);
        response.setMessage("Empleado registrado exitosamente.");
        return response;
    }

    public Empleado selectByID(int id){
        Optional<Empleado> empleado = this.empleadoRepository.findById(id);
        if (empleado.isPresent()){
            return empleado.get();
        }
        else {
            return null;
        }

    }

    public Response deleteEmpleadoById(int id){
        this.empleadoRepository.deleteById(id);
        Response response = new Response();
        response.setCode(200);
        response.setMessage("Empleado eliminado exitosamente");

        return response;


    }

    public Response updateEmpleadoById(Empleado empleado){

        Response response = new Response();
        if (empleado.getIdEmpleado() == 0) {
            response.setCode(500);
            response.setMessage("ID Empleado incorrecto");

            return response;

        }

        Empleado empleadoEncontrado = selectByID(empleado.getIdEmpleado());
        if (empleadoEncontrado == null){
            response.setCode(500);
            response.setMessage("ID Empleado no existe");

            return response;

        }

        empleadoEncontrado.setNombre(empleado.getNombre());
        empleadoEncontrado.setCorreoElectronico(empleado.getCorreoElectronico());
        empleadoEncontrado.setDireccion(empleado.getDireccion());
        empleadoEncontrado.setEmpresa(empleado.getEmpresa());
        empleadoEncontrado.setRol(empleado.getRol());
        this.empleadoRepository.save(empleadoEncontrado);

        response.setCode(200);
        response.setMessage("Empleado actualizado exitosamente");

        return response;


    }

}
