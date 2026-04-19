package com.mycompany.restauranteelbuensabor.model;

public class Factura {
    private final Pedido pedido;
    private final int numero;
    private final double subtotal;
    private final double descuento;
    private final double iva;
    private final double total;

    public Factura(Pedido pedido, int numero, double subtotal, double descuento, double iva, double total) {
        this.pedido = pedido;
        this.numero = numero;
        this.subtotal = subtotal;
        this.descuento = descuento;
        this.iva = iva;
        this.total = total;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public int getNumero() {
        return numero;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getDescuento() {
        return descuento;
    }

    public double getIva() {
        return iva;
    }

    public double getTotal() {
        return total;
    }
}
