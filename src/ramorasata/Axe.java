package ramorasata;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import rakotoniaina.Frais;
import Utils.BaseModele;

public class Axe extends BaseModele{
	String nom;
	
	public Axe() {}
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public void setId(int id) {
		// TODO Auto-generated method stub
		this.id = id;
	}

	
	public Axe(int id, String nom) {
		super();
		setId(id);
		setNom(nom);
	}
	@Override
	public void save(Connection con) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Connection con) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object[] findAll(Connection con) throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<Axe> array = new ArrayList<Axe>();
		PreparedStatement st = null;
		try {
			String sql = "select * from axe";
			st = con.prepareStatement(sql);
			ResultSet result =  st.executeQuery();
			while(result.next()) {
				array.add(new Axe(result.getInt("id"), result.getString("nomAxe")));
			}
		} catch(Exception ex){
			throw ex;
		}finally{
			if(st!=null)st.close();
		}
		Object[] tab =  array.toArray();
		return tab;
	}

	@Override
	public Object findById(Connection con, int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void update(Connection con) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public static Axe[] caster(Object[] ob) throws ClassCastException {
		Axe[] ret = new Axe[ob.length];
		for(int i=0; i<ob.length; i++) {
			ret[i] = (Axe) ob[i];
		}
		return ret;
	}
	
}
