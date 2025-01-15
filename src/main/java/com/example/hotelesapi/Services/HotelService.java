package com.example.hotelesapi.Services;

import com.example.hotelesapi.Entities.Habitacion;
import com.example.hotelesapi.Entities.Hotel;
import com.example.hotelesapi.Repositorys.HabitacionRepository;
import com.example.hotelesapi.Repositorys.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HotelService {
    private final HotelRepository hotelRepository;
    private final HabitacionRepository habitacionRepository;

    public HotelService(HotelRepository hotelRepository, HabitacionRepository habitacionRepository) {
        this.hotelRepository = hotelRepository;
        this.habitacionRepository = habitacionRepository;
    }

    public List<Hotel> listarHoteles() {
        return hotelRepository.findAll();
    }

    public Optional<Hotel> buscarHotelXID(int id) {
        return listarHoteles().stream().filter(hotel -> hotel.getId() == id).findFirst();
    }

    public List<Hotel> filtrarXCategoria(int categoria) {
        return listarHoteles().stream().filter(hotel -> hotel.getCategoria() == categoria).toList();
    }

    public List<Hotel> filtrarXLocalidad(String localidad) {
        return listarHoteles().stream().filter(hotel -> hotel.getLocalidad().equalsIgnoreCase(localidad)).toList();
    }

    public <S extends Hotel> S guardarHotel(S entity) {
        return hotelRepository.save(entity);
    }

    public Hotel addHabitacion(int id, Habitacion habitacion) {
        var hotel = hotelRepository.findById(id);
        if (hotel.isPresent()) {
            var existeHotel = hotel.get();
            existeHotel.addHabitacion(habitacion);
            return hotelRepository.save(existeHotel);
        }
        return null;
    }

    public boolean deleteHabitacion(int idHotel, int idHabitacion) {
        boolean borrado = false;
        if (hotelRepository.findById(idHotel).isPresent()) {
            Hotel hotel = hotelRepository.findById(idHotel).get();
            HabitacionServices habitacionServices = new HabitacionServices(habitacionRepository);
            if (habitacionServices.buscarHabitacionXID(idHabitacion).isPresent()) {
                Habitacion habitacion = habitacionServices.buscarHabitacionXID(idHabitacion).get();
                if (habitacion.getHotel() == hotel) {
                    hotel.deleteHabitacion(habitacion);
                    habitacionServices.deleteById(habitacion.getId());
                    borrado = true;
                }
            }
        }
        return borrado;
    }

}
