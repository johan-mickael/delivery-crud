package rakotoniaina;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import ramorasata.Liste;
import Utils.Helper;

public class VLivraison {
	int id;
	String nomAxe;
	int valeur;
	String etat;
	String nomLivreur;
	String prenom;
	Date dateLivraison;
	String produit;
	
	public VLivraison(int id, String nomAxe, int valeur, String etat,
			String nomLivreur, String prenom, Date dateLivraison, String produit) {
		super();
		this.id = id;
		this.nomAxe = nomAxe;
		this.valeur = valeur;
		this.etat = etat;
		this.nomLivreur = nomLivreur;
		this.prenom = prenom;
		this.dateLivraison = dateLivraison;
		this.produit = produit;
	}
	public int getId() {
		return id;
	}
	public String getNomAxe() {
		return nomAxe;
	}
	public int getValeur() {
		return valeur;
	}
	public String getEtat() {
		return etat;
	}
	public String getNomLivreur() {
		return nomLivreur;
	}
	public String getPrenom() {
		return prenom;
	}
	public Date getDateLivraison() {
		return dateLivraison;
	}
	public String getProduit() {
		return produit;
	}
	
	public static VLivraison[] findAll() throws Exception {
		Connection con = null;
		ArrayList<VLivraison> list = new ArrayList<VLivraison>();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			con = Helper.getConnect();
			String sql = "select * from VLivraison";
			st = con.prepareStatement(sql);
			rs =  st.executeQuery();
			while(rs.next()) {
				list.add(new VLivraison(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7), rs.getString(8)));
			}
		} catch (Exception ex) {
			throw ex;
		} finally {
			if(st!=null) 
				st.close();
			if(rs != null)
				rs.close();
			if(con!=null) 
				con.close();
		}
		return list.toArray(new VLivraison[list.size()]);
	}
	
	public static Object[][] pagination(int numero, int limit) throws Exception {
		Connection con = null;
		Object[][] object = new Object[2][];
		ArrayList<VLivraison> list = new ArrayList<VLivraison>();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			con = Helper.getConnect();
			String sql = "select * from VLivraison limit ? offset ?";
			Integer[] tab = Helper.pages("VLivraison", limit, con);
			object[0] = tab;
			int offset = (limit*numero) - limit; 
			if(tab[1] < limit) offset = 0;
			st = con.prepareStatement(sql);
			st.setInt(1, limit);
			st.setInt(2, offset);
			rs =  st.executeQuery();
			while(rs.next()) {
				list.add(new VLivraison(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7), rs.getString(8)));
			}
		} catch (Exception ex) {
			throw ex;
		} finally {
			if(st!=null) 
				st.close();
			if(rs != null)
				rs.close();
			if(con!=null) 
				con.close();
		}
		object[1] = list.toArray(new VLivraison[list.size()]);
		return object;
	}
}
