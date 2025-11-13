package test;

import modelo.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class InventarioTest {

    private Inventario inventario;

    @BeforeEach
    void setUp() {
        inventario = new Inventario();
    }

    @Test
    void testAgregarProducto() {
        Producto p = new Producto("Manzanas", 10);
        inventario.agregarProducto(p);
        assertEquals(1, inventario.getProductos().size());
        assertEquals("Manzanas", inventario.getProductos().get(0).getNombre());
    }

    @Test
    void testBuscarProducto() {
        inventario.agregarProducto(new Producto("Papas", 5));
        Producto encontrado = inventario.buscarProducto("Papas");
        assertNotNull(encontrado);
        assertEquals(5, encontrado.getStock());
    }

    @Test
    void testActualizarStock() {
        inventario.agregarProducto(new Producto("Peras", 8));
        boolean actualizado = inventario.actualizarStock("Peras", 15);
        assertTrue(actualizado);
        assertEquals(15, inventario.buscarProducto("Peras").getStock());
    }

    @Test
    void testActualizarStockProductoInexistente() {
        boolean resultado = inventario.actualizarStock("Uvas", 20);
        assertFalse(resultado);
    }
}
