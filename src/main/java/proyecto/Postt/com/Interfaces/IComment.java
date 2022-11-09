package proyecto.Postt.com.Interfaces;

import java.util.List;

import proyecto.Postt.com.model.Entity.Post;
import proyecto.Postt.com.model.Entity.User;

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
