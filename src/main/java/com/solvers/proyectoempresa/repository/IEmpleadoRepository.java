package com.solvers.proyectoempresa.repository;


import com.solvers.proyectoempresa.entities.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public abstract class IEmpleadoRepository implements JpaRepository<Empleado,Integer> {
    public IEmpleadoRepository() {
    }
    @Query("SELECT u FROM Empleado u WHERE u.correoElectronico = ?1 and u.password = ?2")
    public abstract ArrayList<Empleado> validaCredenciales(String usuario, String password);

    @Query("SELECT u FROM Empleado u WHERE u.correoElectronico = ?1")
    public abstract ArrayList<Empleado> existeCorreo(String correoElectronico);


    @Query("SELECT u FROM Empleado u WHERE u.correoElectronico = ?1")
    public abstract Empleado finByUserName(String correoElectronico);
}