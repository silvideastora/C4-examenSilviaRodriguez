package examenBackend.dao.impl;

import examenBackend.db.H2Connection;
import examenBackend.dao.IDao;
import examenBackend.model.Odontologo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoIDaoH2 implements IDao<Odontologo> {
    private static Logger LOGGER= Logger.getLogger(OdontologoIDaoH2.class);
    private static String SQL_INSERT_ODONTOLOGO="INSERT INTO ODONTOLOGOS VALUES(DEFAULT,?,?,?)";
    private static String SQL_BUSCAR_ALL="SELECT * FROM ODONTOLOGOS";


    @Override
    public Odontologo registrar(Odontologo odontologo) {
        Connection connection= null;
        Odontologo odontologoRetornar= null;

        try{

            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement= connection.prepareStatement(SQL_INSERT_ODONTOLOGO, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,odontologo.getMatricula());
            preparedStatement.setString(2,odontologo.getNombre());
            preparedStatement.setString(3,odontologo.getApellido());
            preparedStatement.executeUpdate();
            ResultSet resultSet= preparedStatement.getGeneratedKeys();
            while(resultSet.next()){
                Integer idNuevo= resultSet.getInt(1);
                odontologoRetornar= new Odontologo(idNuevo, odontologo.getMatricula(),odontologo.getNombre(),odontologo.getApellido());
                LOGGER.info("odontologo agregado"+ odontologo);
            }

            connection.commit();
            connection.setAutoCommit(true);
        } catch (Exception e){
            if(connection!=null){
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    LOGGER.error(ex.getMessage());
                }
            }
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
                e.printStackTrace();
            }
        }

        return odontologoRetornar;
    }

    @Override
    public List<Odontologo> buscarTodos() {
        Connection connection=null;
        Odontologo odontologo= null;
        List<Odontologo> resultado= new ArrayList<>();

        try{
            connection=H2Connection.getConnection();
            Statement statement= connection.createStatement();
            ResultSet resultSet= statement.executeQuery(SQL_BUSCAR_ALL);
            while (resultSet.next()){
                Integer id=resultSet.getInt(1);
                String matricula= resultSet.getString(2);
                String nombre= resultSet.getString(3);
                String apellido= resultSet.getString(4);
                odontologo= new Odontologo(id,matricula,nombre,apellido);
                LOGGER.info("odnotologo" + odontologo);
                resultado.add(odontologo);
            }
        } catch (Exception e){
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
                e.printStackTrace();
            }
        }

        return resultado;
    }
}
