package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Kris
 */
public class DatabaseConnection {

    final private String urlRoot = "localhost";
    final private String urlPort = "3306";
    final private String dbUser = "DatabaseConnection";
    final private String dbPassword = "fMBTBgjNRZyyiv3C";
    final private String dbRoot = "agenda";
    public Connection connection = null;
    public ResultSet resultSet;
    public Statement statement;

    public String getUrlRoot() {
        return urlRoot;
    }

    public String getUrlPort() {
        return urlPort;
    }

    public String getDbUser() {
        return dbUser;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public String getDbRoot() {
        return dbRoot;
    }

    public void connectDatabase() {

        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (Exception e) {
                System.err.println("ERROR no se puede registrar el driver \n" + e);
            }

            try {
                connection = DriverManager.getConnection(
                        "jdbc:mysql://" + urlRoot + ":" + urlPort + "/" + dbRoot,
                        dbUser,
                        dbPassword);
            } catch (SQLException sqle) {
                System.err.println("ERROR: no se puede conectar a la DB \n" + sqle);
            }

            try {
                boolean valid = connection.isValid(50000);
                System.out.println(valid ? "Conexion con la base de datos establecida" : "ERR 404");
                statement = connection.createStatement();
            } catch (SQLException sqle) {
                System.err.println("ERROR: no se puede conectar a la DB \n" + sqle);
            }

        } catch (Exception e) {
            System.err.println("ERROR: \n" + e);
        }
    }

    public void CloseConnection() {
        try {
            System.out.println("--------------------------");
            if (statement != null) {
                statement.close();
                System.out.println("|   statement cerrado    |");
            }
            if (resultSet != null) {
                resultSet.close();
                System.out.println("|   resultSet cerrado    |");
            }
            if (connection != null) {
                connection.close();
                System.out.println("|conexión con DB cerrada |");
            }
            System.out.println("--------------------------");
        } catch (SQLException e) {
            System.err.println("ERROR: No se pudo cerrar la conexion con DB " + e);
        }
    }
}