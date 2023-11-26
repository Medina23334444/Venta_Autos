package View.Util;

import Controller.AutoController;

import Controller.TDAListas.Expection.VacioExpection;
import Model.Auto;
import javax.swing.JComboBox;

public class Util_VistaLinked1_Auto {

    public static void cargaAutos(JComboBox cbxmarca) throws VacioExpection {
        AutoController ac = new AutoController();
        cbxmarca.removeAllItems();
        try {
            for (int i = 0; i < ac.getListAutos().getSize(); i++) {
                cbxmarca.addItem(ac.getListAutos().get(i));
            }
        } catch (VacioExpection e) {
            e.printStackTrace();
        }
    }

    public static Auto getComboAuto(JComboBox cbx) {
        return (Auto) cbx.getSelectedItem();
    }

}
