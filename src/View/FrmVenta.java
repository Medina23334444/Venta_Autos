/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Controller.TDAListas.Expection.VacioExpection;
import Controller.TDAListas.LinkedList;
import Controller.VentaController;

import View.Tablas.ModelTableVenta1;
import View.Tablas.ModelTableVentaBusqueda;
import View.Util.Util_VistaLinked1_Auto;
import View.Util.Util_VistaLinked_Vendedor;
import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author Usuario
 */
public class FrmVenta extends javax.swing.JFrame {

    private VentaController vc = new VentaController();
    private ModelTableVenta1 mtv = new ModelTableVenta1();
    private ModelTableVentaBusqueda a = new ModelTableVentaBusqueda();

    /**
     * Creates new form Venta
     */
    public FrmVenta() {
        initComponents();
        limpiar();
    }

    private void buscar() throws VacioExpection {

        String criterio = cbxCri.getSelectedItem().toString().toLowerCase();
        String tipo = cbxTipo.getSelectedItem().toString();
        Integer ascdes = cbxForm.getSelectedIndex();
        String text = txtBusqueda.getText();
        String busqueda = cbxTipoBus.getSelectedItem().toString();
        try {

            if (criterio.equalsIgnoreCase("auto")) {
                criterio = "id_auto";
                text = Util_VistaLinked1_Auto.getComboAuto(cbxAuto1).getId().toString();
            } else if (criterio.equalsIgnoreCase("vendedor")) {
                criterio = "id_vendedor";
                text = Util_VistaLinked_Vendedor.getComboVendedor(cbxVendedor1).getId().toString();
            }

            if (busqueda.equalsIgnoreCase("BusquedaBinaria")) {
                a.setVenta1(vc.busquedaBinaria2(vc.listAll(), text, criterio, tipo, ascdes));
                tblVenta.setModel(a);
                tblVenta.updateUI();

            } else {
                mtv.setVentas(vc.buscarlinealBinario(vc.listAll(), text, criterio, tipo));
                tblVenta.setModel(mtv);
                tblVenta.updateUI();
            }

        } catch (Exception e) {
            System.out.println("erooor" + e);
        }

    }

    private void ordenar() {
        String criterio = cbxCri.getSelectedItem().toString().toLowerCase();
        String criterio1 = cbxTipo.getSelectedItem().toString().toLowerCase();
        Integer ascdes = cbxForm.getSelectedIndex();
        try {
            long tiempoInicio = System.nanoTime();

            if (criterio.equalsIgnoreCase("auto")) {
                criterio = "id_auto";
            } else if (criterio.equalsIgnoreCase("vendedor")) {
                criterio = "id_vendedor";
            }
            if (criterio1.equalsIgnoreCase("MergeSort")) {
                mtv.setVentas(vc.mergeSort(vc.getListVentas(), ascdes, criterio));
            } else {
                mtv.setVentas(vc.quicksort(vc.getListVentas(), ascdes, criterio));
            }
            long tiempoFin = System.nanoTime();
            double tiempoTotal = (tiempoFin - tiempoInicio) / 1e6;
            System.out.println("Tiempo total de ordenacion: " + tiempoTotal + " milisegundos");
            tblVenta.setModel(mtv);
            tblVenta.updateUI();
        } catch (Exception e) {
            System.out.println("Errooorrrr" + e);
        }

    }

    public void cargarTabla() {
        mtv.setVentas(vc.getListVentas());
        tblVenta.setModel(mtv);
        tblVenta.updateUI();
    }

    public Boolean validar() {
        return !txtFecha.getText().trim().isEmpty()
                && !txtValor.getText().trim().isEmpty();
    }

    private void limpiar() {
        // enviamos al txt, el codigo generado en el controller
        txtNroVenta.setText(vc.generatedCode());
        // enviamos al txt, la fecha generada desde el modelo
        txtFecha.setText(vc.getVenta().generarFecha());
        txtFecha.setEnabled(false);
        txtValor.setText("");
        vc.setVenta(null);
        vc.setListVentas(new LinkedList<>());
        try {
            Util_VistaLinked1_Auto.cargaAutos(cbxAuto);
            Util_VistaLinked_Vendedor.cargaVendedor(cbxVendedor);
            Util_VistaLinked1_Auto.cargaAutos(cbxAuto1);
            Util_VistaLinked_Vendedor.cargaVendedor(cbxVendedor1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        cargarTabla();
        vc.setIndex(-1);
    }

    public void guardar() {
        if (validar()) {
            try {
                vc.getVenta().setFecha(txtFecha.getText());
                vc.getVenta().setNroVenta(txtNroVenta.getText());
                vc.getVenta().setValorVenta(Double.parseDouble(txtValor.getText()));
                vc.getVenta().setId_auto(Util_VistaLinked1_Auto.getComboAuto(cbxAuto).getId());
                // enviamos el id, utilizando el generar id del controllador
                vc.getVenta().setId(vc.generatedId());
                vc.getVenta().setId_vendedor(Util_VistaLinked_Vendedor.getComboVendedor(cbxVendedor).getId());

                if (vc.saved()) {
                    limpiar();
                    JOptionPane.showMessageDialog(null, "Se guardo correctamente", "OK", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo guardar", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (Exception e) {
                System.out.println(e + "errrror");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Complete todos los campos");
        }
    }

    private void cargarVista() {
        vc.setIndex(tblVenta.getSelectedRow());
        if (vc.getIndex() < 0) {
            JOptionPane.showMessageDialog(null, "No se puede guardar correctamente");
        } else {
            try {
                vc.setVenta(mtv.getVentas().get(vc.getIndex()));
                cbxAuto.setEnabled(false);
                cbxVendedor.setEnabled(false);
                txtFecha.setText(vc.getVenta().getFecha());
                txtNroVenta.setText(vc.getVenta().getNroVenta());
                txtValor.setText(String.valueOf(vc.getVenta().getValorVenta()));
            } catch (Exception e) {
                System.out.println(e + "Errooor");
            }

        }
    }

    // Modifica los detalles de un vendedor en la archivo json 
    // Muestra un mensaje de éxito o error según el resultado de la modificación.
    private void modificar() {
        try {
            vc.getVenta().setValorVenta(Double.parseDouble(txtValor.getText()));
            if (vc.update1(vc.getIndex())) {
                JOptionPane.showMessageDialog(null, "Se ha modificado correctamente", "OK", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo modificar", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            System.out.println(e + "Errooor");
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollBar1 = new javax.swing.JScrollBar();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        cbxVendedor = new javax.swing.JComboBox<>();
        cbxAuto = new javax.swing.JComboBox<>();
        txtFecha = new javax.swing.JTextField();
        txtValor = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtNroVenta = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblVenta = new javax.swing.JTable();
        cbxTipo = new javax.swing.JComboBox<>();
        cbxCri = new javax.swing.JComboBox<>();
        cbxForm = new javax.swing.JComboBox<>();
        txtBusqueda = new javax.swing.JTextField();
        cbxTipoBus = new javax.swing.JComboBox<>();
        cbxVendedor1 = new javax.swing.JComboBox<>();
        cbxAuto1 = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Registro Ventas"));

        cbxVendedor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbxAuto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Britannic Bold", 0, 18)); // NOI18N
        jLabel1.setText("Numero de Venta:");

        txtNroVenta.setText("jLabel2");

        jLabel2.setText("Vendedor:");

        jLabel3.setText("Fecha:");

        jLabel4.setText("Modelo Auto:");

        jLabel5.setText("Valor Venta:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(129, 129, 129)
                        .addComponent(jLabel1)
                        .addGap(31, 31, 31)
                        .addComponent(txtNroVenta))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbxVendedor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbxAuto, 0, 176, Short.MAX_VALUE)
                            .addComponent(txtValor))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNroVenta))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxAuto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Tabla Ventas"));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblVenta.setBackground(new java.awt.Color(204, 204, 255));
        tblVenta.setForeground(new java.awt.Color(51, 51, 51));
        tblVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblVenta);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 118, 600, 199));

        cbxTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MergeSort", "QuickSort" }));
        jPanel2.add(cbxTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(245, 42, 150, -1));

        cbxCri.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Fecha", "ValorVenta", "nroVenta", "id", "Auto", "Vendedor" }));
        cbxCri.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxCriItemStateChanged(evt);
            }
        });
        cbxCri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxCriActionPerformed(evt);
            }
        });
        jPanel2.add(cbxCri, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 40, 123, -1));

        cbxForm.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ascendente", "Descendente" }));
        cbxForm.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxFormItemStateChanged(evt);
            }
        });
        jPanel2.add(cbxForm, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 90, 123, -1));

        txtBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBusquedaActionPerformed(evt);
            }
        });
        jPanel2.add(txtBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 216, 30));

        cbxTipoBus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "BusquedaBinaria", "BusquedaBinariaLineal" }));
        jPanel2.add(cbxTipoBus, new org.netbeans.lib.awtextra.AbsoluteConstraints(245, 84, -1, -1));

        cbxVendedor1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxVendedor1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxVendedor1ItemStateChanged(evt);
            }
        });
        jPanel2.add(cbxVendedor1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 200, -1));

        cbxAuto1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxAuto1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxAuto1ItemStateChanged(evt);
            }
        });
        jPanel2.add(cbxAuto1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 200, -1));

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));

        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Listar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Modificar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("  Panel de Control");

        jButton4.setText("Buscar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 13, Short.MAX_VALUE))
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(jLabel9)
                .addGap(35, 35, 35)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 15, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        guardar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        cargarVista();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        modificar();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void cbxCriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxCriActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxCriActionPerformed

    private void cbxFormItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxFormItemStateChanged
        ordenar();
    }//GEN-LAST:event_cbxFormItemStateChanged

    private void txtBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBusquedaActionPerformed

    }//GEN-LAST:event_txtBusquedaActionPerformed

    private void cbxVendedor1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxVendedor1ItemStateChanged

    }//GEN-LAST:event_cbxVendedor1ItemStateChanged

    private void cbxAuto1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxAuto1ItemStateChanged

    }//GEN-LAST:event_cbxAuto1ItemStateChanged

    private void cbxCriItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxCriItemStateChanged
        if (evt.getItem().toString().equalsIgnoreCase("auto")) {
            cbxVendedor1.setVisible(false);
            txtBusqueda.setVisible(false);
            cbxAuto1.setVisible(true);
        } else if (evt.getItem().toString().equalsIgnoreCase("vendedor")) {
            cbxVendedor1.setVisible(true);
            txtBusqueda.setVisible(false);
            cbxAuto1.setVisible(false);
        } else {
            cbxVendedor1.setVisible(false);
            txtBusqueda.setVisible(true);
            cbxAuto1.setVisible(false);
        }
    }//GEN-LAST:event_cbxCriItemStateChanged

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            buscar();
        } catch (VacioExpection ex) {
            System.out.println("Eroorr" + ex);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        UIManager.put("Component.innerFocusWidth", 2);
        UIManager.put("Button.arc", 50);
        UIManager.put("CheckBox.arc", 150);
        UIManager.put("TextComponent.arc", 150);
        FlatDarkLaf.setup();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmVenta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbxAuto;
    private javax.swing.JComboBox<String> cbxAuto1;
    private javax.swing.JComboBox<String> cbxCri;
    private javax.swing.JComboBox<String> cbxForm;
    private javax.swing.JComboBox<String> cbxTipo;
    private javax.swing.JComboBox<String> cbxTipoBus;
    private javax.swing.JComboBox<String> cbxVendedor;
    private javax.swing.JComboBox<String> cbxVendedor1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable tblVenta;
    private javax.swing.JTextField txtBusqueda;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JLabel txtNroVenta;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables
}
