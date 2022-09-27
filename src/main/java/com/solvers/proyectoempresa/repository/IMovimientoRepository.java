package com.solvers.proyectoempresa.repository;

import com.solvers.proyectoempresa.entities.MovimientoDinero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMovimientoRepository extends JpaRepository<MovimientoDinero,Long> {
    //Presenta los movimientos realizados al interior de una empresa
    @Query(value = "select * from MovimientoDinero where id_empresa= ?1", nativeQuery = true)
    public abstract List<MovimientoDinero> findByEmpresa(int id);

    //Permite consultar en SpringBoot el total de movimientos de una empresa específica
    @Query(value = "select sum(monto) from MovimientoDinero where id_empresa= ?1", nativeQuery = true)
    public abstract float montoTotal(int id);

    //@Query representa una consulta en una base de datos por medio de código. select * (todas las columnas) from "Tabla a consultar"
}
