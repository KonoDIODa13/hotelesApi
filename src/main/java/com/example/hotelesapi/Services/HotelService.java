package com.example.hotelesapi.Services;

import com.example.hotelesapi.Entities.Habitacion;
import com.example.hotelesapi.Entities.Hotel;
import com.example.hotelesapi.Repositorys.HabitacionRepository;
import com.example.hotelesapi.Repositorys.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService {
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private HabitacionRepository habitacionRepository;


    public List<Hotel> listarHoteles() {
        /*
        Listar todos los hoteles.
        Función que devuelve la lista completa de hoteles de la bd.
         */
        return hotelRepository.findAll();
    }

    public Optional<Hotel> buscarHotelXID(int id) {
        /*
        Buscar el hotel que tenga dicho id.
        Recorro la lista de hoteles mediante stream,
        busco la primera habitación de la lista por el id,
        si existe, me devolverá un hotel.
        */
        return listarHoteles().stream().filter(hotel -> hotel.getId() == id).findFirst();
    }

    public List<Hotel> filtrarXCategoria(int categoria) {
        /*
        Buscar todas los hoteles que tengan dicha categoria.
        Recorro la lista de hoteles mediante stream,
        busco si hay algun hotel con dicha categoria,
        si existe, lo devolveré en forma de lista.
        */
        return listarHoteles().stream().filter(hotel -> hotel.getCategoria() == categoria).toList();
    }

    public List<Hotel> filtrarXLocalidad(String localidad) {
        /*
        Buscar todas los hoteles que tengan dicha localidad.
        Recorro la lista de hoteles mediante stream,
        busco si hay algun hotel con dicha categoria,
        si existe, lo devolveré en forma de lista.
        */
        return listarHoteles().stream().filter(hotel -> hotel.getLocalidad().equalsIgnoreCase(localidad)).toList();
    }

    public <S extends Hotel> S guardarHotel(S entity) {
        /*
        Guardar en bd el hotel.
        Función que guarda en bd el hotel especificado.
        */
        return hotelRepository.save(entity);
    }

    public Hotel addHabitacion(int id, Habitacion habitacion) {
        // Añade al hotel una habitación.
        var existeHotel = hotelRepository.findById(id);
        // Compruebo si existe el hotel.
        if (existeHotel.isPresent()) {
            // Si existe, le añado la habitacion especificada y guardo el hotel en bd.
            var hotel = existeHotel.get();
            hotel.addHabitacion(habitacion);
            return hotelRepository.save(hotel);
        }
        // en función de si se existe hotel, devolverá dicho hotel para mostar los cambios.
        return null;
    }

    public boolean deleteHabitacion(int idHotel, int idHabitacion) {
        // Borrar una habitacion a un hotel.
        boolean borrado = false;
        // Comprobar si se ha borrado.
        if (hotelRepository.findById(idHotel).isPresent()) {
            // Compruebo si existe el hotel.
            Hotel hotel = hotelRepository.findById(idHotel).get();
            HabitacionService habitacionServices = new HabitacionService(habitacionRepository);
            // Instancio el servicio de habitaciones para poder averiguar si existe la habitación.
            if (habitacionServices.buscarHabitacionXID(idHabitacion).isPresent()) {
                Habitacion habitacion = habitacionServices.buscarHabitacionXID(idHabitacion).get();
                // Si existe, compruebo si la habitación pertenece al hotel que tenemos.
                if (habitacion.getHotel() == hotel) {
                    // En caso positivo, borro la habitacion del hotel y de la bd y devuelvo el boolean en true.
                    hotel.deleteHabitacion(habitacion);
                    habitacionServices.deleteById(habitacion.getId());
                    borrado = true;
                }
            }
        }
        // en función de si se ha eliminado o no la habitación, devolveré un boolean
        return borrado;
    }

}
