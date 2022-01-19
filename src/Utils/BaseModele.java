package Utils;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class BaseModele {
	protected int id;

	public abstract int getId();

	public abstract void setId(int id) throws Exception;
	
	public abstract void save(Connection con) throws Exception;
	
	public abstract void delete(Connection con) throws Exception;
	
	public abstract void update(Connection con) throws Exception;
	
	public abstract Object[] findAll(Connection con) throws Exception;
	
	public abstract Object findById(Connection con, int id) throws Exception;
}
