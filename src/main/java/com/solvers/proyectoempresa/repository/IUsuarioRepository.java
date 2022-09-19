package com.solvers.proyectoempresa.repository;

import com.solvers.proyectoempresa.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface IUsuarioRepository extends JpaRepository<User,Integer> {

    @Query("SELECT u FROM User u WHERE u.mail1 = ?1 and u.password = ?2")
    ArrayList<User> validaUsuario(String usuario, String password);

    @Query("SELECT u FROM User u WHERE u.mail1 = ?1")
    ArrayList<User> existeMail(String mail1);

}
