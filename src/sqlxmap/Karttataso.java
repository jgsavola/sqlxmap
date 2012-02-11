/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlxmap;

import java.awt.Color;

/**
 * Karttatasossa säilytetään karttatason tietoja, kuten LayerData-oliota, 
 * karttatason nimeä, SQL-lausetta, näyttötyylejä, yms.
 * 
 * @author jonne
 */
public class Karttataso {
    private LayerData layerData;
    private Color piirtovari;

    public Karttataso() {
    }

    public LayerData getLayerData() {
        return layerData;
    }

    public void setLayerData(LayerData layerData) {
        this.layerData = layerData;
    }

    public Color getPiirtovari() {
        return piirtovari;
    }

    public void setPiirtovari(Color piirtovari) {
        this.piirtovari = piirtovari;
    }
    
    
}
