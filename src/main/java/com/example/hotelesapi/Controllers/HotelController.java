package com.example.hotelesapi.Controllers;

import com.example.hotelesapi.Dtos.HabitacionDTO;
import com.example.hotelesapi.Dtos.HotelDTO;
import com.example.hotelesapi.Entities.Habitacion;
import com.example.hotelesapi.Entities.Hotel;
import com.example.hotelesapi.Services.HotelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/hotel")
public class HotelController {
    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("/")
    public List<Hotel> listarHoteles() {
        try {
            return hotelService.listarHoteles();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error al obtener todos los hoteles", e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hotel> buscarHotelXID(@PathVariable int id) {
        try {
            return ResponseEntity.of(hotelService.buscarHotelXID(id));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error al obtener dicho hotel", e);
        }
    }

    @GetMapping("/filtrarXcategoria/{categoria}")
    public ResponseEntity<List<Hotel>> filtrarXCategoria(@PathVariable int categoria) {
        return ResponseEntity.ok(hotelService.filtrarXCategoria(categoria));
    }

    @GetMapping("/filtrarXlocalidad/{localidad}")
    public ResponseEntity<List<Hotel>> filtrarXLocalidad(@PathVariable String localidad) {
        return ResponseEntity.ok(hotelService.filtrarXLocalidad(localidad));
    }

    @PostMapping("/annadirHotel")
    public ResponseEntity<Boolean> insertarHotel(@RequestBody HotelDTO hoteldto) {
        Hotel hotel = new Hotel();
        hotel.setNombre(hoteldto.nombre());
        hotel.setDescripcion(hoteldto.descripcion());
        hotel.setCategoria(hoteldto.categoria());
        hotel.setLocalidad(hoteldto.localidad());
        hotel.setPiscina(hoteldto.piscina());
        hotelService.guardarHotel(hotel);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/annadirHabitacion/hotel/{id}")
    public ResponseEntity<?> guardarHabitacion(@PathVariable int id, @RequestBody HabitacionDTO habitacionDTO) {
        Habitacion habitacion = new Habitacion();
        habitacion.setTamanno(habitacionDTO.tamano());
        habitacion.setPrecio(habitacionDTO.precio());
        habitacion.setDesayuno(habitacionDTO.desayuno());
        habitacion.setOcupada(habitacionDTO.ocupada());
        hotelService.addHabitacion(id, habitacion);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/eliminarHabitacion/hotel/{idHotel}/habitacion/{idHabitacion}")
    public ResponseEntity<?> eliminarHabitacion(@PathVariable int idHotel, @PathVariable int idHabitacion) {
        hotelService.deleteHabitacion(idHotel, idHabitacion);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }




}
