package proyecto.RedSocial.proyecto.Interfaces;

import proyecto.RedSocial.proyecto.model.Entity.Post;
import proyecto.RedSocial.proyecto.model.Entity.User;
import proyecto.RedSocial.proyecto.model.Entity.Comment;

public interface IUser {
	public int getId();

	public void setId(int id);

	public String getNombre();

	public void setNombre(String nombre);

	public String getPassword();

	public void setPassword(String password);

	public String getAvatar();

	public void setAvatar(String avatar);

	public Post getPost();

	public void setPost(Post post);

	public Comment getComment();

	public void setComment(Comment comment);

	public Post getLikes();

	public void setLikes(Post likes);

	public User getFollowed();

	public void setFollowed(User followed);

	public User getFollower();

	public void setFollower(User follower);

}
