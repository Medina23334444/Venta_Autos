package View.Tablas;

import Controller.TDAListas.Expection.VacioExpection;
import Controller.TDAListas.LinkedList;
import Model.Auto;
import Model.Vendedor;
import javax.swing.table.AbstractTableModel;





public class ModelTableVendedor extends AbstractTableModel {

    private LinkedList<Vendedor> vendedor;

    @Override
    public int getRowCount() {
        return getVendedor().getSize();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int row, int col) {
     Vendedor seller = null;
        try {
           seller = vendedor.get(row);
           seller.getId();
            System.out.println(""+ seller.getId());
        } catch (VacioExpection e) {
            throw new RuntimeException(e);
        }
        switch (col) {
            case 0:
                return (seller != null) ? seller.getDNI() : "";
            case 1:
                return (seller != null) ? seller.getRUC() : "";
            case 2:
                return (seller != null) ? seller.getNombre(): "";
            case 3:
                return (seller != null) ? seller.getTelefono() : "";
            default:
                return null;
        }
    }

    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "DNI";
            case 1:
                return "RUC";
            case 2:
                return "Nombre";
            case 3:
                return "Telefono";
            default:
                return null;
        }
    }

    /**
     * @return the vendedor
     */
    public LinkedList<Vendedor> getVendedor() {
        return vendedor;
    }

    /**
     * @param vendedor the vendedor to set
     */
    public void setVendedor(LinkedList<Vendedor> vendedor) {
        this.vendedor = vendedor;
    }

  

}
