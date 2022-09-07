package com.solvers.proyectoempresa.service;

import com.solvers.proyectoempresa.entities.MovimientoDinero;
import com.solvers.proyectoempresa.repository.IMovimientoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class MovimientoService {

    private IMovimientoRepository movimientoRepository;

    public MovimientoService(IMovimientoRepository rep){
        this.movimientoRepository = rep;
    }

    public ArrayList<MovimientoDinero> selectAll(){
        return (ArrayList<MovimientoDinero>) this.movimientoRepository.findAll();
    }

    public Response createMovimiento(MovimientoDinero data){
        Response response = new Response();
        this.movimientoRepository.save(data);
        response.setCode(200);
        response.setMessage("Movimiento creado exitosamente");

        return response;
    }

    public MovimientoDinero selectByID(int id){
        Optional<MovimientoDinero> movimiento = this.movimientoRepository.findById(id);
        if (movimiento.isPresent()){
            return movimiento.get();
        }
        else {
            return null;
        }

    }

    public Response deleteMovimientoById(int id){
        this.movimientoRepository.deleteById(id);
        Response response = new Response();
        response.setCode(200);
        response.setMessage("Movimiento eliminado exitosamente");

        return response;


    }

    public Response updateMovimientoById(MovimientoDinero movimiento){

        Response response = new Response();
        if (movimiento.getIdMovimiento() == 0) {
            response.setCode(500);
            response.setMessage("ID Movimiento incorrecto");

            return response;

        }

        MovimientoDinero movimientoEncontrado = selectByID(movimiento.getIdMovimiento());
        if (movimientoEncontrado == null){
            response.setCode(500);
            response.setMessage("ID Movimiento no existe");

            return response;

        }

        movimientoEncontrado.setMonto(movimiento.getMonto());
        this.movimientoRepository.save(movimientoEncontrado);

        response.setCode(200);
        response.setMessage("Movimiento actualizado exitosamente");

        return response;


    }
}
