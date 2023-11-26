package View.Util;


import Controller.MarcaController;
import Controller.TDAListas.Expection.VacioExpection;
import Model.Marca;
import javax.swing.JComboBox;


public class Util_VistaLinked {

    public static void cargaMarca(JComboBox cbxmarca) throws VacioExpection {
        MarcaController mc = new MarcaController();

        cbxmarca.removeAllItems();
        try {
            for (int i = 0; i < mc.getMarcas().getSize(); i++) {
                cbxmarca.addItem(mc.getMarcas().get(i));
            }
        } catch (VacioExpection e) {
            e.printStackTrace();
        }
    }

    public static Marca getComboMarca(JComboBox cbx) {
        return (Marca) cbx.getSelectedItem();
    }

}
