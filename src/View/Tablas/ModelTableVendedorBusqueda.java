package View.Tablas;

import Controller.TDAListas.Expection.VacioExpection;
import Controller.TDAListas.LinkedList;
import Model.Vendedor;
import javax.swing.table.AbstractTableModel;

public class ModelTableVendedorBusqueda extends AbstractTableModel {

   private Vendedor vendedor = new Vendedor();

    @Override
    public int getRowCount() {
        return 1;
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Vendedor seller = vendedor;
       
        switch (col) {

            case 0:
                return (seller != null) ? seller.getId() : "";
            case 1:
                return (seller != null) ? seller.getRUC() : "";
            case 2:
                return (seller != null) ? seller.getNombre() : "";
            case 3:
                return (seller != null) ? seller.getTelefono() : "";
            case 4:
                return (seller != null) ? seller.getCorreo() : "";
            case 5:
                return (seller != null) ? seller.getDNI() : "";
            default:
                return null;
        }
    }

    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "ID";
            case 1:
                return "RUC";
            case 2:
                return "Nombre";
            case 3:
                return "Telefono";
            case 4:
                return "Correo";
            case 5:
                return "DNI";
            default:
                return null;
        }
    }

    /**
     * @return the vendedor
     */
    public Vendedor getVendedor() {
        return vendedor;
    }

    /**
     * @param vendedor the vendedor to set
     */
    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

   

}
