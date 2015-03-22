/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlxmap.ui;

/**
 *
 * @author jgsavola
 */
public class Ohjeita extends javax.swing.JDialog {

    /**
     * Creates new form Ohjeita
     */
    public Ohjeita(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        okButton.requestFocusInWindow();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        okButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(600, 500));

        jTextPane1.setEditable(false);
        jTextPane1.setContentType("text/html"); // NOI18N
        jTextPane1.setText("<html>\n  <head>\n\n  </head>\n  <body>\n    <pre>\nToiminnot\n---------\n\n* Karttanäkymän vierittäminen:\n\n  Nuolinäppäimet (tai \"H\", \"J\", \"K\", \"L\")\n\n* Karttanäkymän lähentäminen:\n\n  Plus (tai hiiren rullapainike)\n\n* Karttanäkymän loitontaminen:\n\n  Miinus (tai hiiren rullapainike)\n\n* Karttatason läpinäkyvyyden muuttaminen:\n\n  Valitse taso tasovalikosta ja muuta läpinäkyvyyttä\n  liukukytkimestä. Erityisesti kysely \"Hae Helsinki ja\n  rekursiivisesti kaikki kunnat, jotka ...\" on mielenkiintoinen \n  (ja paljastaa pienen kombinatoriikkaan liittyvän ongelman\n  rekursiivisen kyselyn muotoilussa...)\n\n* SQL-kyselyn suorittaminen Kyselyikkunassa:\n\n  1. Avaa Kyselyikkuna Tietokantakysely-painikkeesta.\n  2. Muokkaa kyselyjä ja paina \"Suorita\"-painikketta; tai\n  3. Valitse kyselyjä Kyselyikkunassa tai missä tahansa muussa\n     ikkunassa (selain, tekstieditori, terminaali, ...) ja paina\n     \"Suorita valinta\".\n\n* SQL-kyselyn suorittaminen leikepöydältä:\n\n  1. Valitse kyselyjä missä tahansa ikkunassa (selain, tekstieditori,\n     terminaali, ...)\n  2. Paina \"Tietokantakysely leikepöydältä\" -nappia tai välilyöntiä (SPACE).\n\n* Kaikkien karttatasojen poistaminen:\n\n  Paina \"Poista karttatasot\".\n\n* Näytä kaikki karttatasot:\n\n  Paina \"Näytä kaikki\" (tai \"R\")\n\n* Näytä käyttöohjeet:\n\n  Help ==> Contents\n\n* Näytä tietoja ohjelmasta:\n\n  Help ==> About\n\n* Lopeta ohjelma:\n\n  File ==> Exit\n\n    </pre>\n  </body>\n</html>\n");
        jScrollPane1.setViewportView(jTextPane1);

        okButton.setText("Sulje");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(153, 153, 153)
                        .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 158, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(okButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        dispose();
    }//GEN-LAST:event_okButtonActionPerformed

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
            java.util.logging.Logger.getLogger(Ohjeita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ohjeita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ohjeita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ohjeita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the dialog
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                Ohjeita dialog = new Ohjeita(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JButton okButton;
    // End of variables declaration//GEN-END:variables
}
