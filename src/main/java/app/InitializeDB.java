package app;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import indexer.DBUTILS;

/**
 * Servlet implementation class InitializeDB
 */
public class InitializeDB extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InitializeDB() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @throws IOException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String host = null;
		String port = null;
		String databaseName = null; 
		String userName = null;
		String password = null;
		String error = null;
		if(request.getParameterMap().containsKey("host"))
		{
			host = request.getParameter("host");
		}
		else
		{
			error = "Host value is mandatory";
		}	
		if(request.getParameterMap().containsKey("port"))
		{
			port = request.getParameter("port");
		}
		else
		{
			error += ", Port value is mandatory";
		}
		if(request.getParameterMap().containsKey("database_name"))
		{
			databaseName = request.getParameter("database_name");
		}
		else
		{
			error += ", databaseName value is mandatory";
		}
		if(request.getParameterMap().containsKey("user_name"))
		{
			userName = request.getParameter("user_name");
		}
		else
		{
			error += ", userName value is mandatory";
		}
		if(request.getParameterMap().containsKey("password"))
		{
			password = request.getParameter("password");
		}
		else
		{
			error += ", password value is mandatory";
		}
		if(host==null || host.length()==0 || port==null || port.length()==0 || databaseName==null || databaseName.length()==0 || userName==null || userName.length()==0 || password==null ||  password.length()==0)
		{
			error = "Every field is mandatory";
		}
		if(error==null)
		{
			Properties prop = new Properties();
			OutputStream output = null;
			
			try {
				output = new FileOutputStream("config.properties");
				prop.setProperty("host", host);
				prop.setProperty("port", port);
				prop.setProperty("database_name", databaseName);
				prop.setProperty("user_name", userName);
				prop.setProperty("password", password);
				prop.store(output, null);
				
			} catch (Exception e) {
				error ="Some thing went wrong in saving properties ! "+e.getMessage();				
			}finally {
				if (output != null) {
					try {
						output.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}
			
			if(error!=null)
			{
				String params= ""
						+ "host="+host+""
						+ "&port="+port+""
						+ "&database_name="+databaseName+""
						+ "&user_name="+userName
						+ "&password="+password;				
				response.sendRedirect("/indexer/db_config.jsp?"+params+"&error="+error);
			}
			else
			{				
				response.sendRedirect("get_schema_details");
			}	
			
		}
		else
		{
			String params= ""
					+ "host="+host+""
					+ "&port="+port+""
					+ "&database_name="+databaseName+""
					+ "&user_name="+userName
					+ "&password="+password;
			
			response.sendRedirect("/indexer/db_config.jsp?"+params+"&error="+error);
			
		}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
