/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlxmap;

import com.vividsolutions.jts.geom.Envelope;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKBReader;
import com.vividsolutions.jts.io.WKTReader;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author jonne
 */
public class LayerData implements Iterable<Geometry> {
    private ArrayList<Geometry> geometries;
    private Envelope layerEnvelope;
    private GeometryFactory geometryFactory;
    private WKTReader wktReader;
    private WKBReader wkbReader;
    
    public LayerData() {
        geometries = new ArrayList<Geometry>();
        geometryFactory = new GeometryFactory();
        wktReader = new WKTReader(geometryFactory);
        wkbReader = new WKBReader(geometryFactory);
    }
    
    public void addGeometry(Geometry geom) {
        geometries.add(geom);
        extendEnvelope(geom);
    }
    
    public void addWKTGeometry(String wkt) throws ParseException {
        Geometry geom = wktReader.read(wkt);
        if (geom != null) {
            addGeometry(geom);
        }
    }
    
    public void addWKBGeometry(String hexWKB) throws ParseException {
        addWKBGeometry(WKBReader.hexToBytes(hexWKB));
    }
    
    public void addWKBGeometry(byte[] wkb) throws ParseException {
        Geometry geom = wkbReader.read(wkb);
        if (geom != null) {
            addGeometry(geom);
        }
    }

    public void extendEnvelope(Geometry geom) {
        if (layerEnvelope == null) {
            layerEnvelope = new Envelope(geom.getEnvelopeInternal());
        } else {
            layerEnvelope.expandToInclude(geom.getEnvelopeInternal());
        }
    }

    public Envelope getEnvelope() {
        return layerEnvelope;
    }

    @Override
    public Iterator<Geometry> iterator() {
        return geometries.iterator();
    }
}
