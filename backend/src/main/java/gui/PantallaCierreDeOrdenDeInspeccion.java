package gui;

import com.PPAI.backend.backend.DTOs.OrdenDeInspeccionDTO;
import com.PPAI.backend.backend.controllers.Gestor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PantallaCierreDeOrdenDeInspeccion extends JFrame {
    private Gestor gestor;
    private List<OrdenDeInspeccionDTO> ordenesEnTabla = new ArrayList<>();
    private VentanaPrincipalCerrarOrden ventanaPrincipal;

    public PantallaCierreDeOrdenDeInspeccion(VentanaPrincipalCerrarOrden ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
        this.gestor = new Gestor(this);
        initComponents();

        jTable1.setVisible(false);
        jTextArea1.setVisible(false);
        jComboBox1.setVisible(false);
        jTextArea2.setVisible(false);

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                ventanaPrincipal.habilitarVentanaPrincipal();
                dispose();
            }

            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                ventanaPrincipal.habilitarVentanaPrincipal();
            }
        });
    }

    private void initComponents() {
        setTitle("Cancelar Orden de Inspección");
        setSize(850, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        Color fondo = new Color(255, 250, 240);
        Color naranja = new Color(255, 140, 0);
        Font fuenteTitulo = new Font("SansSerif", Font.BOLD, 16);
        Font fuenteTexto = new Font("SansSerif", Font.PLAIN, 14);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(fondo);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        // PANEL ORDEN
        JPanel panelOrden = new JPanel(new BorderLayout());
        panelOrden.setBackground(fondo);
        panelOrden.setBorder(BorderFactory.createTitledBorder("1. Selección de Orden"));

        jButton2 = crearBoton("Cargar órdenes finalizadas", naranja);
        jButton2.addActionListener(this::jButton2ActionPerformed);
        panelOrden.add(jButton2, BorderLayout.NORTH);

        jTable1 = new JTable(new DefaultTableModel(
                new Object[][]{},
                new String[]{"N° Orden", "Fecha Finalización", "Estación", "Sismógrafo"}
        ));
        jTable1.setFont(fuenteTexto);
        jTable1.setRowHeight(22);
        jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1 = new JScrollPane(jTable1);
        panelOrden.add(jScrollPane1, BorderLayout.CENTER);

        jButton1 = crearBoton("Seleccionar Orden", naranja);
        jButton1.addActionListener(this::jButton1ActionPerformed);
        panelOrden.add(jButton1, BorderLayout.SOUTH);

        mainPanel.add(panelOrden);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // PANEL OBSERVACIÓN
        JPanel panelObservacion = new JPanel(new BorderLayout());
        panelObservacion.setBackground(fondo);
        panelObservacion.setBorder(BorderFactory.createTitledBorder("2. Ingreso de Observación de Cierre"));

        jTextArea1 = new JTextArea(3, 40);
        jTextArea1.setLineWrap(true);
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setFont(fuenteTexto);
        jScrollPane2 = new JScrollPane(jTextArea1);
        panelObservacion.add(jScrollPane2, BorderLayout.CENTER);

        jButton3 = crearBoton("Enviar Observación", naranja);
        jButton3.addActionListener(this::jButton3ActionPerformed);
        panelObservacion.add(jButton3, BorderLayout.SOUTH);

        mainPanel.add(panelObservacion);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // PANEL MOTIVO
        JPanel panelMotivo = new JPanel();
        panelMotivo.setLayout(new GridBagLayout());
        panelMotivo.setBackground(fondo);
        panelMotivo.setBorder(BorderFactory.createTitledBorder("3. Motivo de Fuera de Servicio"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelMotivo.add(new JLabel("Seleccione Motivo:"), gbc);

        gbc.gridx = 1;
        jComboBox1 = new JComboBox<>();
        jComboBox1.setFont(fuenteTexto);
        panelMotivo.add(jComboBox1, gbc);

        gbc.gridx = 2;
        jButton4 = crearBoton("Seleccionar", naranja);
        jButton4.addActionListener(this::jButton4ActionPerformed);
        panelMotivo.add(jButton4, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        jTextArea2 = new JTextArea(3, 40);
        jTextArea2.setFont(fuenteTexto);
        jTextArea2.setLineWrap(true);
        jTextArea2.setWrapStyleWord(true);
        jScrollPane3 = new JScrollPane(jTextArea2);
        panelMotivo.add(jScrollPane3, gbc);

        gbc.gridy = 2;
        gbc.gridwidth = 1;
        jButton5 = crearBoton("Enviar Comentario", naranja);
        jButton5.addActionListener(this::jButton5ActionPerformed);
        panelMotivo.add(jButton5, gbc);

        gbc.gridx = 1;
        jButton6 = crearBoton("Confirmar Cancelación", naranja);
        jButton6.addActionListener(this::jButton6ActionPerformed);
        panelMotivo.add(jButton6, gbc);

        mainPanel.add(panelMotivo);
        getContentPane().add(mainPanel);
    }
    
    public void habilitarPantalla(){
        this.setVisible(true);
        this.toFront();
        this.requestFocus();
    }
    private JButton crearBoton(String texto, Color colorFondo) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("SansSerif", Font.PLAIN, 14));
        boton.setBackground(colorFondo);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setPreferredSize(new Dimension(200, 35));
        return boton;
    }

    public void mostrarTiposMotivo(List<String> motivos) {
        jComboBox1.setVisible(true);
        jComboBox1.removeAllItems();
        for (String motivo : motivos) {
            jComboBox1.addItem(motivo);
        }
    }

    public void tomarSeleccionDeOrden() {
        int ordenSeleccionada = jTable1.getSelectedRow();
        if (ordenSeleccionada != -1) {
            OrdenDeInspeccionDTO seleccionada = ordenesEnTabla.get(ordenSeleccionada);
            gestor.tomarSeleccionDeOrden(seleccionada);
            JOptionPane.showMessageDialog(this, "Usted ha seleccionado la orden N° " + seleccionada.getNumeroOrden());
        }
    }

    public void solicitarIngresoDeObservacionDeCierre() {
        jTextArea1.setVisible(true);
    }

    public void tomarIngresoDeObservacionDeCierre(String txt) {
        gestor.tomarObservacionDeCierre(txt);
    }

    public void mostrarDatosDeOrdenes(List<OrdenDeInspeccionDTO> ordenes) {
        jButton6.setEnabled(true);
        jTable1.setVisible(true);
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        modelo.setRowCount(0);
        ordenesEnTabla.clear();
        for (OrdenDeInspeccionDTO orden : ordenes) {
            ordenesEnTabla.add(orden);
            modelo.addRow(new Object[]{
                    orden.getNumeroOrden(),
                    orden.getFechaHoraFinalizacion().toString(),
                    orden.getNombreEstacionSismologica(),
                    orden.getIdentificadorSismografo()
            });
        }
    }

    public void solicitarIngresoComentario() {
        jTextArea2.setEnabled(true);
        jButton5.setEnabled(true);
    }

    public void tomarConfirmacion() {
        jButton6.setEnabled(false);
        gestor.tomarConfirmacion();
        gestor.cerrarOrdenDeInspeccion();
    }

    public void solicitarConfirmacion() {
        int rta = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea confirmar la operación?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (rta == JOptionPane.YES_OPTION) {
            tomarConfirmacion();
        }
    }
    
    public void tomarSeleccionDeTipoFueraDeServicio(){
        jTextArea2.setVisible(true);
        String motivo = (String) jComboBox1.getSelectedItem();
        if (motivo != null) {
            gestor.tomarSeleccionDeTipoFueraDeServicio(motivo);
            
        }
    }
    public void tomarIngresoComentario(){
        String comentario = jTextArea2.getText().trim();
        if (!comentario.isEmpty()) {
            gestor.tomarIngresoComentario(comentario);
            jTextArea2.setText("");
            jTextArea2.setVisible(false);
            jTextArea2.setEnabled(false);
            jButton5.setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(this, "Debe ingresar un comentario.");
        }
        
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        gestor.cerrarOrdenDeInspeccion();
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        tomarSeleccionDeOrden();
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        jTextArea1.setVisible(false);
        String texto = jTextArea1.getText().trim();
        if (!texto.isEmpty()) {
            tomarIngresoDeObservacionDeCierre(texto);
            jTextArea1.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Debe ingresar una observación.");
        }
    }
    
    // este boton seria el tomarSeleccionDeTipoFueraDeServicio
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
        tomarSeleccionDeTipoFueraDeServicio();
    }
    
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {
        tomarIngresoComentario();
    }

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {
        solicitarConfirmacion();
    }

    private JButton jButton1, jButton2, jButton3, jButton4, jButton5, jButton6;
    private JComboBox<String> jComboBox1;
    private JScrollPane jScrollPane1, jScrollPane2, jScrollPane3;
    private JTable jTable1;
    private JTextArea jTextArea1, jTextArea2;
}
