package easv.dk.DAL;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;



public class ConnectionManager {
    private final SQLServerDataSource ds;

    public ConnectionManager() throws IOException
    {
        ds = new SQLServerDataSource();
        ds.setServerName("10.176.111.31");
        ds.setDatabaseName("movieCollDB");
        ds.setPortNumber(1433);
        ds.setUser("CSe21B_34");
        ds.setPassword("CSe21B_34");
    }

    public Connection getConnection() throws SQLServerException
    {
        return ds.getConnection();
    }
    public static void main (String[] args) throws SQLException, IOException {
        ConnectionManager ds = new ConnectionManager();
            try (Connection connection= ds.getConnection()){
                System.out.println("Is it open"+!connection.isClosed());}
    }
}