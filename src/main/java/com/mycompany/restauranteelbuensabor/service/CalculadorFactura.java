/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restauranteelbuensabor.service;

import com.mycompany.restauranteelbuensabor.model.Datos;

public class CalculadorFactura {

    private static final double TASA_IVA = 0.19;
    private static final double TASA_PROPINA = 0.10;
    private static final double TASA_DESCUENTO = 0.05;
    private static final double UMBRAL_PROPINA = 50000;
    private static final int MIN_ITEMS_DESCUENTO = 3;

    public static double calcularTotalFactura() {
        double subtotal = calcularSubtotal();
        double subtotalConDescuento = aplicarDescuento(subtotal);
        double iva = calcularIVA(subtotalConDescuento);
        double total = calcularPropina(subtotalConDescuento + iva);
        Datos.estado = 1;// estado de factura generada
        Datos.total = total;
        return total;
    }

    public static double calcularSubtotal() {
        double subtotal = 0;
        int indice = 0;
        while (indice < Datos.cantidades.length) {
            if (Datos.cantidades[indice] > 0) {
                subtotal = subtotal + Datos.precios[indice] * Datos.cantidades[indice];
            }
            indice++;
        }
        return subtotal;
    }

    public static double aplicarDescuento(double subtotal) {
        int cantidadProductos = contadorProductosPedidos();
        if (cantidadProductos > MIN_ITEMS_DESCUENTO && subtotal > 0) {
            return subtotal - (subtotal * TASA_DESCUENTO);
        }

        return 0;
    }

    public static int contadorProductosPedidos() {
        int cantidadProductos = 0;
        for (int i = 0; i < Datos.cantidades.length; i++) {
            if (Datos.cantidades[i] > 0) {
                cantidadProductos++;
            }
        }
        return cantidadProductos;

    }

    public static double calcularIVA(double base) {
        return base * TASA_IVA;
    }

    public static double calcularPropina(double base) {
        if (base > UMBRAL_PROPINA) {
            return base + (base * TASA_PROPINA);
        }
        return base;
    }

}