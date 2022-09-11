package com.solvers.proyectoempresa.service;

import com.solvers.proyectoempresa.entities.Empresa;
import com.solvers.proyectoempresa.entities.MovimientoDinero;
import com.solvers.proyectoempresa.repository.IEmpresaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class EmpresaService {
    private IEmpresaRepository empresaRepository;

    public EmpresaService(IEmpresaRepository empresaRepository) { //permite la comunicación entre la BD y el código
        this.empresaRepository = empresaRepository;
    }

    public ArrayList<Empresa> selectAll() {
        return (ArrayList<Empresa>) this.empresaRepository.findAll(); // (ArrayList<Empresa>) casteo para convertir el retorno en una lista tipo empresa
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
    public Response createEmpresa(Empresa empresa){
        this.empresaRepository.save(empresa); //crea una nueva empresa
        Response response = new Response(); //instanciar un response que brinde respuesta al cliente
        response.setCode(200); //establece el código de respuesta al cliente
        response.setMessage("Empresa creada exitosamente");
        return response; //retorna la respuesta al controlador para que la entregue al cliente
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
            response.setMessage("ID Empresa incorrecto");

            return response;

        }

        Empresa empresaEncontrada = selectByID(empresa.getIdEmpresa());
        if (empresaEncontrada == null){
            response.setCode(500);
            response.setMessage("ID Empresa no existe");

            return response;

        }

        empresaEncontrada.setNombre(empresa.getNombre());
        this.empresaRepository.save(empresaEncontrada);

        response.setCode(200);
        response.setMessage("Empresa actualizada exitosamente");

        return response;


    }
}
