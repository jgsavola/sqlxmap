/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlxmap;

import java.awt.Color;
import java.util.Random;

/**
 * Generoi satunnaisia värejä sopivalla jakaumalla.
 * 
 * Jotta jaukauma olisi mahdollisimman tasainen, käytä kultaista leikkausta
 * seuraavan värin generoimiseen. Käytä HSV-väriavaruutta pastellivärien luomiseen.
 * 
 * Idea ja toteutus perustuvat Martin Ankerlin blogiin:
 * http://martin.ankerl.com/2009/12/09/how-to-create-random-colors-programmatically/
 * 
 * @author jonne
 */
public class SatunnainenVari implements Varisarja {
    private double hue;
    private double saturation;
    private double value;
    
    private final double kultainenLeikkaus = 0.618033988749895;
    
    /**
     * Alkuarvot: saturation: 0.5, value: 0.95, hue: satunnainen.
     */
    public SatunnainenVari() {
        Random r = new Random();
        
        init(0.5, 0.95, r.nextDouble());
    }
    
    /**
     * Alkuarvoina otetaan kaikki HSV-komponentit.
     * 
     * @param hue värisävy
     * @param saturation värikylläisyys
     * @param value valoisuus
     */
    public SatunnainenVari(double hue, double saturation, double value) {
        init(hue, saturation, value);
    }

    /**
     * Alkuarvona otetaan värisävy.
     * 
     * @param hue värisävy
     */
    public SatunnainenVari(double hue) {
        init(hue, 0.5, 0.95);
    }

    private void init(double hue, double saturation, double value) {
        this.hue = hue;
        this.saturation = saturation;
        this.value = value;
    }
    
    @Override
    public Color seuraavaVari() {
        hue = (hue + kultainenLeikkaus) % 1.0;
        
        return hsv2rgb(hue, saturation, value);
    }

    private static Color hsv2rgb(double h, double s, double v) {
        int h_i = (int)(h*6);
        double f = h*6 - h_i;
        double p = v * (1 - s);
        double q = v * (1 - f*s);
        double t = v * (1 - (1 - f) * s);
        double r, g, b;

        switch (h_i) {
            case 0:
                r = v;
                g = t;
                b = p;
                break;
            case 1:
                r = q;
                g = v;
                b = p;
                break;
            case 2:
                r = p;
                g = v;
                b = t;
                break;
            case 3:
                r = p;
                g = q;
                b = v;
                break;
            case 4:
                r = t;
                g = p;
                b = v;
                break;
            case 5:
                r = v;
                b = p;
                g = q;
                break;
            default:
                r = g = b = 0.0;
        }

        //System.out.println("h " + h + " s " + s + " v " + v);
        //System.out.println("r " + r + " g " + g + " b " + b);
        return new Color((float)r, (float)g, (float)b);
    }
}
