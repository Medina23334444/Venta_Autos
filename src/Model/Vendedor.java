/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Usuario
 */
public class Vendedor {

    private Integer id;
    private String DNI;
    private String RUC;
    private String nombre;
    private String telefono;
    private String correo;

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
     * @return the RUC
     */
    public String getRUC() {
        return RUC;
    }

    /**
     * @param RUC the RUC to set
     */
    public void setRUC(String RUC) {
        this.RUC = RUC;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return the DNI
     */
    public String getDNI() {
        return DNI;
    }

    /**
     * @param DNI the DNI to set
     */
    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    @Override
    public String toString() {
        return id + "-" + nombre;

    }

    public Boolean comparar(Vendedor c, String field, Integer type) {

        switch (type) {
            case 1:
                if (field.equalsIgnoreCase("DNI")) {
                    return getDNI().compareTo(c.getDNI()) > 0;
                } else if (field.equalsIgnoreCase("id")) {
                    return this.getId().intValue() > (c.getId().intValue());
                } else if (field.equalsIgnoreCase("RUC")) {
                    return getRUC().compareTo(c.getRUC()) > 0;
                } else if (field.equalsIgnoreCase("nombre")) {
                    return getNombre().compareTo(c.getNombre()) > 0;
                } else if (field.equalsIgnoreCase("correo")) {
                    return getCorreo().compareTo(c.getCorreo()) > 0;
                }

            case 0:
                if (field.equalsIgnoreCase("DNI")) {
                    return getDNI().compareTo(c.getDNI()) < 0;
                } else if (field.equalsIgnoreCase("id")) {
                    return this.getId().intValue() < (c.getId().intValue());
                } else if (field.equalsIgnoreCase("RUC")) {
                    return getRUC().compareTo(c.getRUC()) < 0;
                } else if (field.equalsIgnoreCase("nombre")) {
                    return getNombre().compareTo(c.getNombre()) < 0;
                } else if (field.equalsIgnoreCase("correo")) {
                    return getCorreo().compareTo(c.getCorreo()) < 0;
                }
            default:
                return false;
        }
    }

    public int comparar(Vendedor vendedor, String text, String campo) {
        switch (campo.toLowerCase()) {
            case "correo":
                return text.compareTo(vendedor.getCorreo().toLowerCase());
            case "dni":
                return text.compareTo(vendedor.getDNI().toLowerCase());
            case "nombre":
                return text.compareTo(vendedor.getNombre().toLowerCase());
            case "ruc":
                return text.compareTo(vendedor.getRUC().toLowerCase());
            case "id":
                return Integer.compare(Integer.parseInt(text), vendedor.getId());
            default:
                throw new IllegalArgumentException("Campo de comparación no válido");
        }
    }
}
