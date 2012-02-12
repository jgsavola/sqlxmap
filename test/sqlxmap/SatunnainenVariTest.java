/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlxmap;

import java.awt.Color;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author jonne
 */
public class SatunnainenVariTest {
    
    public SatunnainenVariTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
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
     * Hae kaksi väriä värisarjasta. Testi ei testaa oikeastaan muuta kuin
     * että palautusarvot ovat ei-null ja että ne ovat eri värejä.
     */
    @Test
    public void testSeuraavaVari() {
        System.out.println("seuraavaVari");
        SatunnainenVari instance = new SatunnainenVari();
        Color result1 = instance.seuraavaVari();
        Color result2 = instance.seuraavaVari();
        System.out.println("result1 " + result1 + "\nresult2 " + result2);
        assertFalse("Satunnaiset värit ovat eri värejä", result1.equals(result2));
    }
}
