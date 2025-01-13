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

    public Optional<Habitacion> buscarHabitacionXID(int id) {
        // return listarHabitaciones().stream().filter(hotel -> hotel.getId() == id).findFirst().orElse(null); en caso de que no fuera optional
        return listarHabitaciones().stream().filter(hotel -> hotel.getId() == id).findFirst();
    }


    public List<Habitacion> filtrarHabitacionXTamannos(int tamanno1, int tamanno2) {
        return (tamanno1 >= tamanno2) ?
                buscarHabitacionesLibres().stream().filter(habitacion ->
                        habitacion.getTamanno() >= tamanno2 && habitacion.getTamanno() < tamanno1).sorted().toList() :
                buscarHabitacionesLibres().stream().filter(habitacion ->
                        habitacion.getTamanno() >= tamanno1 && habitacion.getTamanno() < tamanno2).sorted().toList();
    }

    public List<Habitacion> filtrarHabitacionXPrecios(double precio1, double precio2) {
        List<Habitacion> habitaciones = buscarHabitacionesLibres().stream().filter(habitacion ->
                habitacion.getPrecio() >= precio2 && habitacion.getPrecio() < precio1).toList();
        System.out.println(habitaciones);
        return (precio1 >= precio2) ?
                buscarHabitacionesLibres().stream().filter(habitacion ->
                        habitacion.getPrecio() >= precio2 && habitacion.getPrecio() < precio1).toList() :
                buscarHabitacionesLibres().stream().filter(habitacion ->
                        habitacion.getPrecio() >= precio1 && habitacion.getPrecio() < precio2).toList();
    }

    private List<Habitacion> buscarHabitacionesLibres() {
        return listarHabitaciones().stream().filter(habitacion -> !habitacion.isOcupada()).toList();
    }


}
