package com.example.hotelesapi.Repositorys;

import com.example.hotelesapi.Entities.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HabitacionRepository extends JpaRepository<Habitacion, Integer> {
}
