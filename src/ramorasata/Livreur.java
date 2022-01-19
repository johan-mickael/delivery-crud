package ramorasata;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import rakotoniaina.Livraison;
import Utils.BaseModele;
import Utils.Helper;

public class Livreur extends BaseModele {
	String nom;
	String prenom;
	Integer idAxe;
	Date dateEntree;
	Integer etat;
	
	public Livreur(Integer id ,String nom, String prenom, Integer idAxe, Date dateEntree) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.idAxe = idAxe;
		this.dateEntree = dateEntree;
	}

	public Livreur() {}
	
	public Livreur(Integer id) {
		this.id = id;
	}
	
	public Livreur(String id, String nom, String prenom, String idAxe, String dateEntree) throws Exception {
		super();
		try {
			this.id = new Integer(id);
			setNom(nom);
			setPrenom(prenom);
			setIdAxe(new Integer(idAxe));
			setDateEntree(dateEntree);
		} catch(Exception ex) {
			throw ex;
		}
	}
	
	public Livreur(String nom, String prenom, String idAxe, String dateEntree) throws Exception {
		super();
		try {
			setNom(nom);
			setPrenom(prenom);
			setIdAxe(new Integer(idAxe));
			setDateEntree(dateEntree);
		} catch(Exception ex) {
			throw ex;
		}
	}
	
	public Integer getEtat() {
		return etat;
	}

	public void setEtat(Integer etat) {
		this.etat = etat;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) throws Exception {
		if(prenom.length() < 2) {
			throw new Exception("Nom invalide");
		}
		this.prenom = prenom;
	}
	
	public Date getDateEntree() {
		return dateEntree;
	}
	public void setDateEntree(String dateEntree) throws Exception {
		if(!Helper.verifyDate(dateEntree))
			throw new Exception("date invalide");
		this.dateEntree = Date.valueOf(dateEntree) ;
	}

	public int getIdAxe() {
		return idAxe;
	}

	public void setIdAxe(int idAxe) {
		this.idAxe = idAxe;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) throws Exception {
		if(nom.length() < 2) {
			throw new Exception("Nom invalide");
		}
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
	
	

	@Override
	public void save(Connection con) throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement st = null;
		con.setAutoCommit(false);
		try {
			String sql = "INSERT INTO LIVREUR(nomLivreur, prenom, idAxe, dateEntree, etat) VALUES(?,?,?,?,1)";
			st = con.prepareStatement(sql);
			st.setString(1, this.nom);
			st.setString(2, this.prenom);
			st.setInt(3, this.idAxe);
			st.setDate (4, this.dateEntree);
			st.executeUpdate();
			con.commit();
		}catch(Exception ex){
			con.rollback();
			throw ex;
		}finally {
			if(st!=null)st.close();
		}
	}
	
	public void saveLivreur() throws Exception {
		Connection con = null;
		try {
			con =  Helper.getConnect();
			this.save(con);
		} catch (Exception ex) {
			throw ex;
		} finally {
			if(con!=null) con.close();
		}
		
	}

	@Override
	public void delete(Connection con) throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement st = null;
		con.setAutoCommit(false);
		try {
			String sql = "UPDATE livreur set etat = 0 where id = ? ";
			st = con.prepareStatement(sql);
			st.setInt(1, this.id);
			st.executeUpdate();
			con.commit();
		}catch(Exception ex){
			con.rollback();
			throw ex;
		}finally {
			if(st!=null)st.close();
		}
	}
	
	public static void deleteLivreur(String id) throws Exception {
		Livreur livreur = new Livreur(new Integer(id));
		Connection con = null;
		try {
			con = Helper.getConnect();
			livreur.delete(con);
		} catch (Exception ex) {
			throw ex;
		} finally {
			if(con!=null) con.close();
		}
	}

	@Override
	public Object[] findAll(Connection con) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<Livreur> array = new ArrayList<Livreur>();
		PreparedStatement st = null;
		try {
			String sql = "select * from livreur where etat = 1";
			st = con.prepareStatement(sql);
			ResultSet result =  st.executeQuery();
			while(result.next()) {
				array.add(new Livreur(result.getInt("id"), result.getString("nomLivreur"),result.getString("prenom"), result.getInt("idAxe"), result.getDate("dateEntree")));
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
	public Object findById(Connection con, int id) throws Exception {
		// TODO Auto-generated method stub
		Livreur livreur = null;
		PreparedStatement st = null;
		try {
			String sql = "select * from livreur where id = ?";
			st = con.prepareStatement(sql);
			st.setInt(1, id); 
			ResultSet result =  st.executeQuery();
			result.next();
			livreur = new Livreur(result.getInt("id"), result.getString("nomLivreur"),result.getString("prenom"), result.getInt("idAxe"), result.getDate("dateEntree"));
		} catch(Exception ex){
			throw ex;
		}finally{
			if(st!=null)st.close();
		}
		return livreur;
	}

	@Override
	public void update(Connection con) throws Exception {
		PreparedStatement st = null;
		con.setAutoCommit(false);
		try {
			String sql = "UPDATE Livreur set nomLivreur = ? , prenom = ? , idAxe = ? , dateEntree = ? where id = ? ";
			st = con.prepareStatement(sql);
			st.setString(1, this.nom);
			st.setString(2, this.prenom);
			st.setInt(3, this.idAxe);
			st.setDate (4, this.dateEntree);
			st.setInt(5, this.id);
			st.executeUpdate();
			con.commit();
		}catch(Exception ex){
			con.rollback();
			throw ex;
		}finally {
			if(st!=null)st.close();
		}
	}
	
	public static void updateLivreur(Livreur livreur) throws Exception {
		Connection con = null;
		try {
			con = Helper.getConnect();
			livreur.update(con);
		} catch (Exception ex) {
			throw ex;
		} finally {
			if(con!=null) con.close();
		}
	}
	
	public static Livreur[] caster(Object[] ob) throws ClassCastException {
		Livreur[] ret = new Livreur[ob.length];
		for(int i=0; i<ob.length; i++) {
			ret[i] = (Livreur) ob[i];
		}
		return ret;
	}
	
	public static Livreur findModif(String id)throws Exception {
		Connection con = null;
		Livreur livreur = null;
		try{
		con = Helper.getConnect();
		livreur = (Livreur) new Livreur().findById(con, Integer.parseInt(id));
		}catch (Exception ex) {
			throw ex;
		} finally {
			if(con!=null) con.close();
		}
		return livreur;
	}
	
	public boolean verifierAxe(String idAxe) {
		String id = this.idAxe.toString();
		return id.compareTo(idAxe) == 0;
	}
}
