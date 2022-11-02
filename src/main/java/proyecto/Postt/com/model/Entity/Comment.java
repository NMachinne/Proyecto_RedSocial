package proyecto.Postt.com.model.Entity;

import java.util.ArrayList;
import java.util.List;

public class Comment {
	protected String fecha;
	protected String txt;
	protected List<User> user;
	protected List<Post> post;

	public Comment(String fecha, String txt, List<User> user, List<Post> post) {

		this.fecha = fecha;
		this.txt = txt;
		this.user = user;
		this.post = post;
	}
	
	
	public Comment() {
		this("","",new ArrayList<User>(),new ArrayList<Post>());

	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getTxt() {
		return txt;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}

	public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}

	public List<Post> getPost() {
		return post;
	}

	public void setPost(List<Post> post) {
		this.post = post;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((post == null) ? 0 : post.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		if (post == null) {
			if (other.post != null)
				return false;
		} else if (!post.equals(other.post))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Comment [fecha=" + fecha + ", txt=" + txt + ", user=" + user + ", post=" + post + "]";
	}

}
