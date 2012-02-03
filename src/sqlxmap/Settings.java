/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlxmap;

import java.io.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Tallenna ja lataa käyttäjäasetuksia.
 * 
 * <code>Settings</code> huolehtii käyttäjäkohtaisten asetusten lukemistesta
 * ja tallentamisesta. Oletussijainti tallennettaville tiedostoille on
 * käyttäjän kotihakemisto (<code>user.home</code>).
 * 
  * @author jonne
 */
public class Settings {
    private String settingsDirectory;
    private ArrayList<DatabaseInfo> dbInfo;

            
    public Settings() throws Exception {
        load(System.getProperty("user.home"));
    }
    
    public Settings(String settingsDirectory) throws Exception {
        load(settingsDirectory);
    }
    
    final void load(String homeDirectory) throws Exception {        
        File f = new File(homeDirectory, ".sqlxmap");
        settingsDirectory = f.getPath();
        if (!f.exists()) {
            if (!f.mkdir()) {
                throw new Exception();
            }
        }
        
        loadDatabaseInfo();
    }
    
    private void loadDatabaseInfo() throws IOException {
        File propFile = new File(settingsDirectory, "databases.properties");

        dbInfo = new ArrayList<DatabaseInfo>();
        DatabaseInfo db = new DatabaseInfo();

        try {
            db.loadFromPropertiesFile(propFile.getPath());
        } catch (FileNotFoundException ex) {
            /*
             * Ei ole virhe, jos properties-tiedostoa ei löydy. Se vain
             * tarkoittaa, että tietokantayhteyksiä vielä ei ole tallennettu
             * tälle käyttäjälle.
             * 
             * Sen sijaan muut poikkeukset ovat virheitä ja päästetään
             * ketjua eteenpäin.
             */
        }

        dbInfo.add(db);
    }

    private void saveDatabaseInfo() throws IOException {
        File propFile = new File(settingsDirectory, "databases.properties");
        DatabaseInfo db = dbInfo.get(0);
        
        db.saveToPropertiesFile(propFile.getPath());
    }
    
    
    public ArrayList<DatabaseInfo> getDbInfo() {
        return dbInfo;
    }
    
    public void setDbInfo(ArrayList<DatabaseInfo> dbInfo) throws IOException {
        this.dbInfo = dbInfo;
        
        // Simulate persistent Settings -- save immediately.
        saveDatabaseInfo();
    }
}
