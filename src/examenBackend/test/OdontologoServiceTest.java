package examenBackend.test;

import examenBackend.dao.impl.OdontologoIDaoH2;
import examenBackend.model.Odontologo;
import examenBackend.service.OdontologoService;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;


public class OdontologoServiceTest {
    private static Logger LOGGER= Logger.getLogger(OdontologoServiceTest.class);
    private static OdontologoService odontologoService=new OdontologoService(new OdontologoIDaoH2());

    @BeforeAll
    static void crearTablas(){
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/parcial;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "sa");
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }
    @Test
    @DisplayName("se ingresa un odontologo")
    void ingreso(){
        Odontologo odontologo= new Odontologo("598469","juliana","vargas");
        Odontologo odontologBD= odontologoService.registrarOdontologo(odontologo);

        assertNotNull(odontologBD);

    }
    @Test
    @DisplayName("listar todos odontologos")
    void listar(){
        Odontologo odontologo=new Odontologo("294765","andres","pinzon");
        odontologoService.registrarOdontologo(odontologo);

        List<Odontologo> lista=odontologoService.buscarTodos();
        assertEquals(2,lista.size());
    }


}
