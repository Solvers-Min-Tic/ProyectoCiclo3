package com.solvers.proyectoempresa.service;

import com.solvers.proyectoempresa.entities.TipoDoc;
import com.solvers.proyectoempresa.repository.ITipoDocRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service

public class TipoDocService {

    private ITipoDocRepository repository;

    public TipoDocService(ITipoDocRepository rep){
        this.repository = rep;
    }

    public ArrayList<TipoDoc> selectAll(){
        return (ArrayList<TipoDoc>) this.repository.findAll();
    }

    ///Metodo que permite registrar un documento
    public Response createDocumento(TipoDoc data){

        //validar documento existe
        ArrayList<TipoDoc> documentos = this.repository.findByNombre(data.getNombre());
        if(documentos != null && documentos.size() > 0){
            Response response = new Response();
            response.setCode(500);
            response.setMessage("Este tipo de documento ya existe");
            return response;
        }

        this.repository.save(data);
        Response response = new Response();
        response.setCode(200);
        response.setMessage("Documento registrado exitosamente");
        return response;
    }
}
