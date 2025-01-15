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
        if (nombre == null) {
            comprobado = false;
        }

        if (descripcion == null) {
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

        if (localidad == null) {
            comprobado = false;
        }
        return comprobado;
    }

}
