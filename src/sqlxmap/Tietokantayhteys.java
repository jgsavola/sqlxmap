/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlxmap;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author jgsavola
 */
public class Tietokantayhteys {
    /**
     * Tietokantayhteyden tiedot.
     */
    private DatabaseInfo dbInfo;
    private Connection connection;
    
    /**
     * Luokan konstruktori.
     * 
     * @param dbInfo Tietokantayhteyden tiedot.
     */
    public Tietokantayhteys(DatabaseInfo dbInfo) {
        this.dbInfo = dbInfo;
    }
    
    /**
     * Yhdistä tietokantaan.
     * 
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public void yhdista() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");

        connection = DriverManager.getConnection(dbInfo.getJDBCURL());
        System.out.println("Connection successful!");
    }

    public void sulje() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}
