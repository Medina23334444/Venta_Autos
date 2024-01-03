package View.Tablas;

import Controller.TDAListas.Expection.VacioExpection;
import Controller.TDAListas.LinkedList;
import Model.Auto;
import javax.swing.table.AbstractTableModel;

public class ModelTableAuto extends AbstractTableModel {

    private LinkedList<Auto> llantas;

    @Override
    public int getRowCount() {
        return getLlantas().getSize();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Auto auto = null;
        try {
            auto = llantas.get(row);
            auto.getId();
        } catch (VacioExpection e) {
            throw new RuntimeException(e);
        }
        switch (col) {
            case 0:
                return (auto != null) ? auto.getId() : "";
            case 4:
                return (auto != null) ? auto.getColor() : "";
            case 1:
                return (auto != null) ? auto.getDescripcion() : "";
            case 2:
                return (auto != null) ? auto.getPrecio() : "";
            case 3:
                return (auto != null) ? auto.getModelo() : "";
            default:
                return null;
        }
    }

    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "ID";
            case 4:
                return "Color";
            case 1:
                return "Descripcion";
            case 2:
                return "Precio";
            case 3:
                return "Modelo";
            default:
                return null;
        }
    }

    /**
     * @return the llantas
     */
    public LinkedList<Auto> getLlantas() {
        return llantas;
    }

    /**
     * @param llantas the llantas to set
     */
    public void setLlantas(LinkedList<Auto> llantas) {
        this.llantas = llantas;
    }

}
