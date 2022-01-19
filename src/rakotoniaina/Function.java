package rakotoniaina;

import java.sql.Connection;
import java.sql.PreparedStatement;

import ramorasata.Axe;
import Utils.Helper;

public class Function {
	public static void delete(Connection con, String table, int id) throws Exception {
		PreparedStatement st = null;
		try {
			String query = "DELETE FROM "+table+" WHERE id = ?";
			st = con.prepareStatement(query);
			st.setInt(1, id);
			st.executeUpdate();
			con.commit();
		}catch(Exception ex){
			con.rollback();
			throw ex;
		}finally {
			st.close();
		}
	}
	
	public static Object[][] findBasicData() throws Exception {
		Connection con = null;
		Object[][] ret = new Object[2][];
		try {
			con = Helper.getConnect();
			ret[0] = new Frais().findAll(con);
			ret[1] = new Axe().findAll(con);
		} catch(Exception ex) {
			throw ex;
		} finally {
			if(con != null)
				con.close();
		}
		return ret;
	}
}
