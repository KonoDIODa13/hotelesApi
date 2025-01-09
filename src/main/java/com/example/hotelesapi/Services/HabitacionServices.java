package com.example.hotelesapi.Services;

import com.example.hotelesapi.Entities.Habitacion;
import com.example.hotelesapi.Repositorys.HabitacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HabitacionServices {
    private final HabitacionRepository habitacionRepository;

    public HabitacionServices(HabitacionRepository habitacionRepository) {
        this.habitacionRepository = habitacionRepository;
    }

    public List<Habitacion> listarHabitaciones() {
        return habitacionRepository.findAll();
    }

    public Optional<Habitacion> BuscarHabitacionXID(int id) {
        // return listarHabitaciones().stream().filter(hotel -> hotel.getId() == id).findFirst().orElse(null); en caso de que no fuera optional
        return listarHabitaciones().stream().filter(hotel -> hotel.getId() == id).findFirst();
    }


}
