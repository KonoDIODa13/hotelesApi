package com.example.hotelesapi.Controllers;

import com.example.hotelesapi.Entities.Habitacion;
import com.example.hotelesapi.Entities.Hotel;
import com.example.hotelesapi.Services.HabitacionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/habitacion")
public class HabitacionController {

    private final HabitacionService habitacionService;

    public HabitacionController(HabitacionService habitacionService) {
        this.habitacionService = habitacionService;
    }

    @GetMapping("/")
    public ResponseEntity<?> listarHabitaciones() {
        List<Habitacion> listaHabitacion = habitacionService.listarHabitaciones();
        // Recogo todas las habitaciones de la bd en una lista.
        return !listaHabitacion.isEmpty() ?
                new ResponseEntity<List<Habitacion>>(listaHabitacion, HttpStatus.ACCEPTED) :
                new ResponseEntity<String>("No se ha podido encontrar ninguna habitación", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarHabitacionXID(@PathVariable int id) {
        Optional<Habitacion> habitacion = habitacionService.buscarHabitacionXID(id);
        // Si existe habitación, devolveré dicha habitación.
        return habitacion.isPresent() ?
                new ResponseEntity<Habitacion>(habitacion.get(), HttpStatus.ACCEPTED) :
                new ResponseEntity<String>("No se pudo encontrar ninguna habitacion cuyo id sea " + id, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/filtrar/precio/{precio1},{precio2}")
    public ResponseEntity<?> filtrarHabitacionesXPrecio(@PathVariable double precio1, @PathVariable double precio2) {
        double precioMayor;
        double precioMenor;
        // Compruebo cual de los dos es mayor.
        if (precio1 > precio2) {
            precioMayor = precio1;
            precioMenor = precio2;
        } else if (precio1 < precio2) {
            precioMayor = precio2;
            precioMenor = precio1;
        } else {
            // Si ambos precios son iguales, devuelvo un mensaje de error.
            return new ResponseEntity<String>("No pueden ser los dos precios los mismos.", HttpStatus.BAD_REQUEST);
        }
        List<Habitacion> listaHabitacion = habitacionService.filtrarHabitacionXPrecios(precioMenor, precioMayor);
        // Si no encuentra habitaciones entre esos precios, devolverá quen o ha encontrado ninguna, en caso contrario,
        // devolverá una lista con las habitaciones.
        return !listaHabitacion.isEmpty() ?
                new ResponseEntity<List<Habitacion>>(listaHabitacion, HttpStatus.ACCEPTED) :
                new ResponseEntity<String>("No se ha podido encontrar habitaciones entre los precios " + precioMenor + " y " + precioMayor, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/filtrar/tamanno/{tamanno}")
    public ResponseEntity<?> filtrarHabitacionesXTamanno(@PathVariable int tamanno) {
        List<Habitacion> listaHabitacion = habitacionService.filtrarHabitacionXTamanno(tamanno);
        return !listaHabitacion.isEmpty() ?
                new ResponseEntity<List<Habitacion>>(listaHabitacion, HttpStatus.ACCEPTED) :
                new ResponseEntity<String>("No se ha podido encontrar habitaciones cuyo tamaño sea " + tamanno, HttpStatus.BAD_REQUEST);

    }

    @PutMapping("/{id}/ocupar")
    public ResponseEntity<?> ocuparHabitacion(@PathVariable int id) {
        Optional<Habitacion> habitacionOptional = habitacionService.buscarHabitacionXID(id);
        if (habitacionOptional.isPresent()) {
            Habitacion habitacion = habitacionOptional.get();
            return habitacionService.updateHabitacion(habitacion) ?
                    new ResponseEntity<Habitacion>(habitacion, HttpStatus.ACCEPTED) :
                    new ResponseEntity<String>("La habitacion cuyo id es " + id + " ya esta ocupada.", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<String>("No se pudo encontrar ninguna habitacion cuyo id sea " + id, HttpStatus.BAD_REQUEST);
        }
    }

}
