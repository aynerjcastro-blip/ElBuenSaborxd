package com.mycompany.restauranteelbuensabor.model;

import java.util.List;

public class Carta {

    public static final String NOMBRE_RESTAURANTE = "EL BUEN SABOR";
    public static final String DIRECCION = "Calle 15 #8-32, Valledupar";
    public static final String NIT = "900.123.456-7";

    private static final List<Producto> productos = List.of(
            new Producto("Bandeja Paisa", 32000),
            new Producto("Sancocho de Gallina", 28000),
            new Producto("Arepa con Huevo", 8000),
            new Producto("Jugo Natural", 7000),
            new Producto("Gaseosa", 4500),
            new Producto("Cerveza Poker", 6000),
            new Producto("Agua Panela", 3500),
            new Producto("Arroz con Pollo", 25000));

    public static List<Producto> getProductos() {
        return productos;
    }

    public static Producto getProducto(int indice) {
        return productos.get(indice);
    }

    public static int getTotalProductos() {
        return productos.size();
    }
}
