package View.Tablas;

import Model.Auto;
import javax.swing.table.AbstractTableModel;

public class ModelTableAutoBusqueda extends AbstractTableModel {

    private Auto auto1 = new Auto();

    @Override
    public int getRowCount() {
        return 1;
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Auto auto = auto1;
       
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
     * @return the auto
     */
    public Auto getAuto() {
        return auto1;
    }

    /**
     * @param auto the auto to set
     */
    public void setAuto(Auto auto) {
        this.auto1 = auto;
    }
    

}
