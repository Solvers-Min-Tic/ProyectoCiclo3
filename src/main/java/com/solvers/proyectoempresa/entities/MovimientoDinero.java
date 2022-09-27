package com.solvers.proyectoempresa.entities;

/*import com.solvers.proyectoempresa.Empleado;*/

import javax.persistence.*;

@Entity
@Table(name= "movimientodinero")
public class MovimientoDinero {

    @Column(name = "monto")
    private float monto;

    @Column(name = "conceptomovimiento")
    private String conceptoMovimiento;

    @ManyToOne
    @JoinColumn(name="empleado")
    private Empleado empleado;

    @ManyToOne
    @JoinColumn(name="empresa") //Permite asociar el movimiento de dinero a una empresa
    private Empresa empresa;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMovimiento;



    public float getMonto() {
        return monto;
    }

    public String getConceptoMovimiento() {
        return conceptoMovimiento;
    }

   /* public Empleado getEmpleado() {
        return empleado;
    }
    */

    public int getIdMovimiento() {
        return idMovimiento;
    }
    public void setMonto(float monto) {
        this.monto = monto;
    }

    public void setConceptoMovimiento(String conceptoMovimiento) {
        this.conceptoMovimiento = conceptoMovimiento;
    }

   /* public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
    */


    public void setIdMovimiento(int idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

}
