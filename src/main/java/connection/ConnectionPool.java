package connection;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 * Managing database connections using a connection pool.
 *
 * @author Facundo
 */
public class ConnectionPool {

    /**
     * Instance of the connection pool. Uses {@link BasicDataSource} to manage connections.
     */
    private static BasicDataSource dataSource = null;

    /**
     * Retrieves the {@link DataSource} instance. If the instance has not been created yet, it initializes it with the configuration parameters for the database connection.
     * <p>
     * The configuration includes the JDBC driver, database URL, username, password, and connection pool settings.
     * </p>
     *
     * @return A {@link DataSource} instance configured for database connections.
     */
    private static DataSource getDataSource() {
        if (dataSource == null) {
            dataSource = new BasicDataSource();
            dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");

            dataSource.setUsername("root");
            dataSource.setPassword(System.getenv("DB_MY_PASSWORD"));
            dataSource.setUrl(System.getenv("BOOK_INVENTORY_DB_URL"));
            
            dataSource.setInitialSize(20);
            dataSource.setMaxIdle(15);
            dataSource.setMaxTotal(20);
            dataSource.setMaxWaitMillis(5000);
        }

        return dataSource;
    }

    /**
     * Obtains a connection to the database using the {@link DataSource} connection pool.
     * <p>
     * If an error occurs while obtaining the connection, an error message is logged to the system log.
     * </p>
     *
     * @return A {@link ConnectionPool} object for interacting with the database, or {@code null} if the connection could not be obtained.
     */
    public static java.sql.Connection getConnection() {
        try {
            return getDataSource().getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

}
