package proyecto.Postt.com.model.DAO;

import java.sql.SQLException;
import java.util.Collection;
import proyecto.Postt.com.model.Entity.Post;

public class PostDAO extends ADAO<Post> {
	// Las consultas MariaDB de este DAO
	private final static String INSERT = "INSERT INTO post (fecha,texto,multimedia,id_usuario) VALUES (?,?,?,?)";
	private final static String UPDATE = "UPDATE post SET fecha=?,texto=?,multimedia=? WHERE id=?";
	private final static String DELETE = "DELETE FROM post WHERE id=?";
	private final static String SELECTBYID = "SELECT id,fecha,texto,multimedia FROM post WHERE id=?";
	private final static String SELECTALL = "SELECT id,fecha,texto,multimedia FROM post";
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

	public Collection<Post> getAll(Post post) {
		try {
			Collection<Post> p = super.read(post, SELECTALL, null);
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
