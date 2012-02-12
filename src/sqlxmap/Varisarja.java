/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlxmap;

import java.awt.Color;

/**
 * Sarja <code>Color</code>-olioita, joita voi käyttää esimerkiksi karttasarjojen
 * värien tuottamiseen. <code>Varisarja</code> voi olla tilallinen tai tilaton.
 * 
 * @author jonne
 */
public interface Varisarja {

    /**
     * Anna seuraava väri sarjasta.
     * 
     * @return <code>Color</code>-olio.
     */
    Color seuraavaVari();
}
