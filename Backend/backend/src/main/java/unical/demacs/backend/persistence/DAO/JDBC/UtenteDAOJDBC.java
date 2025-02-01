package unical.demacs.backend.persistence.DAO.JDBC;

import unical.demacs.backend.model.Annuncio;
import unical.demacs.backend.model.Recensione;
import unical.demacs.backend.model.Utente;
import unical.demacs.backend.persistence.DAO.Proxy.UtenteProxy;
import unical.demacs.backend.persistence.DAO.interfaces.UtenteDAO;
import unical.demacs.backend.persistence.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UtenteDAOJDBC implements UtenteDAO {

    private final Connection connection;

    public UtenteDAOJDBC(Connection connection) {
        this.connection = connection;
    }

    //TESTATA E FUNZIONA
    @Override
    public List<Utente> findAll() {
        String query = "SELECT * FROM utente";
        List<Utente> utenti = new ArrayList<>();

        try{
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                //Utente utente = new UtenteProxy();
                Utente utente = new Utente();
                utente.setAmministratore(resultSet.getBoolean("amministratore"));
                utente.setEmail(resultSet.getString("email"));
                utente.setPassword(resultSet.getString("password"));
                utente.setUsername(resultSet.getString("username"));
                utente.setTipo(resultSet.getBoolean("tipo"));
                utenti.add(utente);
            }

            return utenti;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //TESTATA E FUNZIONA
    @Override
    public Utente findByUsername(String username) {

        String query = "SELECT * FROM utente WHERE username = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Utente utente = new UtenteProxy();
                utente.setPassword(resultSet.getString("password"));
                utente.setUsername(resultSet.getString("username"));
                utente.setEmail(resultSet.getString("email"));
                utente.setAmministratore(resultSet.getBoolean("amministratore"));
                utente.setTipo(resultSet.getBoolean("tipo"));
                return utente;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    //TESTATA E FUNZIONA
    @Override
    public void save(Utente utente) {

        String query = "INSERT INTO utente (tipo, username, password, email, amministratore) VALUES (?, ?, ?, ?, ?)";

        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setBoolean(1, utente.getTipo());
            statement.setString(2, utente.getUsername());
            statement.setString(3, utente.getPassword());
            statement.setString(4, utente.getEmail());
            statement.setBoolean(5, utente.getAmministratore());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    // TESTATA E FUNZIONA
    @Override
    public void update(Utente utente, boolean amministratore) {

        //NOTA - LA FUNZIONE UPDATE SERVE SOLAMENTE PER SETTARLO (O MENO) AD AMMINISTRATORE, NIENT'ALTRO

        String query = "UPDATE utente SET amministratore = ? WHERE username = ?";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setBoolean(1, amministratore);
            preparedStatement.setString(2, utente.getUsername());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    //TESTATA E FUNZIONA
    @Override
    public void delete(Utente utente) {

        //PER ELIMINARE, UTILIZZO SOLAMENTE username DATO CHE E' CHIAVE PRIMARIA
        String query = "DELETE FROM utente WHERE username = ?";

        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, utente.getUsername());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //DEVO ELIMINARE TUTTI I SUOI ANNUNCI

        //NON ELIMINO LE SUE ASTE E LE SUE RECENSIONI PERCHE, QUANDO EFFETTUO LA DELETE NEGLI ANNUNCI
        //ELIMINO ANCHE LE ASTE E LE RECENSIONI. QUINDI MI BASTA ELIMINARE SOLAMENTE GLI ANNUNCI IN QUESTO CASO

        //TROVO GLI ANNUNCI DA ELIMINARE
        List<Annuncio> annunciDaEliminare = DBManager.getInstance().getAnnuncioDAO().findByUsernameUtente(utente.getUsername());

        for(Annuncio annuncio : annunciDaEliminare){
            DBManager.getInstance().getAnnuncioDAO().delete(annuncio);
        }

    }
}
