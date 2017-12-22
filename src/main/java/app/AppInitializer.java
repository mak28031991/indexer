package app;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AppInitializer
 */
public class AppInitializer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AppInitializer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//here check for app properteis file 
		//check if all params are present 
		//if every thing works fine then send to db_config.jsp
		//if some thing is missing then also send to db_config.jsp
		
		
		//on submit update app.properties file with db credentails
		//and send the request to db initializer
		//if any thing goes wrong in getting db access throw error on db_config.jsp
		//if we get successfull db access then land on indexer.jsp
		//this jsp will fill a form about existing index and non index columns.		
		//on submit create a sql script and ask user to execute the script.
		
		String host = "";
		String port = "";
		String databaseName = "";
		String userName = "";
		String password = "";
		boolean propertiesFileFound = false;
		Properties prop = new Properties();
		InputStream input = null;
		OutputStream output = null;
		try {
			input = new FileInputStream("config.properties");
			propertiesFileFound = true;
		} catch(FileNotFoundException e)
		{
			
		}
		if(!propertiesFileFound)
		{
			System.out.println("file not found");			
		}
		else
		{
			System.out.println("file found");
			prop.load(input);
			if(prop.getProperty("host")!=null)
			{
				host = prop.getProperty("host");
			}
			if(prop.getProperty("port")!=null)
			{
				port = prop.getProperty("port");
			}
			if(prop.getProperty("database_name")!=null)
			{
				databaseName = prop.getProperty("database_name");
			}
			if(prop.getProperty("user_name")!=null)
			{
				userName = prop.getProperty("user_name");
			}
			if(prop.getProperty("password")!=null)
			{
				password = prop.getProperty("password");
			}
		}	
		
		String params= ""
				+ "host="+host+""
				+ "&port="+port+""
				+ "&database_name="+databaseName+""
				+ "&user_name="+userName
				+ "&password="+password;
		response.sendRedirect("/indexer/db_config.jsp?"+params);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
