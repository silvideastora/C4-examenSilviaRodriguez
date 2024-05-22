package examenBackend.test;

import examenBackend.dao.impl.OdontologoEnMemoria;
import examenBackend.model.Odontologo;
import examenBackend.service.OdontologoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OdontologoEnMemoriaTest {
    private static OdontologoService odontologoService = new OdontologoService( new OdontologoEnMemoria());

    @Test
    @DisplayName("Testear que un paciente fue guardado")
    void testOdontologoGuardadoMemoria(){
        Odontologo odontologo = new Odontologo("23456","John","Wick");
        Odontologo odontologoDesdeMemoria = odontologoService.registrarOdontologo(odontologo);

        assertNotNull(odontologoDesdeMemoria);
    }
    @Test
    @DisplayName("Testear que puedes buscar todos los odontologos")
    void buscarTodos(){
        Odontologo odontologo = new Odontologo("38790","Beatrix","Kiddo");
        Odontologo odontologoDesdeMemoria = odontologoService.registrarOdontologo(odontologo);

        List<Odontologo> pacientes = odontologoService.buscarTodos();

        assertEquals(1, pacientes.size());
    }


}