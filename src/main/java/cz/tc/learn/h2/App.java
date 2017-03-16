package cz.tc.learn.h2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author tomas.cejka
 * 
 * @see <a href="http://www.claudiobernasconi.ch/2010/08/17/h2-embedded-java-db-getting-started/">
 * H2: Embedded Java DB: Getting Started</a>
 */
public class App {

    public static void main(String[] args) {
        try {
            Class.forName("org.h2.Driver");
            try (Connection con = DriverManager.getConnection("jdbc:h2:~/test", "test", ""); Statement stmt = con.createStatement()) {
                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS table1 ( user varchar(50) )");
                stmt.executeUpdate("INSERT INTO table1 ( user ) VALUES ( 'Claudio' )");
                stmt.executeUpdate("INSERT INTO table1 ( user ) VALUES ( 'Bernasconi' )");
                
                ResultSet rs = stmt.executeQuery("SELECT * FROM table1");
                while (rs.next()) {
                    String name = rs.getString("user");
                    System.out.println(name);
                }
                stmt.executeUpdate( "DROP TABLE table1" );
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
