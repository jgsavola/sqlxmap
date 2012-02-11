/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlxmap;

import java.util.Observable;

/**
 *
 * @author jonne
 */
public class SQLTarkkailtava extends Observable {
    private String SQL;

    public SQLTarkkailtava() {
    }

    public String getSQL() {
        return SQL;
    }

    public void setSQL(String SQL) {
        this.SQL = SQL;
        setChanged();
        notifyObservers(SQL);
    }
}
