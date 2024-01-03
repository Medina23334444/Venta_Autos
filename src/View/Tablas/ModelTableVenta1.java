package View.Tablas;

import Controller.AutoController;
import Controller.TDAListas.Expection.VacioExpection;
import Controller.TDAListas.LinkedList;
import Controller.VendedorController;
import Model.Auto;
import Model.Vendedor;
import Model.Venta;
import javax.swing.table.AbstractTableModel;

public class ModelTableVenta1 extends AbstractTableModel {

    private LinkedList<Venta> ventas;
    private AutoController ac = new AutoController();
    private VendedorController vc = new VendedorController();

    @Override
    public int getRowCount() {
        return getVentas().getSize();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Venta venta = null;
        try {
            venta = ventas.get(row);
            venta.getId();
        } catch (VacioExpection e) {
            throw new RuntimeException(e);
        }
        switch (col) {
            case 0:
                return (venta != null) ? venta.getFecha() : "";
            case 1:
                return (venta != null) ? venta.getNroVenta() : "";
            case 2:
                return (venta != null) ? venta.getValorVenta() : "";
            case 3:
              try {
                Vendedor vendedor = vc.busquedaBinaria2(vc.listAll(), venta.getId_vendedor().toString(), "id", "quicksort", 0);
                return (vendedor != null) ? vendedor.getNombre() : "";
            } catch (Exception e) {
            }
            case 4:
              try {
                Auto auto = ac.busquedaBinaria2(ac.listAll(), venta.getId_auto().toString(), "id", "quicksort", 0);
                return (auto != null) ? auto.getModelo() : "";
            } catch (Exception e) {
            }
            default:
                return null;
        }
    }

    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Fecha";
            case 1:
                return "Nro Venta";
            case 2:
                return "Valor Venta";
            case 3:
                return "Nombre";
            case 4:
                return "Modelo";
            default:
                return null;
        }
    }

    /**
     * @return the ventas
     */
    public LinkedList<Venta> getVentas() {
        return ventas;
    }

    /**
     * @param ventas the ventas to set
     */
    public void setVentas(LinkedList<Venta> ventas) {
        this.ventas = ventas;
    }

}
