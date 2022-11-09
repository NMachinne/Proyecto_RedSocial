package proyecto.Postt.com.model.DAO;

import java.sql.SQLException;
import java.util.Collection;
import proyecto.Postt.com.model.Entity.User;

public class UserDAO extends ADAO<User> {
	//Las consultas MariaDB de este DAO
	private final static String INSERT = "INSERT INTO user (nombre,password,avatar) VALUES (?,?,?)";
	private final static String UPDATE = "UPDATE user SET nombre=?,password=?,avatar=? WHERE id=?";
	private final static String DELETE = "DELETE FROM user WHERE id=?";
	private final static String SELECTBYID = "SELECT id,nombre,password,avatar FROM user WHERE id=?";
	private final static String SELECTBYNAME = "SELECT id,nombre,password,avatar FROM user WHERE nombre=?";
	private final static String SELECTALL = "SELECT id,nombre,password,avatar FROM user";
	// Fin de las consultas

	public void save(User user) {
		try {
			super.create(user, INSERT, new Integer[] {1,2,3});
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Collection<User> getById(User user) {
		try {
			Collection<User> u = super.read(user, SELECTBYID, null);
			return u;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Collection<User> getByName(User user) {
		try {
			Collection<User> u = super.read(user, SELECTBYNAME, new Integer[] {1});
			return u;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Collection<User> getAll(User user) {
		try {
			Collection<User> u = super.read(user, SELECTALL, null);
			return u;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void update(User user) {
		try {
			super.update(user, UPDATE, new Integer[] {1, 2, 3, 0});
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(User user) {
		try {
			super.delete(user, DELETE, new Integer[] {0});
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
