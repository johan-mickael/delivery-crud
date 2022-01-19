package Utils;

import java.sql.Connection;
import java.sql.Date;
import java.text.SimpleDateFormat;

import rakotoniaina.Livraison;
import ramorasata.Axe;

public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
//			Object[][] ob = new Livraison().findAll();
//			System.out.println(ob);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date dateEntree = format.parse("2020-10-04");
			System.out.println(dateEntree.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
