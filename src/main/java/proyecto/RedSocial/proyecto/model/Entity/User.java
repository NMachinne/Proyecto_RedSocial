package proyecto.RedSocial.proyecto.model.Entity;

import proyecto.RedSocial.proyecto.Interfaces.IUser;

public class User implements IUser {
	protected int id;
	protected String nombre;
	protected String password;
	protected String avatar;
	protected Post post;
	protected Comment comment;
	protected Post likes;
	protected User followed;
	protected User follower;

	
	public User(int id, String nombre, String password, String avatar, Post post, Comment comment,
			Post likes, User followed, User follower) {
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
		this(-1,"","","",null,null,null,null,null);
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
	
	

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public Post getLikes() {
		return likes;
	}

	public void setLikes(Post likes) {
		this.likes = likes;
	}

	public User getFollowed() {
		return followed;
	}

	public void setFollowed(User followed) {
		this.followed = followed;
	}

	public User getFollower() {
		return follower;
	}

	public void setFollower(User follower) {
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
