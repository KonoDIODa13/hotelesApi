package com.example.hotelesapi.Controllers;

import com.example.hotelesapi.Dtos.HabitacionDTO;
import com.example.hotelesapi.Dtos.HotelDTO;
import com.example.hotelesapi.Entities.Hotel;
import com.example.hotelesapi.Services.HotelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/hotel")
public class HotelController {
    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("/")
    public ResponseEntity<?> listarHoteles() {
        List<Hotel> listaHotel = hotelService.listarHoteles();
        return !listaHotel.isEmpty() ?
                new ResponseEntity<List<Hotel>>(listaHotel, HttpStatus.ACCEPTED) :
                new ResponseEntity<String>("No se ha podido encontrar ningun hotel ", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarHotelXID(@PathVariable int id) {
        Optional<Hotel> hotel = hotelService.buscarHotelXID(id);
        return hotel.isPresent() ?
                new ResponseEntity<Hotel>(hotel.get(), HttpStatus.ACCEPTED) :
                new ResponseEntity<String>("No se ha podido encontrar un hotel cuyo id sea " + id, HttpStatus.BAD_REQUEST);

    }

    @GetMapping("/filtrar/categoria/{categoria}")
    public ResponseEntity<?> filtrarXCategoria(@PathVariable int categoria) {
        List<Hotel> listaHotel = hotelService.filtrarXCategoria(categoria);
        return !listaHotel.isEmpty() ?
                new ResponseEntity<List<Hotel>>(listaHotel, HttpStatus.ACCEPTED) :
                new ResponseEntity<String>("No se ha podido encontrar ningun hotel cuya categoria sea " + categoria, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/filtrar/localidad/{localidad}")
    public ResponseEntity<?> filtrarXLocalidad(@PathVariable String localidad) {
        List<Hotel> listaHotel = hotelService.filtrarXLocalidad(localidad);
        return listaHotel.size() != 0 ?
                new ResponseEntity<List<Hotel>>(listaHotel, HttpStatus.ACCEPTED) :
                new ResponseEntity<String>("No se ha podido encontrar ningun hotel cuya localidad sea " + localidad, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/annadirHotel")
    public ResponseEntity<?> insertarHotel(@RequestBody HotelDTO hotelDTO) {
        return  hotelDTO.compruebaCampos() ?
                new ResponseEntity<Hotel>(hotelService.guardarHotel(hotelDTO.fromDToToHotel()), HttpStatus.CREATED) :
                new ResponseEntity<String>("No se ha podido guardar el hotel", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/{id}/annadirHabitacion")
    public ResponseEntity<?> guardarHabitacion(@PathVariable int id, @RequestBody HabitacionDTO habitacionDTO) {
        return habitacionDTO.compruebaCampos() ?
                new ResponseEntity<Hotel>(hotelService.addHabitacion(id, habitacionDTO.fromDTOToHabitacion()), HttpStatus.CREATED) :
                new ResponseEntity<String>("No se ha podido guardar la habitacion en el hotel", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{idHotel}/habitacion/{idHabitacion}/eliminarHabitacion")
    public ResponseEntity<?> eliminarHabitacion(@PathVariable int idHotel, @PathVariable int idHabitacion) {
        return hotelService.deleteHabitacion(idHotel, idHabitacion) ?
                new ResponseEntity<Hotel>(hotelService.buscarHotelXID(idHotel).get(), HttpStatus.ACCEPTED) :
                new ResponseEntity<String>("No se ha podido borrar la habitacion del hotel", HttpStatus.BAD_REQUEST);

    }


}
