package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Helper {
	public static Connection getConnect() throws Exception {
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/livraison?serverTimezone=UTC","root","");  
			con.setAutoCommit(false);
			return con;
		}catch(Exception e){ 
			throw e;
		}  
	} 
	
	public static boolean verifyDate(String dateE) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date dateEntree = format.parse(dateE);
		Date date = new Date();
		if(date.before(dateEntree))	
			return false;
		return true;
	}
	
	public static String sha1(String mdp, Connection con) throws Exception {
		PreparedStatement st = null;
		ResultSet result = null;
		String ret = null;
		try {
			String sql = "select sha1('"+mdp+"')";
			st = con.prepareStatement(sql);
//			st.setString(1, mdp);
			result = st.executeQuery();
			result.next();
			ret = result.getString(1);
		} catch(Exception ex) {
			throw ex;
		} finally {
			if(result!=null)result.close();
			if(st!=null) st.close();
		}
		return ret;
	}
	
	public static int login(String login, String mdp) throws Exception {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet result = null;
		int id = 0;
		try {
			con = getConnect();
			String sql = "select * from utilisateurs where identifiant = '"+login+"' and mdp = '"+ sha1(mdp,con)+ "'";
			st = con.prepareStatement(sql);
			result = st.executeQuery();
			if(!result.next()) throw new Exception("Identifiant ou mot de passe incorrect");
			id = result.getInt("id");
		} catch(Exception ex) {
			throw ex;
		} finally {
			if(result!=null)result.close();
			if(st!=null) st.close();
			if(con!=null) con.close();
		}
		return id;
	}
	public static Integer[] pages(String table, int nb, Connection con) throws Exception {
		Integer[] ret = new Integer[2];
		PreparedStatement st = null;
		ResultSet result = null;
		try {
			con = getConnect();
			String sql = "select count(*) from "+table;
			st = con.prepareStatement(sql);
			result = st.executeQuery();
			result.next();
			int count = result.getInt(1);
			ret[0] = count/nb;
			if(count-ret[0]*nb > 0) ret[0]++;
			ret[1] = count;
		} catch(Exception ex) {
			throw ex;
		} finally {
			if(result!=null)result.close();
			if(st!=null) st.close();
		}
		return ret;
	}
}
