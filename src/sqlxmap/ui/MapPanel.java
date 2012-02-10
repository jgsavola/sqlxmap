/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlxmap.ui;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Envelope;
import com.vividsolutions.jts.geom.Geometry;
import java.awt.*;
import java.awt.event.MouseEvent;
import com.vividsolutions.jts.geom.util.AffineTransformation;
import java.util.ArrayList;
import sqlxmap.LayerData;

/**
 * Karttaikkuna karttatasojen näyttämiseen.
 * 
 * <code>MapPanel</code> piirtää siihen liitettyjen
 * <code>LayerData</code>-olioiden geometriat karttaikkunaan.
 * 
 * Karttaikkuna ylläpitää karttanäkymän sijantitietoa. Tässä versiossa
 * ikkuna kattaa karttatasojen ympäröivän suorakaiteen, eli karttakohteet
 * on skaalattu niin, että kaikki kohteet näkyvät aina ikkunassa mittakaavasta
 * riippumatta.
 *
 * @author jonne
 */
public class MapPanel extends javax.swing.JPanel {
    private ArrayList<LayerData> layerDataList;
    private Envelope envelope;
    /**
     * Tämä määrittelee koordinaatistomuunnoksen karttakoordinaateista
     * piirtoikkunan pikselikoordinaatteihin.
     */
    private AffineTransformation affine;
    private boolean shownAlready = false;

    /**
     * Creates new form MapPanel
     */
    public MapPanel() {
        initComponents();

        layerDataList = new ArrayList<LayerData>();
        envelope = new Envelope();
        affine = new AffineTransformation();
        resetAffineTransformation();
    }

    /**
     * Lisää karttataso piirrettävien karttatasojen joukkoon.
     *
     * @param layerData Lisättävä karttataso.
     */
    public void addLayerData(LayerData layerData) {
        layerDataList.add(layerData);
        envelope.expandToInclude(layerData.getEnvelope());
        resetAffineTransformation();
        /**
         * TODO: Piirrä karttaikkuna uudestaan.
         */
    }

    public Envelope getEnvelope() {
        return this.envelope;
    }
    
    public void setEnvelope(Envelope envelope) {
        this.envelope = envelope;
        resetAffineTransformation();
    }
    
    public Envelope envelopeExpandToInclude(Envelope envelope) {
        this.envelope.expandToInclude(envelope);
        resetAffineTransformation();

        return this.envelope;
    }
    
    /**
     * Muodosta uudelleen affinimuunnos, jolla geometrioiden koordinaatit
     * muunnetaan maailman koordinaateista ikkunakoordinatteihin.
     * 
     * Käytetään ikkunan kokona mapPanel-ikkunan kokoa.
     */
    private void resetAffineTransformation() {
        AffineTransformation scale = new AffineTransformation();
        scale.setToScale(1.0 / (envelope.getWidth() / this.getWidth()),
                         -1.0 / (envelope.getHeight() / this.getHeight()));
        affine.setToTranslation(-envelope.getMinX(), -envelope.getMaxY());
        affine.compose(scale);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        long startNano = java.lang.System.nanoTime();

        Color[] colors = {
                            new Color(255, 50, 50, 200),
                            new Color(50, 255, 50, 200),
                            new Color(255, 50, 255, 200),
                            new Color(255, 255, 255, 200)
        };
        Graphics2D g2 = (Graphics2D)g;
        g2.setStroke(new BasicStroke(2));

        int n = 0;
        for (LayerData layerData : layerDataList) {
            Color color = colors[n % colors.length];
            g2.setColor(color);

            for (Geometry geometry : layerData) {
                /**
                 * Ei piirretä ulkopuolelle jääviä kuvioita.
                 */
                if (!envelope.intersects(geometry.getEnvelopeInternal())) {
                    continue;
                }

                if (geometry.getGeometryType().equals("LineString") 
                        || geometry.getGeometryType().equals("Polygon")) {
                    int[] x = new int[geometry.getNumPoints()];
                    int[] y = new int[geometry.getNumPoints()];
                    int i = 0;
                    

                    /**
                     * Testaa koordinaatistomuunnosta kahdella tavalla:
                     * 
                     * 1. transformCoordinatesWorldToWindow-metodi, erikseen
                     *    jokaiselle koordinaattipisteelle.
                     * 2. JTS:n affiininen muunnos, geometria-kohde kerrallaan.
                     * 
                     * Valitettavasti näyttää siltä, että JTS:n muunnos on 
                     * hitaampi, vaikka sillä olisi teoriassa enemmän 
                     * mahdollisuuksia optimointiin.
                     */
                    if (false) {
                        for (Coordinate c : geometry.getCoordinates()) {
                            Coordinate wc = transformCoordinatesWorldToWindow(c);
                            x[i] = (int)wc.x;
                            y[i] = (int)wc.y;
                            i++;
                        }
                    } else {
                        Geometry geom2 = affine.transform(geometry);
                        for (Coordinate c : geom2.getCoordinates()) {
                            x[i] = (int)c.x;
                            y[i] = (int)c.y;
                            i++;
                        }
                    }
                    g2.drawPolyline(x, y, i);
                } else {
                    for (Coordinate c : geometry.getCoordinates()) {
                        int w = 5;

                        Coordinate wc = transformCoordinatesWorldToWindow(c);
                        g2.drawOval((int)wc.x - w / 2, (int)wc.y - w / 2, w, w);
                    }
                }
            }
            n++;
        }
        long stopNano = java.lang.System.nanoTime();

        g2.drawString("repaint: " + (stopNano - startNano) / 1.e6 + "ms", 0, 20);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        resetAffineTransformation();
        this.repaint();
    }//GEN-LAST:event_formComponentResized

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    Coordinate transformCoordinatesWindowToWorld(double x, double y) {
        Coordinate c = new Coordinate();

        c.x = x / (double)this.getWidth() * envelope.getWidth() + envelope.getMinX();
        c.y = (double)(this.getHeight() - y - 1) / (double)this.getHeight() * envelope.getHeight() + envelope.getMinY();

        return c;
    }

    Coordinate transformCoordinatesWindowToWorld(int x, int y) {
        return transformCoordinatesWindowToWorld((double)x, (double)y);
    }

    Coordinate transformCoordinatesWindowToWorld(Coordinate c) {
        return transformCoordinatesWindowToWorld(c.x, c.y);
    }

    Coordinate transformCoordinatesWorldToWindow(Coordinate c) {
        Coordinate wc = new Coordinate();

        wc.x = (c.x - envelope.getMinX()) / envelope.getWidth() * (double)this.getWidth();
        wc.y = (envelope.getMaxY() - c.y) / envelope.getHeight() * (double)this.getHeight() - 1.0;

        return wc;
    }

    Iterable<LayerData> getLayerDataList() {
        return layerDataList;
    }
}
