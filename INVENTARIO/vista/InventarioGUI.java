package vista;

import modelo.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InventarioGUI extends JFrame {
    private Inventario inventario;
    private DefaultListModel<String> modeloLista;
    private JList<String> listaProductos;
    private JTextField campoNombre;
    private JTextField campoStock;

    public InventarioGUI(Inventario inventario) {
        this.inventario = inventario;

        setTitle("Inventario del Almacén");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        modeloLista = new DefaultListModel<>();
        listaProductos = new JList<>(modeloLista);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        add(panel);

        JPanel panelSuperior = new JPanel(new GridLayout(2, 2, 5, 5));
        panelSuperior.add(new JLabel("Nombre del producto:"));
        campoNombre = new JTextField();
        panelSuperior.add(campoNombre);
        panelSuperior.add(new JLabel("Stock inicial:"));
        campoStock = new JTextField();
        panelSuperior.add(campoStock);
        panel.add(panelSuperior, BorderLayout.NORTH);

        panel.add(new JScrollPane(listaProductos), BorderLayout.CENTER);

        JButton botonAgregar = new JButton("Agregar Producto");
        panel.add(botonAgregar, BorderLayout.SOUTH);

        botonAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarProducto();
            }
        });
    }

    private void agregarProducto() {
        String nombre = campoNombre.getText().trim();
        String textoStock = campoStock.getText().trim();

        if (nombre.isEmpty() || textoStock.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Completa todos los campos");
            return;
        }

        try {
            int stock = Integer.parseInt(textoStock);
            inventario.agregarProducto(new Producto(nombre, stock));
            actualizarLista();
            campoNombre.setText("");
            campoStock.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Stock debe ser un número");
        }
    }

    private void actualizarLista() {
        modeloLista.clear();
        for (Producto p : inventario.getProductos()) {
            modeloLista.addElement(p.toString());
        }
    }
}
