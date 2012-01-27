/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlxmap;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * Tietokantayhteyden parametrit.
 * 
 * @author jonne
 * 
 */
public class DatabaseInfo {
    /**
     * Tietokantaklusterin isäntäkoneen nimi tai IP-osoite (oletus localhost).
     */
    private String dbHost;
    
    /**
     * Tietokantaklusterin portti (oletus 5432).
     */
    private int dbPort;
    
    /**
     * Tietokannan nimi (oletus käyttäjän login-tunnus).
     */
    private String dbName;
    
    /**
     * Tietokantaklusterin käyttäjätunnus (oletus käyttäjän login-tunnus).
     */
    private String dbUser;
    
    /**
     * Tietokantaklusterin salasana (oletus "").
     */
    private String dbPassword;

    /**
     * Luokan konstruktori.
     * 
     * Oletusarvot: dbHost=localhost, dbPort=5432, dbName=user.name, dbUser=user.name, dbPassword=""
     */
    public DatabaseInfo() {
        dbHost = "localhost";
        dbPort = 5432;
        dbName = System.getProperty("user.name");
        dbUser = dbName;
        dbPassword = "";
    }

    /**
     * Luokan konstruktori kaikilla parametreilla.
     * 
     * @param dbHost
     * @param dbPort
     * @param dbName
     * @param dbUser
     * @param dbPassword 
     */
    public DatabaseInfo(String dbHost, int dbPort, String dbName, String dbUser, String dbPassword) {
        this.dbHost = dbHost;
        this.dbPort = dbPort;
        this.dbName = dbName;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    /**
     * Antaa tietokantaklusterin isäntäkoneen.
     * 
     * @return Tietokantaklusterin isäntäkone.
     */
    
    public String getDbHost() {
        return dbHost;
    }

    /**
     * Muodostaa helposti luettavan teksti-muotoisen esityksen parametreista.
     * 
     * @return Tekstimuotoinen esitys.
     */
    @Override
    public String toString() {
        return "DatabaseInfo{" + "dbHost=" + dbHost + ", dbPort=" + dbPort + ", dbName=" + dbName + ", dbUser=" + dbUser + ", dbPassword=" + dbPassword + '}';
    }

    /**
     * Muodostaa JDBC:n yhteys-URL:n.
     * 
     * @return JDBC-yhteys-URL.
     */
    public String getJDBCURL() {
        return "jdbc:postgresql://" + dbHost + ":" + dbPort + "/" + dbName + "?user=" + dbUser + "&password=" + dbPassword; 
    }
    
    /**
     * Asettaa tietokantaklusterin isäntäkoneen.
     * 
     * @param dbHost Tietokantaklusterin isäntäkone.
     */
    public void setDbHost(String dbHost) {
        this.dbHost = dbHost;
    }

    /**
     * Antaa tietokannan nimen.
     * 
     * @return Tietokannan nimi.
     */
    public String getDbName() {
        return dbName;
    }

    /**
     * Asettaa tietokannan nimen.
     * 
     * @param dbName Tietokannan nimi.
     */        
    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    /**
     * Antaa tietokantaklusterin salasanan.
     * 
     * @return Tietokantaklusterin salasana.
     */
    public String getDbPassword() {
        return dbPassword;
    }

    /**
     * Asettaa tietokantaklusterin salasanan.
     * 
     * @param dbName Tietokantalkusterin salasana.
     */        
    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    /** Antaa tietokantaklusterin portin.
     * 
     * @return Tietokantaklusterin portti.
     */
    public int getDbPort() {
        return dbPort;
    }

    /**
     * Asettaa tietokantaklusterin portin.
     * 
     * @param dbPort Tietokantaklusterin portti.
     */
    public void setDbPort(int dbPort) {
        this.dbPort = dbPort;
    }

    /**
     * Antaa tietokantaklusterin käyttäjätunnuksen.
     * 
     * @return Tietokantaklusterin käyttäjätunnus.
     */
    public String getDbUser() {
        return dbUser;
    }

    /**
     * Asettaa tietokantaklusterin käyttäjätunnuksen.
     * 
     * @param dbUser Tietokantaklusterin käyttäjätunnus.
     */
    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }
    
    /**
     * Lataa tietokannan asetukset Properties-tiedostosta.
     * 
     * @param propertiesFileName Properties-tiedoston nimi.
     * @throws IOException 
     */
    public void loadFromPropertiesFile(String propertiesFileName) throws IOException {
        File propFile = new File(propertiesFileName);
        Properties props = new Properties();
        
        props.load(new FileInputStream(propFile));
        DatabaseInfo db = new DatabaseInfo();
        this.setDbHost(props.getProperty("dbHost"));
        this.setDbPort(Integer.parseInt(props.getProperty("dbPort")));
        this.setDbName(props.getProperty("dbName"));
        this.setDbUser(props.getProperty("dbUser"));
        this.setDbPassword(props.getProperty("dbPassword"));
    }

    /**
     * Tallenna tietokannan asetukset Properties-tiedostoon.
     * 
     * @param propertiesFileName Properties-tiedoston nimi.
     * @throws IOException 
     */
    public void saveToPropertiesFile(String propertiesFileName) throws IOException {
        File propFile = new File(propertiesFileName);
        Properties props = new Properties();

        props.setProperty("dbHost", this.getDbHost());
        props.setProperty("dbPort", "" + this.getDbPort());
        props.setProperty("dbName", this.getDbName());
        props.setProperty("dbUser", this.getDbUser());
        props.setProperty("dbPassword", this.getDbPassword());

        props.store(new FileOutputStream(propFile), null);
    }
}
