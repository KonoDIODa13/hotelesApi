package com.example.hotelesapi.Repositorys;

import com.example.hotelesapi.Entities.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface HabitacionRepository extends JpaRepository<Habitacion, Integer> {

    @Query("SELECT h FROM Habitacion h WHERE h.tamanno =:tamanno")
    public List<Habitacion> getHabitacionsByTamanno(int tamanno);

    @Query("SELECT h FROM Habitacion h WHERE h.precio>=:precioMenor and h.precio<:precioMayor")
    public List<Habitacion> getHabitacionsByPrecioBetween(double precioMenor, double precioMayor);

    @Query("SELECT h FROM Habitacion h WHERE h.id=:id")
    public Optional<Habitacion> getHabitacionById(int id);

    @Query("SELECT h FROM Habitacion h WHERE h.ocupada=false")
    public List<Habitacion> getHabitacionsFree();
}
