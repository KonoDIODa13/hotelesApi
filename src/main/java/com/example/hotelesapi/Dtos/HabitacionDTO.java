package com.example.hotelesapi.Dtos;

import com.example.hotelesapi.Entities.Habitacion;

public record HabitacionDTO(int tamanno, double precio, boolean desayuno, boolean ocupada) {

    public Habitacion fromDTOToHabitacion() {
        Habitacion habitacion = new Habitacion();
        habitacion.setTamanno(tamanno);
        habitacion.setPrecio(precio);
        habitacion.setDesayuno(desayuno);
        habitacion.setOcupada(ocupada);
        return habitacion;
    }

    public boolean compruebaCampos() {
        boolean comprobado = true;
        if (tamanno < 1 || tamanno >= 3) {
            comprobado = false;
        }

        if (precio <= 0.00) {
            comprobado = false;
        }

        /*
        no se como validar un bool xD
        if (desayuno) {
            comprobado = false;
        }
        */

        /*
        no se como validar un bool xD
        if (ocupada) {
            comprobado = false;
        }
        */
        return comprobado;
    }

}
