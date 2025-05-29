package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VentanaPrincipalCerrarOrden extends JFrame {

    private JButton btnCerrarOrden;
    private PantallaCierreDeOrdenDeInspeccion pantallaCancelacion;

    public VentanaPrincipalCerrarOrden() {
        setTitle("Gestión de Ordenes");
        setSize(400, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        btnCerrarOrden = new JButton("Cerrar Orden");
        btnCerrarOrden.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        btnCerrarOrden.setBackground(new Color(255, 112, 67));
        btnCerrarOrden.setForeground(Color.WHITE);
        btnCerrarOrden.setFocusPainted(false);
        btnCerrarOrden.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(230, 90, 45), 1),
                BorderFactory.createEmptyBorder(8, 20, 8, 20)
        ));

        btnCerrarOrden.addActionListener(e -> {
            if (pantallaCancelacion == null || !pantallaCancelacion.isDisplayable()) {
                pantallaCancelacion = new PantallaCierreDeOrdenDeInspeccion(this);
                pantallaCancelacion.setVisible(true);
                this.setEnabled(false); // deshabilita la ventana principal mientras está abierta la de cancelación
            } else {
                pantallaCancelacion.toFront();
                pantallaCancelacion.requestFocus();
            }
        });

        setLayout(new GridBagLayout());
        add(btnCerrarOrden);
    }
    public void OpcionCerrarOrdenDeInspección(){
        if (pantallaCancelacion == null || !pantallaCancelacion.isDisplayable()) {
                pantallaCancelacion = new PantallaCierreDeOrdenDeInspeccion(this);
                pantallaCancelacion.setVisible(true);
                this.setEnabled(false); // deshabilita la ventana principal mientras está abierta la de cancelación
            } else {
                this.pantallaCancelacion.habilitarPantalla();
            }
    }

    public void habilitarVentanaPrincipal() {
        this.setEnabled(true);
        this.toFront();
        this.requestFocus();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaPrincipalCerrarOrden ventana = new VentanaPrincipalCerrarOrden();
            ventana.setVisible(true);
        });
    }
}