package com.example.hotelesapi.Services;

import com.example.hotelesapi.Entities.Habitacion;
import com.example.hotelesapi.Repositorys.HabitacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HabitacionService {
    private final HabitacionRepository habitacionRepository;

    public HabitacionService(HabitacionRepository habitacionRepository) {
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

    public List<Habitacion> filtrarHabitacionXPrecios(double precioMenor, double precioMayor) {
        return buscarHabitacionesLibres().stream().filter(habitacion ->
                habitacion.getPrecio() >= precioMenor && habitacion.getPrecio() < precioMayor).toList();
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

    public boolean updateHabitacion(Habitacion habitacion) {
        boolean modificado;
        if (habitacion.isOcupada()) {
            modificado = false;
        } else {
            habitacion.setOcupada(true);
            habitacionRepository.save(habitacion);
            modificado = true;
        }
        return modificado;
    }
}
