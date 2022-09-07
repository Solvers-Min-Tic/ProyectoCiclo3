package com.solvers.proyectoempresa.repository;

import com.solvers.proyectoempresa.entities.MovimientoDinero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMovimientoRepository extends JpaRepository<MovimientoDinero,Integer> {
}
