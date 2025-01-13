package com.example.hotelesapi.Controllers;

import com.example.hotelesapi.Dtos.HabitacionDTO;
import com.example.hotelesapi.Entities.Habitacion;
import com.example.hotelesapi.Entities.Hotel;
import com.example.hotelesapi.Services.HabitacionServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
            return ResponseEntity.of(habitacionService.buscarHabitacionXID(id));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error al obtener dicho hotel", e);
        }
    }

    @GetMapping("/filtrarXprecio/precio1/{precio1}/precio2/{precio2}")
    public ResponseEntity<List<Habitacion>> filtrarHabitacionesXPrecio(@PathVariable double precio1, @PathVariable double precio2) {
        return ResponseEntity.ok(habitacionService.filtrarHabitacionXPrecios(precio1, precio2));
    }

    @GetMapping("/filtrarXtamanno/{tamanno}")
    public ResponseEntity<List<Habitacion>> filtrarHabitacionesXTamanno(@PathVariable int tamanno) {
        return ResponseEntity.ok(habitacionService.filtrarHabitacionXTamanno(tamanno));
    }

    @PutMapping("/{id}/ocupar")
    public ResponseEntity<?> ocuparHabitacion(@PathVariable int id) {
        habitacionService.updateHabitacion(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
