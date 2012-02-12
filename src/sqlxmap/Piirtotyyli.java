/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlxmap;

import java.awt.Color;

/**
 * Piirtotyyli kohteille.
 *
 * @author jonne
 */
public class Piirtotyyli {
    private Color tayttovari;
    private Color piirtovari;
    private int viivanleveys;

    public Color getPiirtovari() {
        return piirtovari;
    }

    public void setPiirtovari(Color piirtovari) {
        this.piirtovari = piirtovari;
    }

    public Color getTayttovari() {
        return tayttovari;
    }

    public void setTayttovari(Color tayttovari) {
        this.tayttovari = tayttovari;
    }

    public int getViivanleveys() {
        return viivanleveys;
    }

    public void setViivanleveys(int viivanleveys) {
        this.viivanleveys = viivanleveys;
    }

    /**
     * Luo uusi piirtotyyli.
     *
     * @param tayttovari Täyttöväri -- null, jos monikulmioita ei täytetä.
     * @param piirtovari Piirtoväri -- null, jos viivoja ei piirretä.
     * @param viivanleveys Viivanleveys.
     */
    public Piirtotyyli(Color tayttovari, Color piirtovari, int viivanleveys) {
        this.tayttovari = tayttovari;
        this.piirtovari = piirtovari;
        this.viivanleveys = viivanleveys;
    }
}
