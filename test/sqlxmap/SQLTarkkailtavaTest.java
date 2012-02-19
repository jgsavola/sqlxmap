/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlxmap;

import java.util.Observable;
import java.util.Observer;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author jonne
 */
public class SQLTarkkailtavaTest {
    private class SQLTarkkailija implements Observer {
        private String saatuSQL = null;

        public String getSaatuSQL() {
            return saatuSQL;
        }

        @Override
        public void update(Observable o, Object o1) {
            this.saatuSQL = (String)o1;
        }
    }

    public SQLTarkkailtavaTest() {
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
     * Test of getSQL method, of class SQLTarkkailtava.
     */
    @Test
    public void testGetSQL() {
        System.out.println("getSQL");
        SQLTarkkailtava instance = new SQLTarkkailtava();
        String expResult = "123";
        instance.setSQL(expResult);
        String result = instance.getSQL();
        assertEquals(expResult, result);
    }

    /**
     * Test of setSQL method, of class SQLTarkkailtava.
     */
    @Test
    public void testSetSQL() {
        System.out.println("setSQL");
        SQLTarkkailija tarkkailija = new SQLTarkkailija();
        SQLTarkkailtava tarkkailtava = new SQLTarkkailtava();
        String SQL = "XYZZY";

        tarkkailtava.addObserver(tarkkailija);
        tarkkailtava.setSQL(SQL);

        assertEquals(SQL, tarkkailija.getSaatuSQL());
    }
}
