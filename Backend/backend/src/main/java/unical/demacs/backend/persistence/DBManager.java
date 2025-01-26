package unical.demacs.backend.persistence;

import unical.demacs.backend.persistence.DAO.JDBC.AnnuncioDAOJDBC;
import unical.demacs.backend.persistence.DAO.JDBC.CategoriaDAOJDBC;
import unical.demacs.backend.persistence.DAO.JDBC.RecensioneDAOJDBC;
import unical.demacs.backend.persistence.DAO.JDBC.UtenteDAOJDBC;
import unical.demacs.backend.persistence.DAO.interfaces.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {

    private static DBManager instance = null;
    private Connection connection = null;

    //HO UNA SERIE DI DAO, TUTTI QUA IN DBMANAGER, CHE VENGONO UTILIZZATI (SE MI SERVONO) ALL'INTERNO DEGLI ALTRI DAO

    private AnnuncioDAO annuncioDAO = null;
    private RecensioneDAO recensioneDAO = null;
    private UtenteDAO utenteDAO = null;
    private CategoriaDAO categoriaDAO = null;

    public AnnuncioDAO getAnnuncioDAO() {
        if(annuncioDAO == null) {
            annuncioDAO = new AnnuncioDAOJDBC(connection);
        }
        return annuncioDAO;
    }

    public RecensioneDAO getRecensioneDAO() {
        if(recensioneDAO == null) {
            recensioneDAO = new RecensioneDAOJDBC(connection);
        }
        return recensioneDAO;
    }

    public UtenteDAO getUtenteDAO() {
        if(utenteDAO == null) {
            utenteDAO = new UtenteDAOJDBC(connection);
        }
        return utenteDAO;
    }


    public CategoriaDAO getCategoriaDAO() {
        if(categoriaDAO == null) {
            categoriaDAO = new CategoriaDAOJDBC(connection);
        }
        return categoriaDAO;
    }


    private DBManager() {}

    public static DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    public Connection getConnection() {
        if(this.connection == null){
            try{
                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/DeMaCSzon", "postgres", "bruno");
            }
            catch (SQLException e){
                throw new RuntimeException(e);
            }
        }

        return connection;
    }


}
