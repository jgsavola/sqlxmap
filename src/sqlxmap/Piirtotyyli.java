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

    /**
     * Aseta peittävyys väliltä 0.0--100.0 sekä täyttövärille että peittovärille.
     * @param peittavyys Värin peittävyys (transparency/opacity).
     */
    public void asetaPeittavyysVareille(double peittavyys) {
        tayttovari = new Color(tayttovari.getRed(), tayttovari.getGreen(), tayttovari.getBlue(), (int)(255*peittavyys));
        piirtovari = new Color(piirtovari.getRed(), piirtovari.getGreen(), piirtovari.getBlue(), (int)(255*peittavyys));
    }
}
