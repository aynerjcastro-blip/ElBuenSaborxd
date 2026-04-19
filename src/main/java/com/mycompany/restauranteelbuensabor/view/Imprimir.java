package com.mycompany.restauranteelbuensabor.view;

import com.mycompany.restauranteelbuensabor.model.Carta;
import com.mycompany.restauranteelbuensabor.model.Factura;
import com.mycompany.restauranteelbuensabor.model.ItemPedido;
import com.mycompany.restauranteelbuensabor.model.Pedido;
import com.mycompany.restauranteelbuensabor.model.Producto;

public class Imprimir {


    private static final String SEPARADOR_DOBLE  = "========================================";
    private static final String SEPARADOR_SIMPLE = "----------------------------------------";

    public static void imprimirEncabezado() {
        System.out.println(SEPARADOR_DOBLE);
        System.out.println("    RESTAURANTE " + Carta.NOMBRE_RESTAURANTE);
        System.out.println("    " + Carta.DIRECCION);
        System.out.println("    NIT: " + Carta.NIT);
        System.out.println(SEPARADOR_DOBLE);
    }

    public static void mostrarCarta() {
        imprimirEncabezado();
        System.out.println("    --- NUESTRA CARTA ---");
        System.out.println(SEPARADOR_DOBLE);
        int indice = 1;
        for (Producto producto : Carta.getProductos()) {
            System.out.printf("%d. %-22s $%,.0f%n", indice++, producto.getNombre(), producto.getPrecio());
        }
        System.out.println(SEPARADOR_DOBLE);
    }

    public static void mostrarPedido(Pedido pedido) {
        double subtotal = 0;
        System.out.println("--- PEDIDO ACTUAL ---");
        for (ItemPedido item : pedido.getItems()) {
            System.out.printf("%-20s x%-6d $%,.0f%n",
                    item.getProducto().getNombre(),
                    item.getCantidad(),
                    item.calcularSubtotal());
            subtotal += item.calcularSubtotal();
        }
        System.out.println(SEPARADOR_SIMPLE);
        System.out.printf("%-27s $%,.0f%n", "Subtotal:", subtotal);
    }

    private static void imprimirItemsPedido(Pedido pedido) {
        for (ItemPedido item : pedido.getItems()) {
            System.out.printf("%-20s x%-6d $%,.0f%n",
                    item.getProducto().getNombre(),
                    item.getCantidad(),
                    item.calcularSubtotal());
        }
    }

    private static void imprimirTotales(Factura factura) {
        System.out.println(SEPARADOR_SIMPLE);
        System.out.printf("%-27s $%,.0f%n", "Subtotal:", factura.getSubtotal());
        System.out.printf("%-27s $%,.0f%n", "Descuento:", factura.getDescuento());
        System.out.printf("%-27s $%,.0f%n", "IVA (19%):", factura.getIva());
        if (factura.getPropina() > 0) {
            System.out.printf("%-27s $%,.0f%n", "Propina (10%):", factura.getPropina());
        }
        System.out.println(SEPARADOR_SIMPLE);
        System.out.printf("%-27s $%,.0f%n", "TOTAL:", factura.getTotal());
        System.out.println(SEPARADOR_DOBLE);
    }

    public static void imprimirFacturaCompleta(Factura factura) {
        imprimirEncabezado();
        System.out.printf("FACTURA No. %03d%n", factura.getNumero());
        System.out.println(SEPARADOR_SIMPLE);
        imprimirItemsPedido(factura.getPedido());
        imprimirTotales(factura);
        System.out.println("Gracias por su visita!");
        System.out.println(Carta.NOMBRE_RESTAURANTE + " - Valledupar");
        System.out.println(SEPARADOR_DOBLE);
    }

    public static void imprimirFacturaResumen(Factura factura) {
        imprimirEncabezado();
        System.out.printf("FACTURA No. %03d (RESUMEN)%n", factura.getNumero());
        System.out.println(SEPARADOR_SIMPLE);
        imprimirTotales(factura);
    }
}