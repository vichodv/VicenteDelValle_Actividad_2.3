package vista;

import controlador.ControladorInventario;
import modelo.Producto;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class InventarioGUI extends JFrame {
    private JTextField campoNombre;
    private JTextField campoStock;
    private DefaultListModel<String> modeloLista;
    private JList<String> listaProductos;
    private ControladorInventario controlador;

    public InventarioGUI(ControladorInventario controlador) {
        this.controlador = controlador;
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setTitle("Inventario del Almacén");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

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

        modeloLista = new DefaultListModel<>();
        listaProductos = new JList<>(modeloLista);
        panel.add(new JScrollPane(listaProductos), BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        JButton btnAgregar = new JButton("Agregar producto");
        JButton btnEliminar = new JButton("Eliminar seleccionado");
        panelBotones.add(btnAgregar);
        panelBotones.add(btnEliminar);
        panel.add(panelBotones, BorderLayout.SOUTH);

        btnAgregar.addActionListener(e ->
                controlador.agregarProducto(campoNombre.getText(), campoStock.getText()));

        btnEliminar.addActionListener(e ->
                controlador.eliminarProducto(listaProductos.getSelectedIndex()));
    }

    public void actualizarLista(ArrayList<Producto> inventario) {
        modeloLista.clear();
        for (Producto p : inventario) {
            modeloLista.addElement(p.toString());
        }
    }

    public void limpiarCampos() {
        campoNombre.setText("");
        campoStock.setText("");
    }

    public void mostrarMensaje(String mensaje, int tipo) {
        JOptionPane.showMessageDialog(this, mensaje, "Inventario", tipo);
    }
}
