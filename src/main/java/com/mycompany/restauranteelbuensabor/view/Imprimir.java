/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restauranteelbuensabor.view;

import com.mycompany.restauranteelbuensabor.model.Datos;


public class Imprimir {
    private static final String NOMBRE_RESTAURANTE = "EL BUEN SABOR";
    private static final String DIRECCION = "Calle 15 #8-32, Valledupar";
    private static final String NIT = "900.123.456-7";
    private static final String SEPARADOR_DOBLE = "========================================";
    private static final String SEPARADOR_SIMPLE = "----------------------------------------";

    public static void imprimirEncabezado() {
        System.out.println(SEPARADOR_DOBLE);
        System.out.println("    RESTAURANTE " + NOMBRE_RESTAURANTE);
        System.out.println("    " + DIRECCION);
        System.out.println("    NIT: " + NIT);
        System.out.println(SEPARADOR_DOBLE);
    }

    public static void mostrarCarta() {
        imprimirEncabezado();
        System.out.println("    --- NUESTRA CARTA ---");
        System.out.println(SEPARADOR_DOBLE);
        int indice = 0;
        while (indice < Datos.nombres.length) {
            System.out.printf("%d. %-22s $%,.0f%n", (indice + 1), Datos.nombres[indice], Datos.precios[indice]);
            indice++;
        }
        System.out.println(SEPARADOR_DOBLE);
    }

    public static void mostrarPedido() {
        double subtotal = 0;
        int indice = 0;
        System.out.println("--- PEDIDO ACTUAL ---");
        while (indice < Datos.nombres.length) {
            if (Datos.cantidades[indice] > 0) {
                System.out.printf("%-20s x%-6d $%,.0f%n",
                        Datos.nombres[indice],
                        Datos.cantidades[indice],
                        Datos.precios[indice] * Datos.cantidades[indice]);
                subtotal += Datos.precios[indice] * Datos.cantidades[indice];
            }
            indice++;
        }
        System.out.println(SEPARADOR_SIMPLE);
        System.out.printf("%-27s $%,.0f%n", "Subtotal:", subtotal);
    }
}
