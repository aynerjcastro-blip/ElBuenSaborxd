package com.mycompany.restauranteelbuensabor.service;

import com.mycompany.restauranteelbuensabor.model.Factura;
import com.mycompany.restauranteelbuensabor.model.Pedido;
import com.mycompany.restauranteelbuensabor.model.ReglasNegocio;

public class CalculadorFactura {

    public static Factura generarFactura(Pedido pedido, int numeroFactura) {
        double subtotal = pedido.calcularSubTotal();
        double descuento = calcularDescuento(subtotal, pedido.contarItemsDiferentes());
        // El IVA se calcula sobre el subtotal ya descontado, segun normativa DIAN
        double iva = calcularIVA(subtotal - descuento);
        // La propina aplica sobre el total con IVA incluido, no sobre el subtotal
        double total = calcularTotal((subtotal - descuento) + iva);
        double propina = calcularPropina(total);
        return new Factura(pedido, numeroFactura, subtotal, descuento, iva, total, propina);
    }

    private static double calcularDescuento(double subtotal, int itemsDiferentes) {
        // El descuento solo aplica cuando el cliente pide mas de MIN_ITEMS_DESCUENTO
        // productos diferentes, como incentivo a consumir variedad
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

    private static double calcularPropina(double base) {
        if (base > ReglasNegocio.UMBRAL_PROPINA) {
            return base * ReglasNegocio.TASA_PROPINA;
        }
        return 0;
    }

}