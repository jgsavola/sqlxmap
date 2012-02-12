/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlxmap;

import com.vividsolutions.jts.geom.*;
import com.vividsolutions.jts.geom.util.AffineTransformation;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Karttatasossa säilytetään karttatason tietoja, kuten LayerData-oliota,
 * karttatason nimeä, SQL-lausetta, näyttötyylejä, yms.
 *
 * @author jonne
 */
public class Karttataso {
    private LayerData layerData;
    private Color piirtovari;

    /**
     * Luo uusi karttataso.
     */
    public Karttataso() {
    }

    /**
     * Luo uusi karttataso.
     *
     * @param layerData Karttatason geometriat.
     */
    public Karttataso(LayerData layerData) {
        this.layerData = layerData;
    }

    /**
     * Palauta karttatason geometriat.
     *
     * @return LayerData: karttatason geometriat.
     */
    public LayerData getLayerData() {
        return layerData;
    }

    /**
     * Aseta karttatason geometriat.
     *
     * @param layerData LayerData-olio.
     */
    public void setLayerData(LayerData layerData) {
        this.layerData = layerData;
    }

    /**
     * Anna piirtoväri.
     *
     * @return Color
     */
    public Color getPiirtovari() {
        return piirtovari;
    }

    /**
     * Aseta piirtoväri.
     *
     * @param piirtovari Väri, jolla karttatason geometriat piirretään.
     */
    public void setPiirtovari(Color piirtovari) {
        this.piirtovari = piirtovari;
    }

    /**
     * Anna ympäröivä suorakaide.
     *
     * Delegoidaan <code>layerData</code>-oliolle.
     *
     * @return Envelope, ympäröivä suorakaide.
     */
    public Envelope getEnvelope() {
        return layerData.getEnvelope();
    }

    /**
     * Piirrä taso Graphics2D-kontekstin avulla.
     *
     * @param g2 Graphics2D-konteksti.
     * @param envelope Piirrettävän alueen ympäröivä suorakaide.
     * @param affine Affiininen muunnosmatriisi karttatasolta piirtoikkunaan.
     */
    public void piirra(Graphics2D g2, Envelope envelope, AffineTransformation affine) {
        g2.setColor(piirtovari);
        for (Geometry geometry : layerData) {
            /**
             * Ei piirretä ulkopuolelle jääviä kuvioita.
             */
            if (!envelope.intersects(geometry.getEnvelopeInternal())) {
                continue;
            }

            if (geometry.getGeometryType().equals("LineString")) {
                piirraMurtoviiva(g2, affine, geometry);
            } else if (geometry.getGeometryType().equals("Polygon")) {
                piirraMonikulmio(g2, affine, geometry);
            } else if (geometry.getGeometryType().equals("Point")) {
                piirraPiste(g2, affine, geometry);
            }
        }
    }

    /**
     * Piirrä murtoviiva.
     *
     * @param g2
     * @param affine
     * @param geometry
     */
    private void piirraMurtoviiva(Graphics2D g2, AffineTransformation affine, Geometry geometry) {
        int[] x = new int[geometry.getNumPoints()];
        int[] y = new int[geometry.getNumPoints()];
        int i = 0;
        for (Coordinate c : affine.transform(geometry).getCoordinates()) {
            x[i] = (int)c.x;
            y[i] = (int)c.y;
            i++;
        }
        g2.drawPolyline(x, y, i);
    }

    /**
     * Piirrä piste ympyröinä.
     *
     * @param g2
     * @param affine
     * @param geometry
     */
    private void piirraPiste(Graphics2D g2, AffineTransformation affine, Geometry geometry) {
        for (Coordinate c : affine.transform(geometry).getCoordinates()) {
            int w = 5;

            g2.drawOval((int)c.x - w / 2, (int)c.y - w / 2, w, w);
        }
    }

    /**
     * Piirrä monikulmio.
     *
     * Tämä toteutus piirtää vain monikulmion ulkorenkaan. Reikien
     * piirtäminen toteuttamatta, samoin rajaviivojen.
     *
     * @param g2 Grafiikka-konteksti.
     * @param affine Affiininen muunnos geometrian koordinaatistosta ikkunan koordinaatistoon.
     * @param geometry Piirrettävä geometria.
     */
    private void piirraMonikulmio(Graphics2D g2, AffineTransformation affine, Geometry geometry) {
        Polygon polygon = (Polygon)geometry;
        LineString exteriorRing = polygon.getExteriorRing();

        int[] x = new int[exteriorRing.getNumPoints()];
        int[] y = new int[exteriorRing.getNumPoints()];
        int i = 0;
        for (Coordinate c : affine.transform(exteriorRing).getCoordinates()) {
            x[i] = (int)c.x;
            y[i] = (int)c.y;
            i++;
        }
        g2.fillPolygon(x, y, i);
//        piirraMurtoviiva(g2, affine, geometry);
    }
}
