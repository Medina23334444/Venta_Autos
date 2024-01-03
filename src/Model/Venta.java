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

    public Boolean comparar(Venta c, String field, Integer type) {

        switch (type) {
            case 1:
                if (field.equalsIgnoreCase("Fecha")) {
                    return getFecha().compareTo(c.getFecha()) > 0;
                } else if (field.equalsIgnoreCase("id")) {
                    return this.getId().intValue() > (c.getId().intValue());
                } else if (field.equalsIgnoreCase("valorVenta")) {
                    return getValorVenta() > c.getValorVenta();
                } else if (field.equalsIgnoreCase("nroVenta")) {
                    return getNroVenta().compareTo(c.getNroVenta()) > 0;
                }

            case 0:
                if (field.equalsIgnoreCase("Fecha")) {
                    return getFecha().compareTo(c.getFecha()) < 0;
                } else if (field.equalsIgnoreCase("id")) {
                    return this.getId().intValue() < (c.getId().intValue());
                } else if (field.equalsIgnoreCase("valorVenta")) {
                    return getValorVenta() < c.getValorVenta();
                } else if (field.equalsIgnoreCase("nroVenta")) {
                    return getNroVenta().compareTo(c.getNroVenta()) < 0;
                }
            default:
                return false;
        }
    }
    
     public int comparar(Venta venta, String text, String campo) {
        switch (campo.toLowerCase()) {
            case "fecha":
                return text.compareTo(venta.getFecha().toLowerCase());
            case "nroventa":
                return text.compareTo(venta.getNroVenta().toLowerCase());
            case "valorventa":
                return Double.compare(Double.parseDouble(text), venta.getValorVenta());
            case "id":
                return Integer.compare(Integer.parseInt(text), venta.getId());
            default:
                throw new IllegalArgumentException("Campo de comparacion no valido");
        }
    }

}
