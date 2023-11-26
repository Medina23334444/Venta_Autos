/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Controller.TDAListas.LinkedList;
import Controller.Venta_Autos.DataAccessObject;
import Model.Auto;

/**
 *
 * @author Usuario
 */
public class AutoController extends DataAccessObject<Auto> {

    private Auto auto = new Auto();
    private LinkedList<Auto> listAutos = new LinkedList<>();
    private Integer index = -1;

    public AutoController() {
        super(Auto.class);
    }

    /**
     * @return the auto
     */
    public Auto getAuto() {
        //Verifica si el objeto Auto es nulo si no se crea otro objeto Auto
        if (auto == null) {
            auto = new Auto();
        }
        return auto;
    }

    /**
     * @param auto the auto to set
     */
    public void setAuto(Auto auto) {
        this.auto = auto;
    }

    public Boolean saved() {
        return save(auto);
    }

    // Buscamos un objeto Auto por su identificador
    // retorna un objeto Auto o null si no se encuentra
    public Auto search(Integer id) {
        return find(id);
    }
    // Genera el identificador necesario para cada objeto Auto 
    // retorna un entero

    public int generatedId() {
        return generarId();
    }

    public Boolean update1(Integer i) {
        return update(auto, i);
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
    public LinkedList<Auto> getListAutos() {
        if (listAutos.isEmpty()) {
            setListAutos(listAll());
        }
        return listAutos;
    }

    /**
     * @param listAutos the listAutos to set
     */
    public void setListAutos(LinkedList<Auto> listAutos) {
        this.listAutos = listAutos;
    }

}
