package sportconnectfx.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
    private String URL = "jdbc:mysql://localhost:3306/projet";
    private String USER = "root";
    private String PWD = "";
    private Connection cnx;

    public MyConnection() {
        try {
            cnx = DriverManager.getConnection(URL, USER, PWD);
            System.out.println("Connexion établie");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public Connection getCnx() {
        return cnx;
    }
}