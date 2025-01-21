package com.example.hotelesapi.Dtos;

import com.example.hotelesapi.Entities.Hotel;

public record HotelDTO(String nombre, String descripcion, int categoria, boolean piscina, String localidad) {

    public Hotel fromDToToHotel() {
        Hotel hotel = new Hotel();
        hotel.setNombre(nombre);
        hotel.setDescripcion(descripcion);
        hotel.setCategoria(categoria);
        hotel.setPiscina(piscina);
        hotel.setLocalidad(localidad);
        return hotel;
    }

    public boolean compruebaCampos() {
        boolean comprobado = true;
        if (nombre.isEmpty()) {
            comprobado = false;
        }

        if (descripcion.isEmpty()) {
            comprobado = false;
        }

        if (categoria >= 1 && categoria < 5) {
            comprobado = false;
        }

        /*
        no se como validar un bool xD
        if (piscina) {
            comprobado = false;
        }
        */

        if (localidad.isEmpty()) {
            comprobado = false;
        }
        return comprobado;
    }

}
