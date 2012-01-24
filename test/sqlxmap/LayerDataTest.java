/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlxmap;

import com.vividsolutions.jts.geom.Envelope;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author jonne
 */
public class LayerDataTest {
    private GeometryFactory gf = new GeometryFactory();
    private WKTReader wktReader = new WKTReader(gf);

    private String testWKT1;
    private String testWKT2;
    private Geometry testGeometry1;
    private Geometry testGeometry2;
    
    public LayerDataTest() {
        testWKT1 = "POINT(100 100)";
        testWKT2 = "LINESTRING(0 0, 200 0, 200 200, 0 200, 0 0)";
        try {
            testGeometry1 = wktReader.read(testWKT1);
            testGeometry2 = wktReader.read(testWKT2);
        } catch (ParseException ex) {
            Logger.getLogger(LayerDataTest.class.getName()).log(Level.SEVERE, null, ex);
        }
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
     * Test of addGeometry method, of class LayerData.
     * 
     * Add one Geometry, test the envelope afterwards.
     */
    @Test
    public void testAddGeometry_Geometry_OneGeometry() {
        System.out.println("addGeometry");
        LayerData instance = new LayerData();
        instance.addGeometry(testGeometry1);
        Envelope testEnvelope = new Envelope(100, 100, 100, 100);
        
        System.out.println("envelope: " + instance.getEnvelope());
        
        assertTrue("addGeometry extends layer's envelope.", 
                instance.getEnvelope().toString().equals( testEnvelope.toString() ));
    }

    /**
     * Test of addGeometry method, of class LayerData.
     * 
     * Add two Geometries, test the envelope afterwards.
     */
    @Test
    public void testAddGeometry_Geometry_TwoGeometries() {
        System.out.println("addGeometry");
        LayerData instance = new LayerData();
        instance.addGeometry(testGeometry1);
        instance.addGeometry(testGeometry2);
        Envelope testEnvelope = new Envelope(0, 200, 0, 200);
        
        System.out.println("envelope: " + instance.getEnvelope());
        
        assertTrue("addGeometry extends layer's envelope.", 
                instance.getEnvelope().toString().equals( testEnvelope.toString() ));
    }

    /**
     * Test of addGeometry method, of class LayerData.
     * 
     * Add two WKT Geometries, test envelope afterwards.
     */
    @Test
    public void testAddGeometry_String_TwoGeometries() throws Exception {
        System.out.println("addGeometry");
        LayerData instance = new LayerData();
        instance.addGeometry(testWKT1);
        instance.addGeometry(testWKT2);
        Envelope testEnvelope = new Envelope(0, 200, 0, 200);

        assertTrue("addGeometry extends layer's envelope.", 
                instance.getEnvelope().toString().equals( testEnvelope.toString() ));
    }

    /**
     * Test of extendEnvelope method, of class LayerData.
     */
    @Test
    public void testExtendEnvelope() {
        System.out.println("extendEnvelope");
        Geometry geom = null;
        LayerData instance = new LayerData();
        instance.extendEnvelope(geom);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
