package com.example.hotelesapi.Services;

import com.example.hotelesapi.Entities.Hotel;
import com.example.hotelesapi.Repositorys.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService {
    private final HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public List<Hotel> listarHoteles() {
        return hotelRepository.findAll();
    }

    public Optional<Hotel> BuscarHotelXID(int id) {
        // return listarHoteles().stream().filter(hotel -> hotel.getId() == id).findFirst().orElse(null); en caso de que no fuera optional
        return listarHoteles().stream().filter(hotel -> hotel.getId() == id).findFirst();
    }

    public List<Hotel> filtrarXCategoria(int categoria) {
        return listarHoteles().stream().filter(hotel -> hotel.getCategoria() == categoria).toList();
    }

    public List<Hotel> filtrarXLocalidad(String localidad) {
        return listarHoteles().stream().filter(hotel -> hotel.getLocalidad().equalsIgnoreCase(localidad)).toList();
    }

}
