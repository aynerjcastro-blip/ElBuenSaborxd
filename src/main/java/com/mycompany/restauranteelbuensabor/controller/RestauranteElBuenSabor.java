
package com.mycompany.restauranteelbuensabor.controller;

import com.mycompany.restauranteelbuensabor.view.Imprimir;
import com.mycompany.restauranteelbuensabor.model.Datos;
import com.mycompany.restauranteelbuensabor.service.CalculadorFactura;
import com.mycompany.restauranteelbuensabor.service.Utilidades;
import java.util.Scanner;

/**
 *
 * @author alfre
 */
public class RestauranteElBuenSabor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcionMenu = 0;
        boolean continuarEjecutando = true;
        int intentosInvalidos = 0;
        System.out.println("========================================");
        System.out.println("    RESTAURANTE EL BUEN SABOR");
        System.out.println("    Calle 15 #8-32, Valledupar");
        System.out.println("    NIT: 900.123.456-7");
        System.out.println("========================================");
        while (continuarEjecutando) {
    System.out.println("1. Ver carta");
    System.out.println("2. Agregar producto al pedido");
    System.out.println("3. Ver pedido actual");
    System.out.println("4. Generar factura");
    System.out.println("5. Nueva mesa");
    System.out.println("0. Salir");
    System.out.println("========================================");
    System.out.print("Seleccione una opcion: ");
    opcionMenu = sc.nextInt();

    switch (opcionMenu) {
        case 1:
            Imprimir.mostrarCarta();
            System.out.println();
            break;

        case 2:
            System.out.println("--- AGREGAR PRODUCTO ---");
            System.out.print("Numero de producto (1-" + Datos.nombres.length + "): ");
            int numeroProducto = sc.nextInt();
            System.out.print("Cantidad: ");
            int cantidad = sc.nextInt();

            if (numeroProducto > 0 && numeroProducto <= Datos.nombres.length) {
                if (cantidad > 0) {
                    if (Datos.estado == 0) {
                        System.out.print("Ingrese numero de mesa: ");
                        Datos.numeroMesaActual = sc.nextInt();
                        if (Datos.numeroMesaActual <= 0) {
                            Datos.numeroMesaActual = 1;
                        }
                        Datos.estado = 1;
                    }
                    Datos.cantidades[numeroProducto - 1] += cantidad;
                    System.out.println("Producto agregado al pedido.");
                    System.out.println("  -> " + Datos.nombres[numeroProducto - 1] + " x" + cantidad);
                } else {
                    System.out.println(cantidad == 0
                        ? "La cantidad no puede ser cero."
                        : "Cantidad invalida. Ingrese un valor positivo.");
                }
            } else {
                System.out.println(numeroProducto <= 0
                    ? "El numero debe ser mayor a cero."
                    : "Producto no existe. La carta tiene " + Datos.nombres.length + " productos.");
            }
            System.out.println();
            break;

        case 3:
            System.out.println();
            if (Utilidades.hayProductosEnPedido()) {
                Imprimir.mostrarPedido();
            } else {
                System.out.println("No hay productos en el pedido actual.");
                System.out.println("Use la opcion 2 para agregar productos.");
            }
            System.out.println();
            break;

        case 4:
            System.out.println();
            if (Utilidades.hayProductosEnPedido()) {
                CalculadorFactura.calcularTotalFactura();
                Imprimir.imprimirFacturaCompleta();
                System.out.println();
            } else {
                System.out.println("No se puede generar factura.");
                System.out.println("No hay productos en el pedido.");
                System.out.println("Use la opcion 2 para agregar productos primero.");
            }
            break;

        case 5:
            System.out.println();
            Utilidades.reiniciar();
            System.out.println("Mesa reiniciada. Lista para nuevo cliente.");
            System.out.println();
            break;

        case 0:
            continuarEjecutando = false;
            System.out.println("Hasta luego!");
            break;

        default:
            System.out.println("Opcion no valida. Seleccione entre 0 y 5.");
            intentosInvalidos++;
            if (intentosInvalidos > 3) {
                System.out.println("Demasiados intentos invalidos.");
                intentosInvalidos = 0;
            }
            break;
    }
}
        sc.close();
    }// fin main
}
