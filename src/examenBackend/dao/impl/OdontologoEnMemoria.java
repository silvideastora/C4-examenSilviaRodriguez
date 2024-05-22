package examenBackend.dao.impl;

import examenBackend.dao.IDao;
import examenBackend.model.Odontologo;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class OdontologoEnMemoria implements IDao<Odontologo> {

    private Logger LOGGER = Logger.getLogger(OdontologoEnMemoria.class);
    List<Odontologo> odontologos = new ArrayList<>();


    @Override
    public Odontologo registrar(Odontologo odontologo) {
        Integer id = odontologos.size() + 1;
        odontologo.setId(id);
        odontologos.add(odontologo);

        LOGGER.info("Odontologo guardado en memoria" + odontologo);

        return odontologo;
    }

    @Override
    public List<Odontologo> buscarTodos() {

        return odontologos;
    }
}
