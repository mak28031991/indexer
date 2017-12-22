/**
 * 
 */
package indexer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author mayank
 *
 */
public class DBUTILS {

	private static DBUTILS dbUtil;
	Properties prop = new Properties();
	InputStream input = null;	
	private  DBUTILS() {
		
	}

	public static DBUTILS getInstance(){
        if(dbUtil == null){
        	dbUtil = new DBUTILS();
        }
        return dbUtil;
    }

	public Connection getDBConnection() throws IOException, ClassNotFoundException, SQLException
	{		
		
		 String host = null;
		 String port = null;
		 String databaseName = null;
		 String userName = null;
		 String password = null;
		 
		 input = new FileInputStream("config.properties");
		 prop.load(input);
		 host = prop.getProperty("host");
		 port = prop.getProperty("port");
		 databaseName = prop.getProperty("database_name");
		 userName = prop.getProperty("user_name");
		 password = prop.getProperty("password");
		 
		 Class.forName("org.postgresql.Driver");
		 Connection connection = null;
		 connection = DriverManager.getConnection("jdbc:postgresql://"+host+":"+port+"/"+databaseName+"",""+userName+"", ""+password+"");
		 return connection;
	}
}
