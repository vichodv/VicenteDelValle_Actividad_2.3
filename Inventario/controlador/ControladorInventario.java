package controlador;

import modelo.Producto;
import vista.InventarioGUI;

import javax.swing.*;
import java.util.ArrayList;

public class ControladorInventario {
    private ArrayList<Producto> inventario;
    private InventarioGUI vista;

    public ControladorInventario() {
        inventario = new ArrayList<>();
        vista = new InventarioGUI(this);
        vista.setVisible(true);
    }

    public void agregarProducto(String nombre, String stockText) {
        if (nombre.isEmpty() || stockText.isEmpty()) {
            vista.mostrarMensaje("Completa todos los campos", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int stock = Integer.parseInt(stockText);
            Producto p = new Producto(nombre, stock);
            inventario.add(p);
            vista.actualizarLista(inventario);
            vista.limpiarCampos();
        } catch (NumberFormatException e) {
            vista.mostrarMensaje("El stock debe ser un número entero", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void eliminarProducto(int index) {
        if (index >= 0 && index < inventario.size()) {
            inventario.remove(index);
            vista.actualizarLista(inventario);
        } else {
            vista.mostrarMensaje("Selecciona un producto para eliminar", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ControladorInventario::new);
    }
}
