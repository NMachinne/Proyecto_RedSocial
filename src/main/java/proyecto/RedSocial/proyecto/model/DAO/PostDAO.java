package proyecto.RedSocial.proyecto.model.DAO;

import java.sql.SQLException;
import java.util.Collection;
import proyecto.RedSocial.proyecto.model.Entity.Post;
import proyecto.RedSocial.proyecto.model.Entity.User;

public class PostDAO extends ADAO<Post> {
	// Las consultas MariaDB de este DAO
	private final static String INSERT = "INSERT INTO post (fecha,texto,multimedia,id_usuario) VALUES (?,?,?,?)";
	private final static String UPDATE = "UPDATE post SET fecha=?,texto=?,multimedia=? WHERE id=?";
	private final static String DELETE = "DELETE FROM post WHERE id=?";
	private final static String SELECTBYID = "SELECT id,fecha,texto,multimedia FROM post WHERE id=?";
	private final static String SELECTBYID_USER = "SELECT id,fecha,texto,multimedia FROM post WHERE id_usuario=? ORDER BY id DESC LIMIT 1";
	private final static String SELECTALL = "SELECT id,fecha,texto,multimedia FROM post";
	private final static String SELECTALLBYID_USER = "SELECT id,fecha,texto,multimedia FROM post WHERE id_usuario=?";
	// Fin de las consultas

	public void save(Post post) {
		try {
			super.create(post, INSERT, new Integer[] {1, 2, 3, 4});
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Collection<Post> getById(Post post) {
		try {
			Collection<Post> p = super.read(post, SELECTBYID, null);
			return p;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Collection<Post> getAllByIdUser(Post post){
		try {
			Collection<Post> p = super.read(post, SELECTALLBYID_USER,new Integer[] {0});
			return p;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;	
	}
	
	public Collection<Post> getByIdUser(Post post) {
		try {
			Collection<Post> p = super.read(post, SELECTBYID_USER, new Integer[] {1});
			return p;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Collection<Post> getAll() {
		try {
			Collection<Post> p = super.read(new Post(), SELECTALL, null);
			return p;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void update(Post post) {
		try {
			super.update(post, UPDATE, new Integer[] {1, 2, 3, 0});
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(Post post) {
		try {
			super.delete(post, DELETE, new Integer[] {0});
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
