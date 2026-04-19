package com.mycompany.restauranteelbuensabor.model;

public class Producto {
    private final String nombre;
    private final double precio;

    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Producto)) {
            return false;
        }
        Producto otroProducto = (Producto) obj;
        return this.nombre.equals(otroProducto.nombre);
    }
}
