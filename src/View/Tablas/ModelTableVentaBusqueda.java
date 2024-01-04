package View.Tablas;

import Controller.AutoController;
import Controller.TDAListas.LinkedList;
import Controller.VendedorController;
import Model.Auto;
import Model.Vendedor;
import Model.Venta;
import javax.swing.table.AbstractTableModel;

public class ModelTableVentaBusqueda extends AbstractTableModel {

    private Venta venta1 = new Venta();
    private AutoController ac = new AutoController();
    private VendedorController vc = new VendedorController();

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
        Venta venta = venta1;       
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
     * @return the venta1
     */
    public Venta getVenta1() {
        return venta1;
    }

    /**
     * @param venta1 the venta1 to set
     */
    public void setVenta1(Venta venta1) {
        this.venta1 = venta1;
    }



}
