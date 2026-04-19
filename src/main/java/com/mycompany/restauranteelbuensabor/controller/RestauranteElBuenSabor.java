package com.mycompany.restauranteelbuensabor.controller;

import com.mycompany.restauranteelbuensabor.view.Imprimir;
import com.mycompany.restauranteelbuensabor.model.Carta;
import com.mycompany.restauranteelbuensabor.model.Factura;
import com.mycompany.restauranteelbuensabor.model.Mesa;
import com.mycompany.restauranteelbuensabor.model.Pedido;
import com.mycompany.restauranteelbuensabor.service.CalculadorFactura;
import java.util.Scanner;

public class RestauranteElBuenSabor {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Pedido pedido = new Pedido();
        Mesa mesa = new Mesa();
        int numeroFactura = 1;
        int opcionMenu = 0;
        boolean continuarEjecutando = true;
        int intentosInvalidos = 0;

        Imprimir.imprimirEncabezado();

        while (continuarEjecutando) {
            System.out.println("1. Ver carta");
            System.out.println("2. Agregar producto al pedido");
            System.out.println("3. Ver pedido actual");
            System.out.println("4. Generar factura");
            System.out.println("5. Nueva mesa");
            System.out.println("0. Salir");
            System.out.println("========================================");
            System.out.print("Seleccione una opcion: ");
            opcionMenu = Integer.parseInt(sc.nextLine());

            switch (opcionMenu) {
                case 1:
                    Imprimir.mostrarCarta();
                    System.out.println();
                    break;

                case 2:
                    System.out.println("--- AGREGAR PRODUCTO ---");
                    System.out.print("Numero de producto (1-" + Carta.getTotalProductos() + "): ");
                    int numeroProducto = Integer.parseInt(sc.nextLine());
                    System.out.print("Cantidad: ");
                    int cantidad = Integer.parseInt(sc.nextLine());

                    if (numeroProducto > 0 && numeroProducto <= Carta.getTotalProductos()) {
                        if (cantidad > 0) {
                            // La mesa se activa una sola vez por pedido, al agregar el primer producto
                            if (!mesa.estaActiva()) {
                                System.out.print("Ingrese numero de mesa: ");
                                int numeroMesa = Integer.parseInt(sc.nextLine());
                                mesa.activar(numeroMesa <= 0 ? 1 : numeroMesa);
                            }
                            pedido.agregarItem(Carta.getProducto(numeroProducto - 1), cantidad);
                            System.out.println("Producto agregado al pedido.");
                            System.out.println(
                                    "  -> " + Carta.getProducto(numeroProducto - 1).getNombre() + " x" + cantidad);
                        } else {
                            System.out.println(cantidad == 0
                                    ? "La cantidad no puede ser cero."
                                    : "Cantidad invalida. Ingrese un valor positivo.");
                        }
                    } else {
                        System.out.println(numeroProducto <= 0
                                ? "El numero debe ser mayor a cero."
                                : "Producto no existe. La carta tiene " + Carta.getTotalProductos() + " productos.");
                    }
                    System.out.println();
                    break;

                case 3:
                    System.out.println();
                    if (pedido.tieneProductos()) {
                        Imprimir.mostrarPedido(pedido);
                    } else {
                        System.out.println("No hay productos en el pedido actual.");
                        System.out.println("Use la opcion 2 para agregar productos.");
                    }
                    System.out.println();
                    break;

                case 4:
                    System.out.println();
                    if (pedido.tieneProductos()) {
                        Factura factura = CalculadorFactura.generarFactura(pedido, numeroFactura);
                        Imprimir.imprimirFacturaCompleta(factura);
                        // El pedido y la mesa se limpian despues de facturar, no antes,
                        // para que imprimirFacturaCompleta pueda leer los items
                        numeroFactura++;
                        pedido.limpiar();
                        mesa.reiniciar();
                        System.out.println();
                    } else {
                        System.out.println("No se puede generar factura.");
                        System.out.println("No hay productos en el pedido.");
                        System.out.println("Use la opcion 2 para agregar productos primero.");
                    }
                    break;

                case 5:
                    System.out.println();
                    pedido.limpiar();
                    mesa.reiniciar();
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
                    // Se reinicia el contador para no bloquear al usuario indefinidamente
                    if (intentosInvalidos > 3) {
                        System.out.println("Demasiados intentos invalidos.");
                        intentosInvalidos = 0;
                    }
                    break;
            }
        }
        sc.close();
    }
}