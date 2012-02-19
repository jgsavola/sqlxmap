/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlxmap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Testaa JDBC-URL:n muodostamista ja tietokannan yhteystietojen tallentamista
 * Properties-tiedostoon.
 *
 * Huom! Testiluokka luo väliaikaistiedostoja väliaikaishakemistoon.
 * Väliaikaistiedostojen käsittelyä pitäisi parantaa toimivuuden ja
 * tietoturvan näkökulmasta.
 *
 * @author jonne
 */
public class DatabaseInfoTest {
    private File tempPropertiesFileName;

    public DatabaseInfoTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        tempPropertiesFileName = new File(System.getProperty("java.io.tmpdir"), "sqlxmap_test_database.properties");
    }

    @After
    public void tearDown() {
        tempPropertiesFileName.delete();
    }

    /**
     * Test of getJDBCURL method, of class DatabaseInfo.
     */
    @Test
    public void testGetJDBCURL() {
        System.out.println("getJDBCURL");
        DatabaseInfo instance = new DatabaseInfo("localhost", 5555, "Dbname", "User", "Password");
        String expResult = "jdbc:postgresql://localhost:5555/Dbname?user=User&password=Password";
        String result = instance.getJDBCURL();
        assertEquals(expResult, result);
    }

    /**
     * Test of loadFromPropertiesFile method, of class DatabaseInfo.
     */
    @Test
    public void testLoadFromPropertiesFileThatDoesNotExist() {
        System.out.println("loadFromPropertiesFileThatDoesNotExist");
        String propertiesFileName = "/zyx___ei_kai_ole_olemassakaan.properties";

        if (new File(propertiesFileName).exists())
            fail("Testin testLoadFromPropertiesFileThatDoesNotExist suorittaminen edellyttää, että tiedostoa '"
                    + propertiesFileName + "' ei ole olemassa.");

        DatabaseInfo instance = new DatabaseInfo();
        try {
            instance.loadFromPropertiesFile(propertiesFileName);
            fail("loadFromProperties ei tuottanut poikkeusta, vaikka yritettiin avata tiedostoa, jota ei ole olemassa.");
        } catch (FileNotFoundException ex) {
            // Tämä on odotettu tulos.
        } catch (Exception ex) {
            fail("Poikkeus ei ole FileNotFoundException, vaan " + ex);
       }
    }

    /**
     * Test of saveToPropertiesFile method, of class DatabaseInfo.
     */
    @Test
    public void testSaveToPropertiesFile_and_loadFromPropertiesFile() throws Exception {
        System.out.println("testSaveToPropertiesFile_and_loadFromPropertiesFile");
        DatabaseInfo dbInfo = new DatabaseInfo("localhost", 5555, "Dbname", "User", "Password");
        dbInfo.saveToPropertiesFile(tempPropertiesFileName.getPath());

        DatabaseInfo dbInfo2 = new DatabaseInfo();
        dbInfo2.loadFromPropertiesFile(tempPropertiesFileName.getPath());
        assertEquals(dbInfo.getJDBCURL(), dbInfo2.getJDBCURL());
    }
}
