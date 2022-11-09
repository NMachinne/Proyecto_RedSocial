package proyecto.Postt.com.Interfaces;

import java.util.List;

import proyecto.Postt.com.model.Entity.Post;
import proyecto.Postt.com.model.Entity.User;
import proyecto.Postt.com.model.Entity.Comment;

public interface IUser {
		public int getId();
		public void setId(int id);
		public String getNombre();
		public void setNombre(String nombre);
		public String getPassword();
		public void setPassword(String password);
		public String getAvatar();
		public void setAvatar(String avatar);
		public List<Post> getPost();
		public void setPost(List<Post> post);
		public List<Comment> getComment();
		public void setComment(List<Comment> comment);	
		public List<Post> getLikes();
		public void setLikes(List<Post> likes);	
		public List<User> getFollowed();
		public void setFollowed(List<User> followed);	
		public List<User> getFollower();
		public void setFollower(List<User> follower);
		
}
