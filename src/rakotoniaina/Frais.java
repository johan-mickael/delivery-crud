package rakotoniaina;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Utils.BaseModele;

public class Frais extends BaseModele {
	private int valeur;
	
	public Frais() {
		
	}
	public Frais(int id, int valeur) throws Exception {
		super();
		setId(id);
		setValeur(valeur);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) throws Exception {
		if(id < 1)
			throw new Exception("id invalide"); 
		this.id = id;
	}
	public int getValeur() {
		return valeur;
	}
	public void setValeur(int valeur) throws Exception {
		if(valeur < 1)
			throw new Exception("valeur invalide");
		this.valeur = valeur;
	}
	@Override
	public void save(Connection con) throws Exception {
		PreparedStatement st = null;
		try {
			String sql = "INSERT INTO Frais(valeur) VALUES(?)";
			st = con.prepareStatement(sql);
			st.setInt(1, this.valeur);
			st.executeUpdate();
			con.commit();
		}catch(Exception ex){
			con.rollback();
			throw ex;
		}finally {
			st.close();
		}
	}
	@Override
	public void delete(Connection con) throws Exception {
		Function.delete(con, "FRAIS", this.id);
	}
	@Override
	public void update(Connection con) throws Exception {
		PreparedStatement st = null;
		try {
			String query = "UPDATE FRAIS SET valeur=? where id=?";
			st = con.prepareStatement(query);
			st.setInt(1, this.valeur);
			st.setInt(2, this.id);
			st.executeUpdate();
			con.commit();
		}catch(Exception ex){
			con.rollback();
			throw ex;
		}finally {
			st.close();
		}
	}
	@Override
	public Frais[] findAll(Connection con) throws Exception {
		ArrayList<Frais> list = new ArrayList<Frais>();
		Frais[] ret = null;
		Statement ps = null;
	    ResultSet rs = null;
	    try{
	    	String query = "SELECT * FROM Frais";
	    	ps = con.createStatement();
	    	rs = ps.executeQuery(query);
	    	while(rs.next()) {
	    		int id = rs.getInt(1);
	    		int valeur = rs.getInt(2);
	    		Frais tempFrais = new Frais(id, valeur);
	    		list.add(tempFrais);
	    	}
	    } catch(Exception ex){
	    	throw ex;
	    } finally {
	    	ps.close();
	    	rs.close();
	    }
	    ret = list.toArray(new Frais[list.size()]);
		return ret;
	}
	@Override
	public Frais findById(Connection con, int id) throws Exception {
		Frais ret = null;
		Statement ps = null;
	    ResultSet rs = null;
	    try{
	    	String query = "SELECT * FROM Frais where id = " + id;
	    	ps = con.createStatement();
	    	rs = ps.executeQuery(query);
	    	if(rs.next()) {
	    		int idf = rs.getInt(1);
	    		int valeur = rs.getInt(2);
	    		ret = new Frais(idf, valeur);
	    	}
	    } catch(Exception ex){
	    	throw ex;
	    } finally {
	    	ps.close();
	    	rs.close();
	    }
		return ret;
	}
	
	public static Frais[] caster(Object[] ob) throws ClassCastException {
		Frais[] ret = new Frais[ob.length];
		for(int i=0; i<ob.length; i++) {
			ret[i] = (Frais) ob[i];
		}
		return ret;
	}
}
