package com.mycompany.restauranteelbuensabor.model;

public class ReglasNegocio {
    // Tarifa general de IVA para servicios de restaurante, segun vigente desde 2016 en Colombia
    public static final double TASA_IVA = 0.19;

    // Propina sugerida por politica interna del restaurante, no obligatoria por ley
    public static final double TASA_PROPINA = 0.10;

    // Descuento por consumo variado, aplica cuando se piden mas de
    // MIN_ITEMS_DESCUENTO productos diferentes
    public static final double TASA_DESCUENTO = 0.05;

    // Valor minimo del pedido para que aplique la propina sugerida
    public static final double UMBRAL_PROPINA = 50000;

    // Cantidad minima de productos diferentes para acceder al descuento
    public static final int MIN_ITEMS_DESCUENTO = 3;
}