package rakotoniaina;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ramorasata.Liste;
import ramorasata.Livreur;
import Utils.BaseModele;
import Utils.Helper;

public class Livraison extends BaseModele {
	private int idAxe;
	private int idFrais;
	private int etat;
	private int idLivreur;
	private Date dateLivraison;
	private String produit;
	
	public Livraison() {
		// creates a new instance of <Livraison>
	}

	public Livraison(int id, int idAxe, int idFrais, int etat, int idLivreur,
			Date dateLivraison, String produit) throws Exception {
		super();
		setId(id);
		setIdAxe(idAxe);;
		setIdFrais(idFrais);
		setEtat(etat);
		setIdLivreur(idLivreur);
		setDateLivraison(dateLivraison);
		setProduit(produit);
	}
	
	public Livraison(int idAxe, int idFrais, int etat, int idLivreur,
			Date dateLivraison, String produit) throws Exception {
		super();
		setIdAxe(idAxe);;
		setIdFrais(idFrais);
		setEtat(etat);
		setIdLivreur(idLivreur);
		setDateLivraison(dateLivraison);
		setProduit(produit);
	}
	
	public Livraison(String id, String idAxe, String idFrais, String etat, String idLivreur, 
			String dateLivraison, String produit) throws Exception {
		super();
		setId(id);
		setIdAxe(idAxe);
		setIdFrais(idFrais);
		setEtat(etat);
		setIdLivreur(idLivreur);
		setDateLivraison(dateLivraison);
		setProduit(produit);
	}
	public Livraison(String idL) {
		this.id = (int) new Integer(idL);
	}
	public int getIdAxe() {
		return idAxe;
	}
	public void setIdAxe(int idAxe) throws Exception {
		if(idAxe < 1)
			throw new Exception("idAxe invalide");
		this.idAxe = idAxe;
	}
	public void setIdAxe(String idAxe) throws Exception {
		int id = (int) new Integer(idAxe);
		setIdAxe(id);
	}
	public int getIdFrais() {
		return idFrais;
	}
	public void setIdFrais(int idFrais) throws Exception {
		if(idFrais < 1)
			throw new Exception("idFrais invalide");
		this.idFrais = idFrais;
	}
	public void setIdFrais(String idFrais) throws Exception {
		int id = (int) new Integer(idFrais);
		setIdFrais(id);
	}
	public int getEtat() {
		return etat;
	}
	public void setEtat(int etat) throws Exception {
		if(etat < 1 || etat > 3)
			throw new Exception("etat invalide");
		this.etat = etat;
	}
	public void setEtat(String etat) throws Exception {
		int et = (int) new Integer(etat);
		setEtat(et);
	}
	public int getIdLivreur() {
		return idLivreur;
	}
	public void setIdLivreur(int idLivreur) throws Exception {
		if(idLivreur < 1)
			throw new Exception("idLivreur invalide");
		this.idLivreur = idLivreur;
	}
	public void setIdLivreur(String idLivreur) throws Exception {
		int id = (int) new Integer(idLivreur);
		setIdLivreur(id);
	}
	public Date getDateLivraison() {
		return dateLivraison;
	}
	public void setDateLivraison(Date dateLivraison) {
		this.dateLivraison = dateLivraison;
	}
	public void setDateLivraison(String dateLivraison) throws Exception {
		if(Helper.verifyDate(dateLivraison))
			throw new Exception("date invalide");
		this.dateLivraison = Date.valueOf(dateLivraison) ;
	}
	public String getProduit() { 
		return produit;
	}
	public void setProduit(String produit) throws Exception {
		if(produit.length() < 1)
			throw new Exception("produit invalide");
		this.produit = produit;
	}
	@Override
	public int getId() {
		return this.id;
	}
	@Override
	public void setId(int id) throws Exception {
		if(id < 1)
			throw new Exception("id invalide");
		this.id = id;
	}
	public void setId(String id) throws Exception {
		if(id == null) 
			return; 
		int idL = (int) new Integer(id);
		setId(idL);
	}
	@Override
	public void save(Connection con) throws Exception {
		PreparedStatement st = null;
		try {
			String sql = "INSERT INTO LIVRAISON(idAxe, idFrais, etat, idLivreur, dateLivraison, produit) VALUES(?, ?, ?, ?, ?, ?)";
			st = con.prepareStatement(sql);
			st.setInt(1, this.idAxe);
			st.setInt(2, this.idFrais);
			st.setInt(3, this.etat);
			st.setInt(4, this.idLivreur);
			st.setDate(5, this.dateLivraison);
			st.setString(6, this.produit);
			st.executeUpdate();
			con.commit();
		}catch(Exception ex){
			con.rollback();
			throw ex;
		}finally {
			st.close();
		}
	}
	public void save() throws Exception {
		Connection con = null;
		try {
			con =  Helper.getConnect();
			this.save(con);
		} catch (Exception ex) {
			throw ex;
		} finally {
			if(con!=null) 
				con.close();
		}
	}
	@Override
	public void delete(Connection con) throws Exception {
		Function.delete(con, "LIVRAISON", this.id);
	}
	public void delete() throws Exception {
		Connection con = null;
		try {
			con = Helper.getConnect();
			this.delete(con);
		} catch (Exception ex) {
			throw ex;
		} finally {
			if(con!=null) 
				con.close();
		}
	}
	@Override
	public void update(Connection con) throws Exception {
		PreparedStatement st = null;
		try {
			String query = "UPDATE LIVRAISON SET idAxe=?, idFrais=?, etat=?, idLivreur=?, dateLivraison=?, produit=? where id=?";
			st = con.prepareStatement(query);
			st.setInt(1, this.idAxe);
			st.setInt(2, this.idFrais);
			st.setInt(3, this.etat);
			st.setInt(4, this.idLivreur);
			st.setDate(5, this.dateLivraison);
			st.setString(6, this.produit);
			st.setInt(7, this.id);
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
	public Livraison[] findAll(Connection con) throws Exception {
		ArrayList<Livraison> list = new ArrayList<Livraison>();
		Livraison[] ret = null;
		Statement ps = null;
	    ResultSet rs = null;
	    try{
	    	String query = "SELECT * FROM Livraison";
	    	ps = con.createStatement();
	    	rs = ps.executeQuery(query);
	    	while(rs.next()) {
	    		int id = rs.getInt(1);
	    		int idAxe = rs.getInt(2);
	    		int idFrais = rs.getInt(3);
	    		int etat = rs.getInt(4);
	    		int idLivreur = rs.getInt(5);
	    		Date dateLivraison = rs.getDate(6);
	    		String produit = rs.getString(7);
	    		Livraison tempLivraison = new Livraison(id, idAxe, idFrais, etat, idLivreur, dateLivraison, produit);
	    		list.add(tempLivraison);
	    	}
	    } catch(Exception ex){
	    	throw ex;
	    } finally {
	    	ps.close();
	    	rs.close();
	    }
	    ret = list.toArray(new Livraison[list.size()]);
		return ret;
	}
	@Override
	public Livraison findById(Connection con, int id) throws Exception {
		Livraison ret = null;
		Statement ps = null;
	    ResultSet rs = null;
	    try{
	    	String query = "SELECT * FROM Livraison where id = %s";
	    	query = String.format(query, id);
	    	ps = con.createStatement();
	    	rs = ps.executeQuery(query);
	    	if(rs.next()) {
	    		int idL = rs.getInt(1);
	    		int idAxe = rs.getInt(2);
	    		int idFrais = rs.getInt(3);
	    		int etat = rs.getInt(4);
	    		int idLivreur = rs.getInt(5);
	    		Date dateLivraison = rs.getDate(6);
	    		String produit = rs.getString(7);
	    		ret = new Livraison(idL, idAxe, idFrais, etat, idLivreur, dateLivraison, produit);
	    	}
	    } catch(Exception ex){
	    	throw ex;
	    } finally {
	    	ps.close();
	    	rs.close();
	    }
		return ret;
	}
	
	public static Object[][] findAll() throws Exception {
		Connection con = null;
		Object[][] ret = new Object[2][];
		try {
			con = Helper.getConnect();
			ret[0] = new Livraison().findAll(con);
			ret[1] = Liste.findAll();
		} catch(Exception ex) {
			throw ex;
		} finally {
			if(con != null)
				con.close();
		}
		return ret;
	}
	
	public static Livraison findById(int id) throws Exception {
		Connection con = null;
		Livraison livraison = null;
		try{
		con = Helper.getConnect();
		livraison = (Livraison) new Livraison().findById(con, id);
		}catch (Exception ex) {
			throw ex;
		} finally {
			if(con!=null) 
				con.close();
		}
		return livraison;
	}
	
	public void update() throws Exception {
		Connection con = null;
		try{
		con = Helper.getConnect();
		this.update(con);
		}catch (Exception ex) {
			throw ex;
		} finally {
			if(con!=null) 
				con.close();
		}
	}
	
	public static Livraison[] caster(Object[] ob) throws ClassCastException {
		Livraison[] ret = new Livraison[ob.length];
		for(int i=0; i<ob.length; i++) {
			ret[i] = (Livraison) ob[i];
		}
		return ret;
	}
}
