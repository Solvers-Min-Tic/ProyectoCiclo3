package com.solvers.proyectoempresa;

public class MovimientoDinero {

    private float monto;
    private String conceptoMovimiento;
    private Empleado empleado;

    public MovimientoDinero(float monto, String conceptoMovimiento, Empleado empleado) {
        this.monto = monto;
        this.conceptoMovimiento = conceptoMovimiento;
        this.empleado = empleado;
    }

    public float getMonto() {
        return monto;
    }

    public String getConceptoMovimiento() {
        return conceptoMovimiento;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public void setConceptoMovimiento(String conceptoMovimiento) {
        this.conceptoMovimiento = conceptoMovimiento;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
}
