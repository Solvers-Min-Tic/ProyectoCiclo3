package com.solvers.proyectoempresa.repository;


import com.solvers.proyectoempresa.entities.Empleado;
import com.solvers.proyectoempresa.entities.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmpresaRepository extends JpaRepository<Empresa, Integer> {

}
