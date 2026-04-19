package com.mycompany.restauranteelbuensabor.service;

import com.mycompany.restauranteelbuensabor.model.Datos;

public class Utilidades {

    public static double calcularConImpuestosYPropina(
            double precioUnitario,
            double cantidad,
            double porcentajeDescuento,
            double porcentajeIva,
            double porcentajePropina,
            int numeroProductos,
            boolean aplicarPropina) {

        double subtotal = precioUnitario * cantidad;

        if(porcentajeDescuento > 0){
            subtotal = subtotal - (subtotal * porcentajeDescuento);
        }
        subtotal = subtotal + (subtotal * porcentajeIva);
        if(aplicarPropina){
            subtotal = subtotal + (subtotal * porcentajePropina);
        }

        return subtotal;
    }

    public static boolean hayProductosEnPedido() {
        int cantidadProductos = 0;
        int indice = 0;
        while (indice < Datos.cantidades.length) {
            if (Datos.cantidades[indice] > 0) {
                cantidadProductos++;
            }
            indice++;
        }
        if (cantidadProductos == 0) {
            Datos.total = 0;
            Datos.temporal = "";
        }
        return cantidadProductos > 0;
    }

    public static void reiniciar() {
        int indice = 0;
        while (indice < Datos.cantidades.length) {
            Datos.cantidades[indice] = 0;
            indice++;
        }
        Datos.total = 0;
        Datos.estado = 0;
        Datos.numeroMesaActual = 0;
        Datos.temporal = "";
    }
}