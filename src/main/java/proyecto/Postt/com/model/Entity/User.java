package proyecto.Postt.com.model.Entity;

import java.util.*;

import proyecto.Postt.com.Interfaces.IUser;

public class User implements IUser {
	protected int id;
	protected String nombre;
	protected String password;
	protected String avatar;
	protected List<Post> post;
	protected List<Comment> comment;
	protected List<Post> likes;
	protected List<User> followed;
	protected List<User> follower;

	
	public User(int id, String nombre, String password, String avatar, List<Post> post, List<Comment> comment,
			List<Post> likes, List<User> followed, List<User> follower) {
		this.id = id;
		this.nombre = nombre;
		this.password = password;
		this.avatar = avatar;
		this.post = post;
		this.comment = comment;
		this.likes = likes;
		this.followed = followed;
		this.follower = follower;
	}
	
	public User() {
		this(-1,"","","",new ArrayList<Post>(),new ArrayList<Comment>(),new ArrayList<Post>(),new ArrayList<User>(),new ArrayList<User>());
	}
	
	public User(int id, String nombre, String password, String avatar) {
		this.id = id;
		this.nombre = nombre;
		this.password = password;
		this.avatar = avatar;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public List<Post> getPost() {
		return post;
	}

	public void setPost(List<Post> post) {
		this.post = post;
	}

	public List<Comment> getComment() {
		return comment;
	}

	public void setComment(List<Comment> comment) {
		this.comment = comment;
	}

	public List<Post> getLikes() {
		return likes;
	}

	public void setLikes(List<Post> likes) {
		this.likes = likes;
	}

	public List<User> getFollowed() {
		return followed;
	}

	public void setFollowed(List<User> followed) {
		this.followed = followed;
	}

	public List<User> getFollower() {
		return follower;
	}

	public void setFollower(List<User> follower) {
		this.follower = follower;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", nombre=" + nombre + ", password=" + password + ", avatar=" + avatar + ", post="
				+ post + ", comment=" + comment + ", likes=" + likes + ", followed=" + followed + ", follower="
				+ follower + "]";
	}

}
