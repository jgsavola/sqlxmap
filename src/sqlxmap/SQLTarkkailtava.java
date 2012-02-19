/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlxmap;

import java.util.Observable;

/**
 * Tämän luokan avulla Observer-mallia käyttävät tarkkailijat saavat tiedon
 * SQL-merkkijonon päivittymisestä.
 *
 * Luokkaa käytetään mm. SQL-kyselyikkunan ja karttanäytön tiedonsiirtoon.
 *
 * @author jonne
 */
public class SQLTarkkailtava extends Observable {
    private String SQL;

    /**
     * Luo uusi <code>SQLTarkkailtava</code.
     */
    public SQLTarkkailtava() {
    }

    /**
     * Anna SQL-merkkijono.
     *
     * @return SQL-merkkijono.
     */
    public String getSQL() {
        return SQL;
    }

    /**
     * Aseta SQL-merkkijono.
     *
     * Tämä metodi kutsuu mahdollisia tarkkailijoita SQL-merkkijonon
     * asettamisen jälkeen.
     *
     * @param SQL SQL-merkkijono.
     */
    public void setSQL(String SQL) {
        this.SQL = SQL;
        setChanged();
        notifyObservers(SQL);
    }
}
