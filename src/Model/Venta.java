/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDate;

/**
 *
 * @author Usuario
 */
public class Venta {

    private Integer id;
    private String fecha;
    private double valorVenta;
    private Integer id_auto;
    private Integer id_vendedor;
    private String nroVenta;

    // generamos una fecha con Local Date
    // retornamos el un String fecha
    public String generarFecha() {
        LocalDate fechaHoy = LocalDate.now();
        fecha = fechaHoy.toString();
        return fecha;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
//        LocalDate fechaHoy = LocalDate.now();
//        fecha = fechaHoy.toString();
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the valorVenta
     */
    public double getValorVenta() {
        return valorVenta;
    }

    /**
     * @param valorVenta the valorVenta to set
     */
    public void setValorVenta(double valorVenta) {
        this.valorVenta = valorVenta;
    }

    /**
     * @return the id_auto
     */
    public Integer getId_auto() {
        return id_auto;
    }

    /**
     * @param id_auto the id_auto to set
     */
    public void setId_auto(Integer id_auto) {
        this.id_auto = id_auto;
    }

    /**
     * @return the id_vendedor
     */
    public Integer getId_vendedor() {
        return id_vendedor;
    }

    /**
     * @param id_vendedor the id_vendedor to set
     */
    public void setId_vendedor(Integer id_vendedor) {
        this.id_vendedor = id_vendedor;
    }

    /**
     * @return the nroVenta
     */
    public String getNroVenta() {
        return nroVenta;
    }

    /**
     * @param nroVenta the nroVenta to set
     */
    public void setNroVenta(String nroVenta) {
        this.nroVenta = nroVenta;
    }


}
