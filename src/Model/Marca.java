/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import com.thoughtworks.xstream.converters.extended.ToStringConverter;

/**
 *
 * @author Usuario
 */
public class Marca {

    private Integer id;
    private String nombre;
    private Boolean estado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "__" + id + "" + nombre;
    }

    public Boolean comparar(Marca c, String field, Integer type) {

        switch (type) {
            case 1:
                if (field.equalsIgnoreCase("nombre")) {
                    return getNombre().compareTo(c.getNombre()) > 0;
                } else if (field.equalsIgnoreCase("id")) {
                    return getId().intValue() > (c.getId().intValue());

                }

            case 0:
                if (field.equalsIgnoreCase("nombre")) {
                    return getNombre().compareTo(c.getNombre()) < 0;
                } else if (field.equalsIgnoreCase("id")) {
                    return getId().intValue() < (c.getId().intValue());

                }

            default:
                return false;
        }
    }

    public int comparar(Marca marca, String text, String campo) {
        switch (campo.toLowerCase()) {
            case "nombre":
                return text.compareTo(marca.getNombre().toLowerCase());
            case "id":
                try {
                return Integer.compare(Integer.parseInt(text), marca.getId());
            } catch (Exception e) {
                System.out.println("errorr" + e);
            }
            default:
                throw new IllegalArgumentException("Campo de comparación no válido");
        }
    }

}
