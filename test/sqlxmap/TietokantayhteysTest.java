/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlxmap;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author jgsavola
 */
public class TietokantayhteysTest {
    private static DatabaseInfo testDbInfo;
    
    public TietokantayhteysTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        testDbInfo = new DatabaseInfo();
        testDbInfo.loadFromPropertiesFile("testdatabase.properties");
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of yhdista method, of class Tietokantayhteys.
     */
    @Test
    public void testYhdista() {
        System.out.println("yhdista");

        Tietokantayhteys yhteys = new Tietokantayhteys(testDbInfo);
        try {
            yhteys.yhdista();
        } catch (Exception e) {
            fail("Tietokantayhteyden muodostaminen epäonnistui.");
        }
        
        /**
         * Siivoa tietokantayhteydet mahdollisuuksien mukaan.
         */
        try {
            yhteys.sulje();
        } catch (SQLException ex) {
            Logger.getLogger(TietokantayhteysTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testTeeKysely() {
        Tietokantayhteys yhteys = new Tietokantayhteys(testDbInfo);
        try {
            yhteys.yhdista();
        } catch (Exception e) {
            fail("Tietokantayhteyden muodostaminen epäonnistui.");
        }
        
        try {
            LayerData ld = yhteys.teeKysely("SELECT text1, the_geom FROM miljoona.cityp LIMIT 10");
        } catch (SQLException ex) {
            Logger.getLogger(TietokantayhteysTest.class.getName()).log(Level.SEVERE, null, ex);
            fail("Tietokantakysely epäonnistui.");
        }
    }
}
