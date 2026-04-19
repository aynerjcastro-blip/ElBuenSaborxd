package com.mycompany.restauranteelbuensabor.model;

public class Datos {

    // Constantes
    // Datos.java
    public static final double TASA_IVA = 0.19;
    public static final double TASA_PROPINA = 0.10;
    public static final double TASA_DESCUENTO = 0.05;
    public static final double UMBRAL_PROPINA = 50000;
    public static final int MIN_ITEMS_DESCUENTO = 3;
    // Carta
    public static String[] nombres = {
            "Bandeja Paisa",
            "Sancocho de Gallina",
            "Arepa con Huevo",
            "Jugo Natural",
            "Gaseosa",
            "Cerveza Poker",
            "Agua Panela",
            "Arroz con Pollo"
    };
    public static double[] precios = { 32000, 28000, 8000, 7000, 4500, 6000, 3500, 25000 };
    public static int[] cantidades = { 0, 0, 0, 0, 0, 0, 0, 0 };

    // Estado de la mesa activa
    public static int numeroMesaActual = 0;
    public static int estado = 0;
    public static double total = 0;
    public static int numeroFacturas = 1;
    public static String temporal = "";
}