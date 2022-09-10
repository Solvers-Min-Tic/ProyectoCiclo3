package com.solvers.proyectoempresa.service;

import com.solvers.proyectoempresa.entities.Empresa;
import com.solvers.proyectoempresa.repository.IEmpresaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class EmpresaService {
    private IEmpresaRepository empresaRepository;

    public EmpresaService(IEmpresaRepository empresaRepository) { //permite la comunicación entre la BD y el código
        this.empresaRepository = empresaRepository;
    }

    public ArrayList<Empresa> selectAll() {
        return (ArrayList<Empresa>) this.empresaRepository.findAll(); // (ArrayList<Empresa>) casteo para convertir el retorno en una lista tipo empresa
    }

    public Response createEmpresa(Empresa empresa){
        this.empresaRepository.save(empresa); //crea una nueva empresa
        Response response = new Response(); //instanciar un response que brinde respuesta al cliente
        response.setCode(200); //establece el código de respuesta al cliente
        response.setMessage("Empresa creada exitosamente");
        return response; //retorna la respuesta al controlador para que la entregue al cliente
    }
}
