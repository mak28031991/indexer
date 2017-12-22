package app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import indexer.DBUTILS;

/**
 * Servlet implementation class GetSchemaDetails
 */
public class GetSchemaDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetSchemaDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("EVERYTHING NIS FINE");
		DBUTILS util = DBUTILS.getInstance();	
		Connection con = null;
		String error = null;
		try {
			con = util.getDBConnection();
		} catch (ClassNotFoundException e) {
			error ="PostgreSQL JDBC Driver is not in your library path ! "+e.getMessage();
		} catch (IOException e) {
			error ="Error in reading properties file ! "+e.getMessage();
		} catch (SQLException e) {
			error ="Connection Failed! "+e.getMessage();
		}
		JSONObject obj = new JSONObject();
		if(error==null)
		{
			
			String query ="select T1.*, COALESCE(T2.index_name) as index_name from (SELECT DISTINCT table_name, column_name,data_type FROM information_schema.columns WHERE table_schema not in ('pg_catalog','information_schema') )T1 left join ( select     t.relname as table_name,     i.relname as index_name,     a.attname as column_name from     pg_class t,     pg_class i,     pg_index ix,     pg_attribute a where     t.oid = ix.indrelid     and i.oid = ix.indexrelid     and a.attrelid = t.oid     and a.attnum = ANY(ix.indkey)     and t.relkind = 'r' order by     t.relname,     i.relname )T2 on ( T1.table_name = T2.table_name and T1.column_name = T2.column_name) order by T1.table_name, T1.column_name, T1.data_type, T2.index_name  ";
			try {
				Statement statement = con.createStatement();
				ResultSet rs = statement.executeQuery(query);
				JSONArray array = new JSONArray();
				while (rs.next()) {
					String tableName = rs.getString("table_name");
					String columnName = rs.getString("column_name");
					String dataType = rs.getString("data_type");
					String indexName = "";
					if(rs.getString("index_name")!=null)
					{
						indexName = rs.getString("index_name");
					}	
					JSONObject o = new JSONObject();
					o.put("tableName", tableName);
					o.put("columnName", columnName);
					o.put("dataType", dataType);
					o.put("indexName", indexName);
					array.put(o);
				}
				obj.put("data", array);
				System.out.println(obj.toString());
			} catch (SQLException e) {
				error = e.getMessage().toString();				
			}	
		}
		if(error==null)
		{
			request.setAttribute("schema_details", obj.toString());
			RequestDispatcher disp = request.getRequestDispatcher("/schema_details.jsp");			
			disp.forward(request, response);
		}
		else
		{
			request.setAttribute("error", error);
			RequestDispatcher disp = request.getRequestDispatcher("/schema_details.jsp");			
			disp.forward(request, response);
			
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
