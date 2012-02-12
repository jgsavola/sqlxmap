/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlxmap;

import com.vividsolutions.jts.io.ParseException;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Hoitaa tietokantaan liittyvät asiat, kuten yhteyden luomisen ja kyselyt.
 * 
 * Tietokantayhteys luo yhdeyden tietokantaan ja suorittaa SQL-kyselyjä.
 * Kyselyiden tuloksista muodostetaan LayerData-olioita.
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
     * Luokan konstruktori. JDBC-yhteyttä ei automaattisesti luoda, vaan sitä
     * varten pitää kutsua <code>yhdista</code>-metodia.
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

    /**
     * Sulje JDBC-yhteys.
     * 
     * @throws SQLException 
     */
    public void sulje() throws SQLException {
        if (connection != null) {
            connection.close();
            connection = null;
        }
    }
    
    /**
     * Suorita annettu SQL-kysely ja luo siitä LayerData-olio.
     * 
     * Tässä versiossa kyselystä tunnistetaan ja luetaan ainoastaan
     * yksi <code>geometry</code>-tyyppinen sarake.
     * 
     * @param SQL Suoritettava SQL-kysely merkkijonona.
     * @return LayerData-olio, jossa on kyselyn tulosjoukko.
     * @throws SQLException 
     */
    public ArrayList<LayerData> teeKysely(String SQL) throws SQLException {
        ArrayList<LayerData> layerDataList = new ArrayList<LayerData>();
        
        Statement stmt = connection.createStatement();

        stmt.execute(SQL);
        while (true) {
            ResultSet rs = stmt.getResultSet();
            if (rs != null) {
                LayerData ld = tulkitseResultSet(rs);
                ld.setSQL(SQL);
                layerDataList.add(ld);
            }
            /**
             * Seuraava tulos on joko tulosjoukko tai päivitettyjen kohteiden
             * lukumäärä.
             */
            if (stmt.getMoreResults() == false && stmt.getUpdateCount() == -1)
                break;
        }

        return layerDataList;
    }

    private LayerData tulkitseResultSet(ResultSet rs) throws SQLException {
        int geometryColumnNum = 0;
        String geometryColumnTypeName = null;
        
        /**
         * Analysoi tulosjoukon tyyppi. Ainakin olemme kiinnostuneita kentistä,
         * jotka voivat sisältää geometriatietoa. Jos tulosjoukossa on
         * kenttiä, joiden tyyppi on "geometry", tapaus on selvä. Sen sijaan
         * on vaikeampi tunnistaa teksti- tai (binaari)tyyppisiä kenttiä
         * geometriakentiksi. Ehkä käyttäjän pitäisi auttaa jotenkin?
         * 
         * Ratkaisutapoja:
         * 
         * 1. Sopia geometriakentän nimi?
         * 2. Hakea ensimmäinen rivi ja yrittää tunnistaa geometrioita?
         *    Hauras tapa, mutta voisi toimia 99% tapauksista.
         *    Kentän arvo on NULL ==> epätosi negatiivinen,
         *    kentässä on sattumalta geometria ==> epätosi positiivinen) 
         * 
         * FIXME: tästä kannattaisi tehdä oma luokkansa, niin saisi 
         *        monimutkaisuuden piiloon.
         */
        ResultSetMetaData rsmd = rs.getMetaData();
        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            String columnTypeName = rsmd.getColumnTypeName(i);
            int columnType        = rsmd.getColumnType(i);
            String columnName     = rsmd.getColumnName(i);
            
            if ("geometry".equals(columnTypeName)) {
                geometryColumnNum = i;
                geometryColumnTypeName = "WKB";
            }
            
//            System.out.println("ColumnName: " + columnName + ", ColumnType: " + columnTypeName + "(" + columnType + ")");
        }

        LayerData ld = new LayerData();        
        while (rs.next()) {
            String geometry = rs.getString(geometryColumnNum);
//            System.out.println("rivi: " + geometry);
            try {
                if ("WKB".equals(geometryColumnTypeName)) {
                    ld.addWKBGeometry(geometry);
                }
            } catch (ParseException ex) {
                Logger.getLogger(Tietokantayhteys.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return ld;
    }
}
