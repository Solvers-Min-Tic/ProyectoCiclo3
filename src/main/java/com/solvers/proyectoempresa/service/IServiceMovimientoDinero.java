package com.solvers.proyectoempresa.service;

import com.solvers.proyectoempresa.entities.MovimientoDinero;

import java.util.List;

public interface IServiceMovimientoDinero {

    //Crear movimiento de dinero
    public MovimientoDinero create(MovimientoDinero movimientoDinero);
    public Response createMovimiento(MovimientoDinero movimientoDinero);

    //Obtener una lista de los movimientos realizados por una empresa
    public List<MovimientoDinero> findAllByEmpresa(int id);

    //Obtener el monto total de movimientos asociados a una empresa
    public float montoTotal(int id);

}
