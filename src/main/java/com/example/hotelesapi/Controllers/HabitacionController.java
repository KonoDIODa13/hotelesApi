package com.example.hotelesapi.Controllers;

import com.example.hotelesapi.Entities.Habitacion;
import com.example.hotelesapi.Services.HabitacionServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/habitacion")
public class HabitacionController {

    private final HabitacionServices habitacionService;

    public HabitacionController(HabitacionServices habitacionService) {
        this.habitacionService = habitacionService;
    }

    @GetMapping("/")
    public List<Habitacion> listarHabitaciones() {
        try {
            return habitacionService.listarHabitaciones();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error al obtener todos los hoteles", e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Habitacion> buscarHabitacionXID(@PathVariable int id) {
        try {
            return ResponseEntity.of(habitacionService.BuscarHabitacionXID(id));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error al obtener dicho hotel", e);
        }
    }
}