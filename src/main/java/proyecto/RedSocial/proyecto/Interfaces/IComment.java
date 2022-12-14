package proyecto.RedSocial.proyecto.Interfaces;

import java.util.List;

import proyecto.RedSocial.proyecto.model.Entity.Post;
import proyecto.RedSocial.proyecto.model.Entity.User;

public interface IComment {

	public String getFecha();

	public void setFecha(String id);

	public String getTxt();

	public void setTxt(String txt);

	public Post getPost();

	public void setPost(Post post);

	public User getUser();

	public void setUser(User user);
}
