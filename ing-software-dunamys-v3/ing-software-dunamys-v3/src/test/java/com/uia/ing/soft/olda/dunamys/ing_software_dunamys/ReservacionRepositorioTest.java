package com.uia.ing.soft.olda.dunamys.ing_software_dunamys;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Habitacion;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Model.Reservacion;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Repository.ReservacionRepositorio;

@DataJpaTest
class ReservacionRepositorioTest {

    @Autowired
    private TestEntityManager entityManager;
    
    @Autowired
    private ReservacionRepositorio reservacionRepo;

    @Test
    void testFindReservasPorHabitacionYFechas() {
        // Configurar datos de prueba
        Habitacion habitacion = new Habitacion(/* ... */);
        entityManager.persist(habitacion);
        
        Reservacion reserva = new Reservacion(/* ... */);
        entityManager.persist(reserva);
        
        // Ejecutar consulta
        List<Reservacion> resultados = reservacionRepo.findReservasPorHabitacionYFechas(
            habitacion.getId(), 
            reserva.getFechaIngreso(), 
            reserva.getFechaSalida()
        );
        
        // Verificar
        assertFalse(resultados.isEmpty());
    }
}
