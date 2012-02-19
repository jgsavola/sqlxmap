/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlxmap.ui;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Envelope;
import com.vividsolutions.jts.io.ParseException;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import sqlxmap.*;

/**
 * Graafinen käyttöliittymä sovellukselle.
 * 
 * Yksinkertainen Swing-käyttöliittymä, jossa on valikko, työkalupalkki,
 * karttaikkuna (<code>MapPanel</code>) ja statustekstikenttä.
 * 
 * @author jonne
 */
public class SQLxMapApp extends javax.swing.JFrame implements Observer {
    private Settings settings;
    private Varisarja varisarja;

    /**
     * Creates new form SQLxMapApp
     */
    public SQLxMapApp() {
        initComponents();

        mapPanel.requestFocusInWindow();
        varisarja = new SatunnainenVari(0.0);
        
        try {
            settings = new Settings();
        } catch (Exception ex) {
            Logger.getLogger(SQLxMapApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*
         * Testausta varten.
         */
        addTestLayerData();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        kyselyikkunaButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        tietokantakyselyLeikepoydaltaButton = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        poistaKarttatasotButton = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        naytaKaikkiButton = new javax.swing.JButton();
        statusTextField = new javax.swing.JTextField();
        mapPanel = new sqlxmap.ui.MapPanel();
        peittavyysSlider = new javax.swing.JSlider();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        saveAsMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        cutMenuItem = new javax.swing.JMenuItem();
        copyMenuItem = new javax.swing.JMenuItem();
        pasteMenuItem = new javax.swing.JMenuItem();
        deleteMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        contentsMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(600, 400));
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jToolBar1.setRollover(true);

        kyselyikkunaButton.setText("Tietokantakysely");
        kyselyikkunaButton.setFocusable(false);
        kyselyikkunaButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        kyselyikkunaButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        kyselyikkunaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kyselyikkunaButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(kyselyikkunaButton);
        jToolBar1.add(jSeparator1);

        tietokantakyselyLeikepoydaltaButton.setText("Tietokantakysely leikepöydältä");
        tietokantakyselyLeikepoydaltaButton.setFocusable(false);
        tietokantakyselyLeikepoydaltaButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tietokantakyselyLeikepoydaltaButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tietokantakyselyLeikepoydaltaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tietokantakyselyLeikepoydaltaButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(tietokantakyselyLeikepoydaltaButton);
        jToolBar1.add(jSeparator2);

        poistaKarttatasotButton.setText("Poista karttatasot");
        poistaKarttatasotButton.setFocusable(false);
        poistaKarttatasotButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        poistaKarttatasotButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        poistaKarttatasotButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                poistaKarttatasotButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(poistaKarttatasotButton);
        jToolBar1.add(jSeparator3);

        naytaKaikkiButton.setText("Näytä kaikki");
        naytaKaikkiButton.setFocusable(false);
        naytaKaikkiButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        naytaKaikkiButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        naytaKaikkiButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                naytaKaikkiButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(naytaKaikkiButton);

        statusTextField.setEditable(false);
        statusTextField.setText("status");

        mapPanel.setBackground(new java.awt.Color(0, 0, 0));
        mapPanel.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                mapPanelMouseWheelMoved(evt);
            }
        });
        mapPanel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                mapPanelMouseMoved(evt);
            }
        });
        mapPanel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                mapPanelKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout mapPanelLayout = new javax.swing.GroupLayout(mapPanel);
        mapPanel.setLayout(mapPanelLayout);
        mapPanelLayout.setHorizontalGroup(
            mapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        mapPanelLayout.setVerticalGroup(
            mapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        peittavyysSlider.setToolTipText("Värien peittävyys");
        peittavyysSlider.setValue(100);
        peittavyysSlider.setFocusable(false);
        peittavyysSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                peittavyysSliderStateChanged(evt);
            }
        });

        fileMenu.setMnemonic('f');
        fileMenu.setText("File");

        openMenuItem.setMnemonic('o');
        openMenuItem.setText("Open");
        fileMenu.add(openMenuItem);

        saveMenuItem.setMnemonic('s');
        saveMenuItem.setText("Save");
        fileMenu.add(saveMenuItem);

        saveAsMenuItem.setMnemonic('a');
        saveAsMenuItem.setText("Save As ...");
        saveAsMenuItem.setDisplayedMnemonicIndex(5);
        fileMenu.add(saveAsMenuItem);

        exitMenuItem.setMnemonic('x');
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        editMenu.setMnemonic('e');
        editMenu.setText("Edit");

        cutMenuItem.setMnemonic('t');
        cutMenuItem.setText("Cut");
        editMenu.add(cutMenuItem);

        copyMenuItem.setMnemonic('y');
        copyMenuItem.setText("Copy");
        editMenu.add(copyMenuItem);

        pasteMenuItem.setMnemonic('p');
        pasteMenuItem.setText("Paste");
        editMenu.add(pasteMenuItem);

        deleteMenuItem.setMnemonic('d');
        deleteMenuItem.setText("Delete");
        editMenu.add(deleteMenuItem);

        menuBar.add(editMenu);

        helpMenu.setMnemonic('h');
        helpMenu.setText("Help");

        contentsMenuItem.setMnemonic('c');
        contentsMenuItem.setText("Contents");
        helpMenu.add(contentsMenuItem);

        aboutMenuItem.setMnemonic('a');
        aboutMenuItem.setText("About");
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(mapPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(statusTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(peittavyysSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mapPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(statusTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(peittavyysSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void mapPanelMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mapPanelMouseMoved
        DecimalFormat f = new DecimalFormat("##.00");
        Coordinate c = mapPanel.transformCoordinatesWindowToWorld(evt.getX(), evt.getY());
        Coordinate wc = mapPanel.transformCoordinatesWorldToWindow(c);

        statusTextField.setText(evt.getX() + " " + evt.getY()
                + " (" + f.format(c.x) + ", " + f.format(c.y) + ")"
                + " (" + f.format(wc.x) + "," + f.format(wc.y) + ")");
    }//GEN-LAST:event_mapPanelMouseMoved

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        System.out.println("formKeyPressed: '" + evt.getKeyChar() + "'");
    }//GEN-LAST:event_formKeyPressed

    private void mapPanelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mapPanelKeyPressed
        Logger.getLogger(SQLxMapApp.class.getName()).log(Level.FINE, "mapPanelKeyPressed: ''{0}'' ({1})", 
                new Object[]{evt.getKeyChar(), evt.getKeyCode()});
        
        Envelope envelope = new Envelope(mapPanel.getEnvelope());
        
        switch(evt.getKeyCode()) {
            case 65:
            case 45:
                envelope.expandBy(20000);
                break;
            case 521:
            case 83:
                envelope.expandBy(-20000);
                break;
            case 75:
            case 38:
                envelope.translate(0, 20000);
                break;
            case 74:
            case 40:
                envelope.translate(0, -20000);
                break;
            case 72:
            case 37:
                envelope.translate(-20000, 0);
                break;
            case 76:
            case 39:
                envelope.translate(20000, 0);
                break;
            case 82:
                mapPanel.naytaKokoMaailma();
                envelope = mapPanel.getEnvelope();
                break;
        }

        if (!envelope.equals(mapPanel.getEnvelope())) {
            mapPanel.setEnvelope(envelope);
        }
        mapPanel.korjaaKuvasuhde();
        mapPanel.repaint();
        
    }//GEN-LAST:event_mapPanelKeyPressed

    private void kyselyikkunaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kyselyikkunaButtonActionPerformed
        System.out.println("Kysely");
        SQLTarkkailtava tarkkailtava = new SQLTarkkailtava();
        tarkkailtava.addObserver(this);
        Kyselyikkuna kyselyikkuna = new Kyselyikkuna(tarkkailtava);
        kyselyikkuna.setVisible(true);
        kyselyikkuna.setSQLText("--\n"
+ "-- Tee verkosto maantieteellissä koordinaateissa.\n"
+ "--\n"
+ "SELECT ST_Transform(ST_SetSRID(ST_MakePoint(x.x, y.y), 4326), 3067)\n"
+ "       FROM (SELECT generate_series(10, 40, 1) x) x\n"
+ "       JOIN (SELECT generate_series(50, 80, 1) y) y\n"
+ "       ON true;\n"
+ "\n"
+ "-- Maakuntia\n"
+ "SELECT the_geom FROM miljoona.maaku1_p;\n"
+ "\n"
+ "-- Rannikkoviivaa\n"
+ "SELECT the_geom FROM miljoona.coast_l;\n"
+ "\n"
+ "-- Rautateitä\n"
+ "SELECT the_geom FROM miljoona.railway;\n"
+ "\n"
+ "-- Kaupunkeja, joissa yli 3000 asukasta\n"
+ "SELECT the_geom FROM miljoona.cityp WHERE asulkm1999 >= 3000;\n"
+ "\n"
+ "-- Seutukuntia Ahvenanmaan suunnalla\n"
+ "SELECT the_geom FROM miljoona.kunta1_p WHERE seutukunta::int BETWEEN 200 AND 300;\n"
+ "\n"
+ "-- Helsinki\n"
+ "SELECT the_geom FROM miljoona.kunta1_p WHERE kunta_ni1 = 'Helsinki';\n"
+ "\n"
+ "--\n"
+ "-- Hae Helsinki ja rekursiivisesti kaikki kunnat, jotka 'koskettavat'\n"
+ "-- Helsinkiä, ts. niiden leikkaus on piste tai viiva.\n"
+ "--\n"
+ "-- Rekursion päättymisehto on tässä: t.n < 5\n"
+ "--\n"
+ "-- Varoitus! Suoritusaika kasvaa eksponentiaalisesti, 't.n < 7':kin\n"
+ "-- alkoi jo epäilyttää.\n"
+ "--\n"
+ "WITH RECURSIVE t(the_geom, n) AS (\n"
+ "     SELECT the_geom, 1 FROM miljoona.kunta1_p WHERE kunta_ni1 = 'Helsinki'\n"
+ "   UNION ALL\n"
+ "     SELECT kunta1_p.the_geom, t.n + 1 \n"
+ "     FROM miljoona.kunta1_p, t\n"
+ "         WHERE ST_Touches(t.the_geom, kunta1_p.the_geom) AND t.n < 5\n"
+ ")\n"
+ "SELECT ST_Union(the_geom) FROM t\n"
+ "GROUP BY t.n\n"
+ "ORDER BY t.n DESC;\n"
+ "\n"
);
    }//GEN-LAST:event_kyselyikkunaButtonActionPerformed

    private void poistaKarttatasotButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_poistaKarttatasotButtonActionPerformed
        mapPanel.poistaKarttatasot();
    }//GEN-LAST:event_poistaKarttatasotButtonActionPerformed

    private void tietokantakyselyLeikepoydaltaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tietokantakyselyLeikepoydaltaButtonActionPerformed
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemSelection();
        Transferable ct = clipboard.getContents(null);
        try {
            Object transferData = ct.getTransferData(DataFlavor.stringFlavor);
            String kysely = transferData.toString();
            System.out.println("Transferdata: " + kysely);
            suoritaKysely(kysely);
        } catch (IOException ex) {
            Logger.getLogger(Kyselyikkuna.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedFlavorException ex) {
            Logger.getLogger(Kyselyikkuna.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tietokantakyselyLeikepoydaltaButtonActionPerformed

    private void naytaKaikkiButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_naytaKaikkiButtonActionPerformed
        mapPanel.naytaKokoMaailma();
    }//GEN-LAST:event_naytaKaikkiButtonActionPerformed

    private void mapPanelMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_mapPanelMouseWheelMoved
        Envelope envelope = mapPanel.getEnvelope();

        int x = evt.getX();
        int y = evt.getY();

        /**
         * Ota talteen hiiren osoittimen sijainti vanhassa koordinaatistossa.
         */
        Coordinate vanhaPiste = mapPanel.transformCoordinatesWindowToWorld(x, y);

        /**
         * Zoomaa.
         */
        envelope.expandBy(evt.getWheelRotation()*20000);

        /**
         * Hiiren osoittimen sijainti uudessa koordinaatistossa.
         */
        Coordinate uusiPiste = mapPanel.transformCoordinatesWindowToWorld(x, y);

        /**
         * Ainakin lähennettäessä tämä vaikuttaa oikealta: koordinaatistoa
         * siirretään niin, että hiiren osoitin säilyy samassa maantieteellisessä
         * sijainnissa.
         *
         * FIXME: loitonnettaessa pitäisi ehkä käyttää jotain tapaa.
         */
        envelope.translate(vanhaPiste.x - uusiPiste.x,
                           vanhaPiste.y - uusiPiste.y);

        mapPanel.setEnvelope(envelope);
        mapPanel.korjaaKuvasuhde();
        mapPanel.repaint();
    }//GEN-LAST:event_mapPanelMouseWheelMoved

    private void peittavyysSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_peittavyysSliderStateChanged
        for (Karttataso karttataso : mapPanel.getKarttatasot()) {
            karttataso.getPiirtotyyli().asetaPeittavyysVareille(peittavyysSlider.getValue() / 100.0);
        }

        mapPanel.repaint();
    }//GEN-LAST:event_peittavyysSliderStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SQLxMapApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SQLxMapApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SQLxMapApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SQLxMapApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new SQLxMapApp().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenuItem contentsMenuItem;
    private javax.swing.JMenuItem copyMenuItem;
    private javax.swing.JMenuItem cutMenuItem;
    private javax.swing.JMenuItem deleteMenuItem;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton kyselyikkunaButton;
    private sqlxmap.ui.MapPanel mapPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JButton naytaKaikkiButton;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JMenuItem pasteMenuItem;
    private javax.swing.JSlider peittavyysSlider;
    private javax.swing.JButton poistaKarttatasotButton;
    private javax.swing.JMenuItem saveAsMenuItem;
    private javax.swing.JMenuItem saveMenuItem;
    private javax.swing.JTextField statusTextField;
    private javax.swing.JButton tietokantakyselyLeikepoydaltaButton;
    // End of variables declaration//GEN-END:variables

    /**
     * Luo muutama karttakohde karttatasojen piirtämisen testaamista varten.
     */
    private void addTestLayerData() {
        Tietokantayhteys yhteys = new Tietokantayhteys(settings.getDbInfo());
        try {
            String[] testikyselyt = {
                "SELECT the_geom FROM miljoona.maaku1_p",
                "SELECT the_geom FROM miljoona.coast_l",
                "SELECT the_geom FROM miljoona.railway",
                "SELECT the_geom FROM miljoona.cityp WHERE asulkm1999 >= 3000"
            };
            
            yhteys.yhdista();

            for (String SQL : testikyselyt) {
                ArrayList<LayerData> layerDataList = yhteys.teeKysely(SQL);
                for (LayerData ld : layerDataList) {
                    Karttataso karttataso = new Karttataso();
                    karttataso.setLayerData(ld);
                    karttataso.setPiirtotyyli(new Piirtotyyli(varisarja.seuraavaVari(), varisarja.seuraavaVari(), 1));
                    mapPanel.lisaaKarttataso(karttataso);
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SQLxMapApp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SQLxMapApp.class.getName()).log(Level.SEVERE, null, ex);
        }

        LayerData ld = new LayerData();
        Karttataso karttataso = new Karttataso();
        try {
            ld.addWKTGeometry("POINT(500000 7000000)");
            karttataso.setLayerData(ld);
            karttataso.setPiirtotyyli(new Piirtotyyli(varisarja.seuraavaVari(), varisarja.seuraavaVari(), 1));
        } catch (ParseException ex) {
            Logger.getLogger(SQLxMapApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        mapPanel.lisaaKarttataso(karttataso);
    }

    @Override
    public void update(Observable o, Object o1) {
        System.out.println("o1 " + o1);
        
        /**
         * Jossain halutaan tehdä jotakin SQL-lauseelle :)
         */
        if (o instanceof SQLTarkkailtava) {
            String kysely = (String)o1;

            suoritaKysely(kysely);
        }
    }

    private void suoritaKysely(String kysely) {
        Tietokantayhteys yhteys = new Tietokantayhteys(settings.getDbInfo());
        try {
            yhteys.yhdista();

            ArrayList<LayerData> layerDataList = yhteys.teeKysely(kysely);
            for (LayerData ld : layerDataList) {
                Karttataso karttataso = new Karttataso();
                karttataso.setLayerData(ld);
                karttataso.setPiirtotyyli(new Piirtotyyli(varisarja.seuraavaVari(), varisarja.seuraavaVari(), 1));
                mapPanel.lisaaKarttataso(karttataso);
                mapPanel.repaint();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SQLxMapApp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SQLxMapApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
