package controlador;

import modelo.*;
import vista.*;

public class ControladorInventario {
    public static void main(String[] args) {
        Inventario inventario = new Inventario();
        InventarioGUI gui = new InventarioGUI(inventario);
        gui.setVisible(true);
    }
}
