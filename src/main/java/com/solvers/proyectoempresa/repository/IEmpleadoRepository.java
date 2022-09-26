package com.solvers.proyectoempresa.repository;


import com.solvers.proyectoempresa.entities.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface IEmpleadoRepository extends JpaRepository<Empleado,Integer> {


    @Query("SELECT u FROM User u WHERE u.correoElectronico = ?1 and u.password = ?2")
    ArrayList<Empleado> validaCredenciales(String usuario,String password);

    @Query("SELECT u FROM User u WHERE u.correoElectronico = ?1")
    ArrayList<Empleado> existeCorreo(String correoElectronico);


    @Query("SELECT u FROM User u WHERE u.correoElectronico = ?1")
    Empleado finByUserName(String correoElectronico);

}