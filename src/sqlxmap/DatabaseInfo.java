/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlxmap;

/**
 *
 * @author jonne
 */
class DatabaseInfo {
    private String dbHost;
    private int dbPort;
    private String dbName;
    private String dbUser;
    private String dbPassword;

    public DatabaseInfo() {
        dbHost = "localhost";
        dbPort = 5432;
        dbName = System.getProperty("user.name");
        dbUser = dbName;
        dbPassword = "";
    }

    public DatabaseInfo(String dbHost, int dbPort, String dbName, String dbUser, String dbPassword) {
        this.dbHost = dbHost;
        this.dbPort = dbPort;
        this.dbName = dbName;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    public String getDbHost() {
        return dbHost;
    }

    @Override
    public String toString() {
        return "DatabaseInfo{" + "dbHost=" + dbHost + ", dbPort=" + dbPort + ", dbName=" + dbName + ", dbUser=" + dbUser + ", dbPassword=" + dbPassword + '}';
    }

    public void setDbHost(String dbHost) {
        this.dbHost = dbHost;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    public int getDbPort() {
        return dbPort;
    }

    public void setDbPort(int dbPort) {
        this.dbPort = dbPort;
    }

    public String getDbUser() {
        return dbUser;
    }

    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }
    
    
}
