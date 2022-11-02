package proyecto.Postt.com.model.DAO;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import proyecto.Postt.com.model.Conection.MariaDBConnection;
import proyecto.Postt.com.model.Entity.Comment;
import proyecto.Postt.com.model.Entity.Post;
import proyecto.Postt.com.model.Entity.User;

//UserDAO ImplMariaDB
public class UserDAO extends Post {
//Las consultas MariaDB de este DAO
	private final static String INSERT = "INSERT INTO user (nombre) VALUES (?)";
	private final static String UPDATE = "UPDATE user SET nombre=? WHERE id=?";
	private final static String DELETE = "DELETE FROM user WHERE id=?";
	private final static String SELECTBYID = "SELECT id,nombre FROM user WHERE id=?";
	private final static String SELECTALL = "SELECT id,nombre FROM user";

	// Fin de las consultas
	public UserDAO(int id, String nombre, String password, String avatar, List<Post> post, List<Comment> comment,
			List<Post> likes, List<User> followed, List<User> follower) {
		super();
	}

	public UserDAO() {
		super();
	}

	public UserDAO(User user) {
		super();
	}

	public UserDAO(int id) {
		this.getById(id);
	}

	public void save() {
		if (id == -1) {
			// INSERT
			Connection conn = MariaDBConnection.getConnection();
			if (conn != null) {
				PreparedStatement ps;
				try {
					ps = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, this.nombre);
					ps.executeUpdate(); 
					ResultSet rs = ps.getGeneratedKeys();
					if (rs.next()) {
						this.id = rs.getInt(1);
					}
					ps.close();
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		}
	}

	public void update() {
		if (id != -1) {
			// UPDATE
			Connection conn = MariaDBConnection.getConnection();
			if (conn != null) {
				PreparedStatement ps;
				try {
					ps = conn.prepareStatement(UPDATE);
					ps.setString(1, this.nombre);
					ps.setInt(2, this.id);
					ps.executeUpdate(); 
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		}
	}

	public void remove() {
		if (id != -1) {
			// UPDATE
			Connection conn = MariaDBConnection.getConnection();
			if (conn != null) {
				PreparedStatement ps;
				try {
					ps = conn.prepareStatement(DELETE);
					ps.setInt(1, this.id);
					if (ps.executeUpdate() == 1) {
						this.id = -1;
					}
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		}
	}

	private void getById(int id) {
		Connection conn = MariaDBConnection.getConnection();
		if (conn != null) {
			PreparedStatement ps;
			try {
				ps = conn.prepareStatement(SELECTBYID);
				ps.setInt(1, id);
				if (ps.execute()) {
					ResultSet rs = ps.getResultSet();
					if (rs.next()) {
						this.id = rs.getInt("id");
						this.nombre = rs.getString("nombre");
						
					}
				}
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	public static List<Post> getAll() {
		List<Post> result = new ArrayList<Post>();
		Connection conn = MariaDBConnection.getConnection();
		if (conn != null) {
			PreparedStatement ps;
			try {
				ps = conn.prepareStatement(SELECTALL);
				if (ps.execute()) {
					ResultSet rs = ps.getResultSet();
					while (rs.next()) {
						Post s = new Post(rs.getInt("id"), rs.getString("nombre"));
						result.add(s);
					}
					rs.close();
				}
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public List<User> getUser() {
		if (super.getUser() == null) {
			System.out.println("Consultando...");
			setUser(PostDAO.getComplejosOfSede(this));
		}
		return super.setUser();
	}

	@Override
	public void addUser(User u) {
		u.SET(this);
		UserDAO udao = new UserDAO(u);
		if (u.getId() == -1) {
			udao.save();
		} else {
			udao.update();
		}
		super.addUser(udao);
	}

}
