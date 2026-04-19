package com.mycompany.restauranteelbuensabor.service;

import com.mycompany.restauranteelbuensabor.model.Factura;
import com.mycompany.restauranteelbuensabor.model.Pedido;
import com.mycompany.restauranteelbuensabor.model.ReglasNegocio;

public class CalculadorFactura {

    public static Factura generarFactura(Pedido pedido, int numeroFactura) {
        double subtotal  = pedido.calcularSubTotal();
        double descuento = calcularDescuento(subtotal, pedido.contarItemsDiferentes());
        double iva       = calcularIVA(subtotal - descuento);
        double total     = calcularTotal((subtotal - descuento) + iva);
        return new Factura(pedido, numeroFactura, subtotal, descuento, iva, total);
    }

    private static double calcularDescuento(double subtotal, int itemsDiferentes) {
        if (itemsDiferentes > ReglasNegocio.MIN_ITEMS_DESCUENTO) {
            return subtotal * ReglasNegocio.TASA_DESCUENTO;
        }
        return 0;
    }

    private static double calcularIVA(double base) {
        return base * ReglasNegocio.TASA_IVA;
    }

    private static double calcularTotal(double base) {
        if (base > ReglasNegocio.UMBRAL_PROPINA) {
            return base + (base * ReglasNegocio.TASA_PROPINA);
        }
        return base;
    }
    
}