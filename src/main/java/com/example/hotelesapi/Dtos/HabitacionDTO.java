package com.example.hotelesapi.Dtos;

/*

    @Column(name = "tamanno")
    private int tamanno;

    @Column(name = "precio")
    private double precio;

    @Column(name = "desayuno")
    private boolean desayuno;

    @Column(name = "ocupada")
    private boolean ocupada;


 */
public record HabitacionDTO(int tamano, double precio, boolean desayuno, boolean ocupada) {
}
