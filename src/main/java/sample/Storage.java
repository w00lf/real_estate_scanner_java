package sample;

import org.flywaydb.core.Flyway;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by w00lf on 20.06.2015.
 */
public class Storage {
    private final String DATABASE = "jdbc:sqlite:sample.db";
    private final String STATISTICS_TABLE = "statistics";
    private Connection connection;

    Storage() throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        establishConnection();
        migrateSchema();
    }

    public boolean writeStatistics(String metro, int price, String site_inner_id){
        return updateQuery(
                String.format("insert into %s values(%s, %i, %s)",
                        STATISTICS_TABLE,
                        metro,
                        price,
                        site_inner_id)
        );
    }

    private void migrateSchema() {
        Flyway flyway = new Flyway();
        flyway.setDataSource(DATABASE, "", "");
        flyway.migrate();
    }

    private ResultSet readQuary(String query) {
        if (connection == null)
            establishConnection();
        ResultSet res = null;
        try
        {
            // create a database connection
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            res = statement.executeQuery(query);
        }catch(SQLException e)
        {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }
        return res;
    }

    private boolean updateQuery(String query) {
        if (connection == null)
            establishConnection();
        Boolean result = false;
        try{
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            result =  statement.executeUpdate(query) == 1;
        } catch(SQLException e)
        {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }
        return result;
    }

    private void establishConnection() {
        try
        {
            // create a database connection
            connection = DriverManager.getConnection(DATABASE);
        }
        catch(SQLException e)
        {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }
    }
}
