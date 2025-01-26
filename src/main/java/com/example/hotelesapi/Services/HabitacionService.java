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
        /*
        Listar todas las habitaciones.
        Función que devuelve la lista completa de habitaciones de la bd.
         */
        return habitacionRepository.findAll();
    }

    public Optional<Habitacion> buscarHabitacionXID(int id) {
        /*
        Buscar la habitación que tenga ese id.
        Recorro la lista de habitaciones mediante stream,
        busco la primera habitación de la lista por el id,
        si existe, me devolverá una habitación.
        */
        return listarHabitaciones().stream().filter(hotel -> hotel.getId() == id).findFirst();
    }


    public List<Habitacion> filtrarHabitacionXTamanno(int tamanno) {
        /*
        Buscar habitaciones que esten libres y que tengan dicho tamaño.
        Primero, obtengo una lista con las habitaciones libres, la recorro con stream y filtro si hay alguna habitación
        con dicho tamaño (solo puede ser 1 o 2) y lo devuelvo en forma de lista.
        */
        return buscarHabitacionesLibres().stream().filter(habitacion -> habitacion.getTamanno() == tamanno).toList();
    }

    public List<Habitacion> filtrarHabitacionXPrecios(double precioMenor, double precioMayor) {
        /*
        Buscar habitaciones que estén libres y que estén entre los precios especificados.
        Primero, obtengo una lista con las habitaciones libres, la recorro con stream y filtro en función
        los precios establecidos (gracias a la comprobacion que hacemos antes, sé cual es el precio menor y el mayor)
        y lo devuelvo en una lista.
        */
        return buscarHabitacionesLibres().stream().filter(habitacion ->
                habitacion.getPrecio() >= precioMenor && habitacion.getPrecio() < precioMayor).toList();
    }

    public <S extends Habitacion> S save(S entity) {
        /*
        Guardar una habitación.
        Función que guarda una habitación dada en la bd mediante Hibernate.
        */
        return habitacionRepository.save(entity);
    }

    public void deleteById(Integer integer) {
        /*
        Borrar una habitación.
        Función que borra una habitación dada en la bd mediante Hibernate.
        */
        habitacionRepository.deleteById(integer);
    }

    public boolean updateHabitacion(Habitacion habitacion) {
        // Modifica el estado de la habitación a ocupada.
        boolean modificado;
        // Con este boolean, compruebo si se ha modificado la habitación
        if (habitacion.isOcupada()) {
            // En caso de estar ya ocupada, devuelve falso ya que no la ha tenido que modificar.
            modificado = false;
        } else {
            // En caso contrario, la modifica, guarda el cambio en bd y devuelve true
            // ya que lo ha tenido que modificar
            habitacion.setOcupada(true);
            habitacionRepository.save(habitacion);
            modificado = true;
        }
        return modificado;
    }

    private List<Habitacion> buscarHabitacionesLibres() {
        /*
        Busco las habitacion que esten vacias.
        para ello, recorro todas las habitaciones con stream y filtro si el atributo de ocupada
        es false, si es asi, las mete en una lista que terminará devolviendo.
        */
        return listarHabitaciones().stream().filter(habitacion -> !habitacion.isOcupada()).toList();
    }
}
