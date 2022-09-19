package com.solvers.proyectoempresa.repository;

import com.solvers.proyectoempresa.entities.TipoDoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository

public interface ITipoDocRepository extends JpaRepository<TipoDoc,Integer> {

    @Query("SELECT t FROM TipoDoc t WHERE t.nombre = ?1")
    ArrayList<TipoDoc> findByNombre(String nombre);

}
