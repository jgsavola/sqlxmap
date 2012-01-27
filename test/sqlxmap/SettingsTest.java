/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlxmap;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author jonne
 */
public class SettingsTest {
    String tempDir;
    
    public SettingsTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        tempDir = System.getProperty("java.io.tmpdir");
    }
    
    @After
    public void tearDown() {
        new File(tempDir, ".sqlxmap/databases.properties").delete();
        new File(tempDir, ".sqlxmap").delete();
    }


    @Test
    public void testSettingsAreStored() {
        Settings settings;

        DatabaseInfo dbInfo = new DatabaseInfo("testhost", 9999, "testdb", "testuser", "testpass");
        
        try {
            storeDatabaseSettings(dbInfo);
            settings = new Settings(tempDir);
        } catch (Exception ex) {
            Logger.getLogger(SettingsTest.class.getName()).log(Level.SEVERE, null, ex);
            fail("Loading of Settings failed");
            return;
        }

        DatabaseInfo dbInfo2 = settings.getDbInfo().get(0);

        System.out.println("dbInfo.getURL: " + dbInfo.getJDBCURL());
        
        assertTrue("Database settings are persistent.",
                dbInfo.getJDBCURL().equals(dbInfo2.getJDBCURL()));
    }
    
    private void storeDatabaseSettings(DatabaseInfo dbInfo) throws IOException {
        Settings settings;
        try {
            settings = new Settings(tempDir);
        } catch (Exception ex) {
            Logger.getLogger(SettingsTest.class.getName()).log(Level.SEVERE, null, ex);
            fail("Loading of Settings failed");
            return;
        }

        ArrayList<DatabaseInfo> databases = new ArrayList<DatabaseInfo>();
        databases.add(dbInfo);
        settings.setDbInfo(databases);
    }
          
}
