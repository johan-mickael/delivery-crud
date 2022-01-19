package ramorasata;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import rakotoniaina.Livraison;
import Utils.Helper;

public class Liste {
	Integer id;
	String nom;
	String prenom;
	String axe;
	Integer idAxe;
	Date dateEntree;
	
	public Liste(Integer id, String nom, String prenom, Integer idAxe, String axe, Date dateEntree) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.axe = axe;
		this.idAxe = idAxe;
		this.dateEntree = dateEntree;
	}
	
	public static Liste[] findAll() throws Exception {
		Connection con = null;
		ArrayList<Liste> array = new ArrayList<Liste>();
		PreparedStatement st = null;
		ResultSet result = null;
		try {
			con = Helper.getConnect();
			String sql = "select * from listeLivreurs";
			st = con.prepareStatement(sql);
			result = st.executeQuery();
			while(result.next()) {
				array.add(new Liste(result.getInt("id"), result.getString("nomLivreur"), result.getString("prenom"), result.getInt("idAxe"), result.getString("nomAxe"), result.getDate("dateEntree")));
			}
		} catch (Exception ex) {
			throw ex;
		} finally {
			if(result!=null)	result.close();
			if(st!=null) st.close();
			if(con!=null) con.close();
		}
		Object[] tab =  array.toArray();
		Liste[] ret = new Liste[tab.length];
		for(int i = 0; i < tab.length; i++) {
			ret[i] = (Liste) tab[i];
		}
		return ret;
	}
	
	public static Object[][] pagination(int numero, int limit) throws Exception {
		Connection con = null;
		Object[][] object = new Object[2][];
		ArrayList<Liste> array = new ArrayList<Liste>();
		PreparedStatement st = null;
		ResultSet result = null;
		try {
			con = Helper.getConnect();
			String sql = "select * from listeLivreurs limit ? offset ?";
			Integer[] tab = Helper.pages("listeLivreurs", limit, con);
			object[0] = tab;
			int offset = (limit*numero) - limit; 
			if(tab[1] < limit) offset = 0;
			st = con.prepareStatement(sql);
			st.setInt(1, limit);
			st.setInt(2, offset);
			result = st.executeQuery();
			while(result.next()) {
				array.add(new Liste(result.getInt("id"), result.getString("nomLivreur"), result.getString("prenom"), result.getInt("idAxe"), result.getString("nomAxe"), result.getDate("dateEntree")));
			}
		} catch (Exception ex) {
			throw ex;
		} finally {
			if(result!=null)	result.close();
			if(st!=null) st.close();
			if(con!=null) con.close();
		}
		Object[] tab =  array.toArray();
		Liste[] ret = new Liste[tab.length];
		for(int i = 0; i < tab.length; i++) {
			ret[i] = (Liste) tab[i];
		}
		object[1] = ret;
		return object;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getAxe() {
		return axe;
	}

	public void setAxe(String axe) {
		this.axe = axe;
	}

	public Date getDateEntree() {
		return dateEntree;
	}

	public void setDateEntree(Date dateEntree) {
		this.dateEntree = dateEntree;
	}
	
	public Integer getIdAxe() {
		return idAxe;
	}

	public static Liste[] caster(Object[] ob) throws ClassCastException {
		Liste[] ret = new Liste[ob.length];
		for(int i=0; i<ob.length; i++) {
			ret[i] = (Liste) ob[i];
		}
		return ret;
	}
}
