package com.mycompany.restauranteelbuensabor.model;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private final List<ItemPedido> items = new ArrayList<>();

    public void agregarItem(Producto producto, int cantidad) {
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

    public int contarItemsDiferentes(){
        return items.size();
    }
}  
