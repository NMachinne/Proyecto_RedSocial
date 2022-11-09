package proyecto.Postt.com.model.DAO;

import java.sql.SQLException;
import java.util.Collection;
import proyecto.Postt.com.model.Entity.Comment;

public class CommentDAO extends ADAO<Comment>{
	//Las consultas MariaDB de este DAO
	private final static String INSERT = "INSERT INTO comment (fecha,texto) VALUES (?,?)";
	private final static String UPDATE = "UPDATE comment SET fecha=?,texto=? WHERE id=?";
	private final static String DELETE = "DELETE FROM comment WHERE id=?";
	private final static String SELECTBYID = "SELECT fecha,texto FROM comment WHERE id=?";
	private final static String SELECTALL = "SELECT fecha,texto FROM comment";
	// Fin de las consultas
	
	public void save(Comment comment) {
		try {
			super.create(comment, INSERT, new Integer[]{1,2,3});
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Collection<Comment> getById(Comment comment) {
		try {
			Collection<Comment> c = super.read(comment, SELECTBYID, null);
			return c;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Collection<Comment> getAll(Comment comment) {
		try {
			Collection<Comment> c = super.read(comment, SELECTALL, null);
			return c;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void update(Comment comment) {
		try {
			super.update(comment, UPDATE, new Integer[]{1, 2, 3, 0});
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(Comment comment) {
		try {
			super.delete(comment, DELETE, new Integer[]{0});
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
