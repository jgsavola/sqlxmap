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
 * @author jonne
 */
public class Settings {
    private String settingsDirectory;
    private ArrayList<DatabaseInfo> dbInfo;

            
    Settings() throws Exception {
        load(System.getProperty("user.home"));
    }
    
    Settings(String settingsDirectory) throws Exception {
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
    
    private void loadDatabaseInfo() {
        dbInfo = new ArrayList<DatabaseInfo>();
        
        File propFile = new File(settingsDirectory, "databases.properties");
        Properties props = new Properties();
        try {
            props.load(new FileInputStream(propFile));
            DatabaseInfo db = new DatabaseInfo();
            db.setDbHost(props.getProperty("dbHost"));
            db.setDbPort(Integer.parseInt(props.getProperty("dbPort")));
            db.setDbName(props.getProperty("dbName"));
            db.setDbUser(props.getProperty("dbUser"));
            db.setDbPassword(props.getProperty("dbPassword"));

            dbInfo.add(db);
        } catch (IOException ex) {
            //Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void saveDatabaseInfo() {
        File propFile = new File(settingsDirectory, "databases.properties");
        Properties props = new Properties();
        try {
            DatabaseInfo db = dbInfo.get(0);
            props.setProperty("dbHost", db.getDbHost());
            props.setProperty("dbPort", "" + db.getDbPort());
            props.setProperty("dbName", db.getDbName());
            props.setProperty("dbUser", db.getDbUser());
            props.setProperty("dbPassword", db.getDbPassword());
            //save properties to project root folder
            props.store(new FileOutputStream(propFile), null);
        } catch (IOException ex) {
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public ArrayList<DatabaseInfo> getDbInfo() {
        return dbInfo;
    }
    
    public void setDbInfo(ArrayList<DatabaseInfo> dbInfo) {
        this.dbInfo = dbInfo;
        
        // Simulate persistent Settings -- save immediately.
        saveDatabaseInfo();
    }
}
