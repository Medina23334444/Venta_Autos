/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Controller.TDAListas.LinkedList;
import Controller.Venta_Autos.DataAccessObject;
import Model.Vendedor;

/**
 *
 * @author Usuario
 */
public class VendedorController extends DataAccessObject<Vendedor> {

    private Vendedor vendedor = new Vendedor();
    private LinkedList<Vendedor> listVendedor = new LinkedList<>();
    private Integer index = -1;

    public VendedorController() {
        super(Vendedor.class);
    }

    public Boolean saved() {
        return save(vendedor);
    }

    public Boolean update1(Integer i) {
        return update(vendedor, i);
    }

    // Buscamos un objeto Vendedor por su identificador
    // retorna un objeto Vendedor o null si no se encuentra
    public Vendedor search(Integer id) {
        return find(id);
    }

    // Genera el identificador necesario para cada objeto Vendedor
    // retorna un entero
    public int generatedId() {
        return generarId();
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

    /**
     * @return the listAutos
     */
    public LinkedList<Vendedor> getListAutos() {
        if (listVendedor.isEmpty()) {
            setListVendedor(listAll());
        }
        return listVendedor;
    }

    /**
     *
     * @return the vendedor
     */
    public Vendedor getVendedor() {
        //Verifica si el objeto Vendedor es nulo si no se crea otro objeto Vendedor
        if (vendedor == null) {
            vendedor = new Vendedor();
        }
        return vendedor;
    }

    /**
     * @param vendedor the vendedor to set
     */
    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    /**
     * @param listVendedor the listVendedor to set
     */
    public void setListVendedor(LinkedList<Vendedor> listVendedor) {
        this.listVendedor = listVendedor;
    }

}
