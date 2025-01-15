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
        return listarHabitaciones().stream().filter(hotel -> hotel.getId() == id).findFirst();
    }


    public List<Habitacion> filtrarHabitacionXTamanno(int tamanno) {
        return buscarHabitacionesLibres().stream().filter(habitacion -> habitacion.getTamanno() == tamanno).toList();
    }

    public List<Habitacion> filtrarHabitacionXPrecios(double precio1, double precio2) {
        return (precio1 >= precio2) ?
                buscarHabitacionesLibres().stream().filter(habitacion ->
                        habitacion.getPrecio() >= precio2 && habitacion.getPrecio() < precio1).toList() :
                buscarHabitacionesLibres().stream().filter(habitacion ->
                        habitacion.getPrecio() >= precio1 && habitacion.getPrecio() < precio2).toList();
    }

    private List<Habitacion> buscarHabitacionesLibres() {
        return listarHabitaciones().stream().filter(habitacion -> !habitacion.isOcupada()).toList();
    }

    public <S extends Habitacion> S save(S entity) {
        return habitacionRepository.save(entity);
    }

    public void deleteById(Integer integer) {
        habitacionRepository.deleteById(integer);
    }

    public void updateHabitacion(int id) {
        if (buscarHabitacionXID(id).isPresent()) {
            Habitacion habitacion = buscarHabitacionXID(id).get();
            habitacion.setOcupada(true);
            habitacionRepository.save(habitacion);
        }
    }
}
