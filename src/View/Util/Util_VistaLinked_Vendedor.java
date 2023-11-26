package View.Util;

import Controller.TDAListas.Expection.VacioExpection;
import Controller.VendedorController;
import Model.Vendedor;

import javax.swing.JComboBox;

public class Util_VistaLinked_Vendedor {

    public static void cargaVendedor(JComboBox cbxmarca) throws VacioExpection {
        VendedorController vc = new VendedorController();

        cbxmarca.removeAllItems();
        try {
            for (int i = 0; i < vc.getListAutos().getSize(); i++) {
                cbxmarca.addItem(vc.getListAutos().get(i));

            }
        } catch (VacioExpection e) {
            e.printStackTrace();
        }
    }

    public static Vendedor getComboVendedor(JComboBox cbx) {
        return (Vendedor) cbx.getSelectedItem();
    }


    
    

}
