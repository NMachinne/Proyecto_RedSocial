package proyecto.Postt.com.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import proyecto.Postt.com.model.Conection.MariaDBConnection;
import proyecto.Postt.com.model.Entity.Post;
import proyecto.Postt.com.model.Entity.User;

public class CommentDAO extends User{
	//Las consultas MariaDB de este DAO
		private final static String INSERT = "INSERT INTO User (nombre,superficie,id_sede) VALUES (?,?,?)";
		private final static String UPDATE = "UPDATE User SET nombre=?,superficie=?,id_sede=? WHERE id=?";
		private final static String DELETE = "DELETE FROM User WHERE id=?";
		private final static String SELECTBYID = "SELECT id,nombre,superficie,id_sede FROM User WHERE id=?";
		private final static String SELECTALL = "SELECT id,nombre,superficie FROM User";
		private final static String SELECTBYSEDE = "SELECT id,nombre,superficie,id_sede FROM User WHERE id_sede=?";
	
	public PostDAO(int id, String nombre, int superficie) {
		super(id,nombre,superficie);
	}
	public CommentDAO() {
		super();
	}
	public CommentDAO(User d) {
		this(d.getId(),d.getNombre(),d.getSuperficie());
		this.sede = d.getSede();
	}
	public CommentDAO(int id) {
		this.getById(id);
	}
	
	public void save() {
		if(id==-1) {
			//INSERT
			Connection conn = MariaDBConnection.getConnection();
			if(conn != null) {
				PreparedStatement ps;
				try {
					ps = conn.prepareStatement(INSERT,Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, this.nombre);
					System.out.println(this.sede);
					ps.setInt(3, this.sede.getId());
					ps.setInt(2, this.superficie);
					ps.executeUpdate();  //devuelve 1 si todo ok
					ResultSet rs = ps.getGeneratedKeys();
					if(rs.next()) {
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
		if(id!=-1) {
			//UPDATE
			Connection conn = MariaDBConnection.getConnection();
			if(conn != null) {
				PreparedStatement ps;
				try {
					ps = conn.prepareStatement(UPDATE);
					ps.setString(1, this.nombre);
					ps.setInt(2, this.superficie);
					ps.setInt(3, this.sede.getId());
					ps.setInt(4, this.id);
					ps.executeUpdate();  //devuelve 1 si todo ok
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		}
	}
	public void remove() {
		if(id!=-1) {
			//UPDATE
			Connection conn = MariaDBConnection.getConnection();
			if(conn != null) {
				PreparedStatement ps;
				try {
					ps = conn.prepareStatement(DELETE);
					ps.setInt(1, this.id);
					if(ps.executeUpdate()==1) {
						this.id=-1;
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
		if(conn != null) {
			PreparedStatement ps;
			try {
				ps = conn.prepareStatement(SELECTBYID);
				ps.setInt(1, id);
				if(ps.execute()) {
					ResultSet rs = ps.getResultSet();
					if(rs.next()) {
						this.id = rs.getInt("id");
						this.nombre = rs.getString("nombre");
						this.superficie = rs.getInt("superficie");
						int id_sede = rs.getInt("id_sede");
						//eager
						this.sede = new UserDAO(id_sede);
					}
				}
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public static List<User> getAll(){
		List<User> result = new ArrayList<User>();
		Connection conn = MariaDBConnection.getConnection();
		if(conn != null) {
			PreparedStatement ps;
			try {
				ps = conn.prepareStatement(SELECTALL);
				if(ps.execute()) {
					ResultSet rs = ps.getResultSet();
					while(rs.next()) {
						User c = new User(rs.getInt("id"),
									rs.getString("nombre"),
									rs.getInt("superficie"));
						result.add(c);
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
	public static List<User> getComplejosOfSede(Post s){
		List<User> result = new ArrayList<User>();
		Connection conn = MariaDBConnection.getConnection();
		if(conn != null) {
			PreparedStatement ps;
			try {
				ps = conn.prepareStatement(SELECTBYSEDE);
				ps.setInt(1, s.getId());
				if(ps.execute()) {
					ResultSet rs = ps.getResultSet();
					while(rs.next()) {
						User c = new User(rs.getInt("id"),
									rs.getString("nombre"),
									rs.getInt("superficie"));
						c.setSede(new Post(rs.getInt("id_sede")));
						result.add(c);
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
}
