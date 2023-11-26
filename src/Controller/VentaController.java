/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Controller.TDAListas.LinkedList;
import Controller.Venta_Autos.DataAccessObject;
import Model.Venta;

/**
 *
 * @author Usuario
 */
public class VentaController extends DataAccessObject<Venta> {

    private Venta venta = new Venta();
    private LinkedList<Venta> listVentas = new LinkedList<>();
    private Integer index = -1;

    public VentaController() {
        super(Venta.class);
    }

    public Boolean saved() {
        return save(getVenta());
    }

    public Boolean update1(Integer i) {
        return update(getVenta(), i);
    }

    // Genera el identificador necesario para cada objeto Venta
    // retorna un entero
    public int generatedId() {
        return generarId();
    }

    /**
     *
     * @return the venta
     */
    public Venta getVenta() {
        //Verifica si el objeto  Venta es nulo si no se crea otro objeto Venta
        if (venta == null) {
            venta = new Venta();
        }
        return venta;
    }

    /**
     * @param venta the venta to set
     */
    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    /**
     * @return the listVentas
     */
    public LinkedList<Venta> getListVentas() {
        if (listVentas.isEmpty()) {
            setListVentas(listAll());
        }
        return listVentas;
    }

    /**
     * @param listVentas the listVentas to set
     */
    public void setListVentas(LinkedList<Venta> listVentas) {
        this.listVentas = listVentas;
    }

    /**
     * @return the index
     */
    public Integer getIndex() {
        return index;
    }

    /**
     * @param index the index to set
     */
    public void setIndex(Integer index) {
        this.index = index;
    }

    //Genera un nuevo código para identificar un elemento.
    public String generatedCode() {
        StringBuilder code = new StringBuilder();
        // Se obtiene la longitud actual de la lista y se incrementa en uno
        Integer length = listAll().getSize() + 1;
        Integer pos = Integer.toString(length).length();
        for (int i = 0; i < (10 - pos); i++) {
            code.append("0");
        }
        code.append(length.toString());
        // Se devuelve el código como una cadena de texto
        return code.toString();
    }

}
