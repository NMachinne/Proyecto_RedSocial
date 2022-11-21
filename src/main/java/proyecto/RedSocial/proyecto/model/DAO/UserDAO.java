package proyecto.RedSocial.proyecto.model.DAO;

import java.sql.SQLException;
import java.util.Collection;

import proyecto.RedSocial.proyecto.model.Entity.Post;
import proyecto.RedSocial.proyecto.model.Entity.User;

public class UserDAO extends ADAO<User> {
	// Las consultas MariaDB de este DAO
	private final static String INSERT = "INSERT INTO user (nombre,password,avatar) VALUES (?,?,?)";
	private final static String INSERTLIKE = "INSERT INTO likes (id_usuario,id_publicacion,fecha) VALUES (?,?,?)";
	private final static String INSERTFOLLOW = "INSERT INTO follow (id,id_usuario) VALUES (?,?)";
	private final static String UPDATE = "UPDATE user SET nombre=?,password=?,avatar=? WHERE id=?";
	private final static String DELETE = "DELETE FROM user WHERE id=?";
	private final static String DELETEFOLLOW = "DELETE FROM follow WHERE id_usuario=? AND id=?";
	private final static String DELETELIKE = "DELETE FROM likes WHERE id_usuario=? and id_publicacion=?";
	private final static String SELECTBYID = "SELECT id,nombre,password,avatar FROM user WHERE id=?";
	private final static String SELECTBYNAME = "SELECT id,nombre,password,avatar FROM user WHERE nombre=?";	
	private final static String SELECTBYFOLLOWER = "SELECT id,id_usuario,null,null FROM follow WHERE id=?";
	private final static String SELECTBYFOLLOWED = "SELECT id,id_usuario,null,null FROM follow WHERE id_usuario=?";
	private final static String SELECTBYLIKE = "SELECT id_usuario,id_publicacion,fecha,null FROM likes WHERE id_usuario=? and id_publicacion=?";
	private final static String SELECTBYLIKEPOST = "SELECT id_usuario,id_publicacion,fecha,null FROM likes WHERE id_publicacion=?";
	private final static String SELECTBYFOLLOWBYID = "SELECT id,id_usuario,null,null FROM follow WHERE id=? AND id_usuario=?";
	private final static String SELECTALL = "SELECT id,nombre,password,avatar FROM user";
	// Fin de las consultas

	public void save(User user) {
		try {
			super.create(user, INSERT, new Integer[] { 1, 2, 3 });
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void saveLike(User user) {
		try {
			super.create(user, INSERTLIKE, new Integer[] { 0, 1, 2 });
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertFollow(User user) {
		try {
			super.create(user, INSERTFOLLOW, new Integer[] { 0, 1 });
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
	
	public Collection<User> getByIdUser(User user) {
		try {
			Collection<User> u = super.read(user, SELECTBYID, new Integer[] {1});
			return u;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	public Collection<User> getByName(User user) {
		try {
			Collection<User> u = super.read(user, SELECTBYNAME, new Integer[] { 1 });
			return u;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Collection<User> getByFollow(User user) {
		try {
			Collection<User> u = super.read(user, SELECTBYFOLLOWER, null);
			return u;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Collection<User> getByFollowed(User user) {
		try {
			Collection<User> u = super.read(user, SELECTBYFOLLOWED, null);
			return u;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Collection<User> getByLike(User user) {
		try {
			Collection<User> u = super.read(user, SELECTBYLIKE, new Integer[] { 0, 1 });
			return u;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Collection<User> getByLikePost(User user) {
		try {
			Collection<User> u = super.read(user, SELECTBYLIKEPOST, new Integer[] { 0 });
			return u;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Collection<User> getByFollowById(User user) {
		try {
			Collection<User> u = super.read(user, SELECTBYFOLLOWBYID, new Integer[] { 0,1});
			return u;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Collection<User> getAll() {
		try {
			Collection<User> u = super.read(new User(), SELECTALL, null);
			return u;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	 

	public void update(User user) {
		try {
			super.update(user, UPDATE, new Integer[] { 1, 2, 3, 0 });
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(User user) {
		try {
			super.delete(user, DELETE, new Integer[] { 0 });
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteLike(User user) {
		try {
			super.delete(user, DELETELIKE, new Integer[] { 0, 1 });
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteFollow(User user) {
		try {
			super.delete(user, DELETEFOLLOW, new Integer[] { 0, 1 });
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
