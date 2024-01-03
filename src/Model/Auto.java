/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Usuario
 */
public class Auto {

    private Integer id;
    private String color;
    private double precio;
    private String modelo;
    private String descripcion;
    private String anio_Fabricacion;
    private String tipo_Combustible;
    private Integer id_marca;

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
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return the precio
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * @return the modelo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the anio_Fabricacion
     */
    public String getAnio_Fabricacion() {
        return anio_Fabricacion;
    }

    /**
     * @param anio_Fabricacion the anio_Fabricacion to set
     */
    public void setAnio_Fabricacion(String anio_Fabricacion) {
        this.anio_Fabricacion = anio_Fabricacion;
    }

    /**
     * @return the tipo_Combustible
     */
    public String getTipo_Combustible() {
        return tipo_Combustible;
    }

    /**
     * @param tipo_Combustible the tipo_Combustible to set
     */
    public void setTipo_Combustible(String tipo_Combustible) {
        this.tipo_Combustible = tipo_Combustible;
    }

    /**
     * @return the id_marca
     */
    public Integer getId_marca() {
        return id_marca;
    }

    /**
     * @param id_marca the id_marca to set
     */
    public void setId_marca(Integer id_marca) {
        this.id_marca = id_marca;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return getModelo() + "-" + getColor();
    }

    public Boolean comparar(Auto c, String field, Integer type) {

        switch (type) {
            case 1:
                if (field.equalsIgnoreCase("color")) {
                    return getColor().compareTo(c.getColor()) > 0;
                } else if (field.equalsIgnoreCase("id")) {
                    return this.getId().intValue() > (c.getId().intValue());
                } else if (field.equalsIgnoreCase("modelo")) {
                    return getModelo().compareTo(c.getModelo()) > 0;
                } else if (field.equalsIgnoreCase("precio")) {
                    return this.getPrecio() > (c.getPrecio());
                } else if (field.equalsIgnoreCase("descripcion")) {
                    return getDescripcion().compareTo(c.getDescripcion()) > 0;
                }

            case 0:
                if (field.equalsIgnoreCase("color")) {
                    return getColor().compareTo(c.getColor()) < 0;
                } else if (field.equalsIgnoreCase("id")) {
                    return this.getId().intValue() < (c.getId().intValue());
                } else if (field.equalsIgnoreCase("modelo")) {
                    return getModelo().compareTo(c.getModelo()) < 0;
                } else if (field.equalsIgnoreCase("precio")) {
                    return this.getPrecio() < (c.getPrecio());
                } else if (field.equalsIgnoreCase("descripcion")) {
                    return getDescripcion().compareTo(c.getDescripcion()) < 0;
                }
            default:
                return false;
        }
    }

    public int comparar(Auto auto, String text, String campo) {
        switch (campo.toLowerCase()) {
            case "modelo":
                return text.compareTo(auto.getModelo().toLowerCase());
            case "color":
                return text.compareTo(auto.getColor().toLowerCase());
            case "precio":
                return Double.compare(Double.parseDouble(text), auto.getPrecio());
            case "descripcion":
                return text.compareTo(auto.getDescripcion().toLowerCase());
            case "id":
                try {
                return Integer.compare(Integer.parseInt(text), auto.getId());
            } catch (Exception e) {
                System.out.println("errorr" + e);
            }
            default:
                throw new IllegalArgumentException("Campo de comparación no válido");
        }
    }

}
