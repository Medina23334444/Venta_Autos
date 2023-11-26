/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.Venta_Autos;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;
import java.io.File;

/**
 *
 * @author Usuario
 */
public class Conection {

    private static String URL = "ventas" + File.separatorChar;
    private static XStream xstream;

    private Conection() {
        xstream = new XStream(new JettisonMappedXmlDriver());
    }

    /**
     * @return the URL
     */
    public static String getURL() {
        return URL;
    }

    /**
     * @return the xstream
     */
    public static XStream getXstream() {
        if (xstream == null) {
            xstream = new XStream(new JettisonMappedXmlDriver());
//            xstream.setMode(XStream.NO_REFERENCES);
            xstream.addPermission(AnyTypePermission.ANY );
        }

        return xstream;

    }



}
