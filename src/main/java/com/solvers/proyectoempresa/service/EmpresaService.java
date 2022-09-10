package com.solvers.proyectoempresa.service;

import com.solvers.proyectoempresa.entities.Empresa;
import com.solvers.proyectoempresa.repository.IEmpresaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class EmpresaService {

    private IEmpresaRepository empresaRepository;

    public EmpresaService (IEmpresaRepository rep){
        this.empresaRepository = rep;
    }

    public ArrayList<Empresa> selectAll(){
        return (ArrayList<Empresa>) this.empresaRepository.findAll();
    }

    public Response createEmpresa(Empresa data){
        Response response = new Response();
        this.empresaRepository.save(data);
        response.setCode(200);
        response.setMessage("Empresa registrada exitosamente.");
        return response;
    }

    public Empresa selectByID(int id){
        Optional<Empresa> empresa = this.empresaRepository.findById(id);
        if (empresa.isPresent()){
            return empresa.get();
        }
        else {
            return null;
        }

    }

    public Response deleteEmpresaById(int id){
        this.empresaRepository.deleteById(id);
        Response response = new Response();
        response.setCode(200);
        response.setMessage("Empresa eliminada exitosamente");

        return response;


    }

    public Response updateEmpresaById(Empresa empresa){

        Response response = new Response();
        if (empresa.getIdEmpresa() == 0) {
            response.setCode(500);
            response.setMessage("ID Empresa incorrecta");

            return response;

        }

        Empresa empresaEncontrado = selectByID(empresa.getIdEmpresa());
        if (empresaEncontrado == null){
            response.setCode(500);
            response.setMessage("ID Empresa no existe");

            return response;

        }

        empresaEncontrado.setNombre(empresa.getNombre());
        empresaEncontrado.setDireccion(empresa.getDireccion());
        empresaEncontrado.setTelefono(empresa.getTelefono());
        empresaEncontrado.setNit(empresa.getNit());
        this.empresaRepository.save(empresaEncontrado);

        response.setCode(200);
        response.setMessage("Empresa actualizada exitosamente");

        return response;


    }

}
