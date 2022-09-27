package com.solvers.proyectoempresa.service;

import com.solvers.proyectoempresa.entities.MovimientoDinero;
import com.solvers.proyectoempresa.repository.IMovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transaction;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovimientoService implements IServiceMovimientoDinero{
    @Autowired
    IMovimientoRepository movimientoRepository;

    @Override
    public MovimientoDinero create(MovimientoDinero movimientoDinero){  //Crea un movimiento de dinero
        return movimientoRepository.save(movimientoDinero);
    }

    @Override
    public List<MovimientoDinero> findAllByEmpresa(int id) {   //Crea una lista con los movimientos de una empresa
        List<MovimientoDinero> listaMovimientoDinero = new ArrayList<>();
        movimientoRepository.findByEmpresa(id).forEach(listaMovimientoDinero::add);
        return listaMovimientoDinero;
    }

    @Override
    public float montoTotal(int id) {  //Calcula el monto total de los movimientos realizados por una empresa
        return movimientoRepository.montoTotal(id);
    }
    public Response createMovimiento(MovimientoDinero data){
        Response response = new Response();
        this.movimientoRepository.save(data);
        response.setCode(200);
        response.setMessage("Movimiento creado exitosamente");

        return response;
    }
    /*
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

    public Object findAll() {
    } */
}
