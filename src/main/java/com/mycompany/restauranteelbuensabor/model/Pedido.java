package com.mycompany.restauranteelbuensabor.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pedido {
    private final List<ItemPedido> items = new ArrayList<>();

    public void agregarItem(Producto producto, int cantidad) {
         // Si el producto ya existe en el pedido se acumula la cantidad
        // en lugar de crear un item duplicado
        for (ItemPedido item : items) {
            if (item.getProducto().equals(producto)) {
                item.agregarCantidad(cantidad);
                return;
            }
        }
        items.add(new ItemPedido(producto, cantidad));
    }


    public double calcularSubTotal() {
        double total = 0;
        for (ItemPedido item : items) {
            total += item.calcularSubtotal();
        }
        return total;
    }

    public int contarItemsDiferentes() {
        return items.size();
    }

    public boolean tieneProductos() {
        return !items.isEmpty();
    }

    public void limpiar() {
        items.clear();
    }

    public List<ItemPedido> getItems() {
        // Retorna una vista inmutable para evitar modificaciones externas
        // Los cambios deben hacerse a traves de agregarItem() y limpiar()
        return Collections.unmodifiableList(items);
    }
}
