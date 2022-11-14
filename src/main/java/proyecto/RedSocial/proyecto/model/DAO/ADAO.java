package proyecto.RedSocial.proyecto.model.DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import proyecto.RedSocial.proyecto.model.Conection.MariaDBConnection;
import proyecto.RedSocial.proyecto.model.Entity.Comment;
import proyecto.RedSocial.proyecto.model.Entity.Post;
import proyecto.RedSocial.proyecto.model.Entity.User;


public abstract class ADAO<T> {
	
	/**
	 * Numero de fila de database
	 */
	private static final int row = 6;
	
	/**
	 * Base de datos con los campos de cada Objeto: User, Post o Comment
	 */
	private static final String[] database = {
			User.class.toString(), null    , Post.class.toString(), null    , Comment.class.toString(), null     ,
			"id"                 ,"int"    , "id"                 ,"int"    , "id_usuario"            , "int"    ,
			"nombre"             ,"string" , "fecha"              ,"string" , "id_publicacion"        , "int"    ,
			"password"           ,"string" , "texto"              ,"string" , "fecha"                 , "string" ,
			"avatar"             ,"string" , "multimedia"         ,"string" , "texto"                 , "string" ,
			null                 , null    , "id_usuario"         ,"int"    , null                    , null     ,
			null                 , null    , null                 , null    , null                    , null     ,
	};
	
	
	/**
	 * Guarda los datos de un objeto de tipo generico en una lista de objetos
	 * @param i Posicion de la primera fila en database
	 * @param obj Objeto de tipo generico que puede ser solo de tipo: User, Post o Comment
	 * @return Lista de Objetos
	 */
	private List<Object> tObject(int i, T obj) {
		List<Object> o = new ArrayList<Object>();
		switch(i) {
			case 0:
				User u = (User)obj;
				o.add((Integer)u.getId());
				o.add((String)u.getNombre());
				o.add((String)u.getPassword());
				o.add((String)u.getAvatar());
				break;
			case 2:
				Post p = (Post)obj;
				o.add((Integer)p.getId());
				o.add((String)p.getFecha());
				o.add((String)p.getTxt());
				o.add((String)p.getMultimedia());
				break;
			case 4:
				Comment c = (Comment)obj;
				o.add((String)c.getFecha());
				o.add((String)c.getTxt());
				break;
		}
		return o;
	}
	
	
	/**
	 * Realiza modificaciones en cualquier tabla de la base de datos
	 * @param obj Objeto de tipo generico que puede ser solo de tipo: User, Post o Comment
	 * @param sql Sentencia SQL que permite realizar: INSERT, UPDATE y DELETE
	 * @param field Campos que pertenecen a database
	 * @return Devuelve true si la sentencia SQL se ha completado
	 * @throws SQLException Devuelve una excepcion si hay algun error
	 */
	public boolean create(T obj, String sql, Integer[] field) throws SQLException {
		int i = 0;
		String clase = "";
		while (i < row) {
			clase = database[i];
			if (clase != null && obj.getClass().toString().equals(clase)) {
				List<Object> o = tObject(i, obj);
				
				i += row;
				
				Connection conn = MariaDBConnection.getConnection();
				PreparedStatement ps;
				ps = conn.prepareStatement(sql);
				
				int v = 0;
				int s = 0;
				while (v < sql.length()) {
					if (sql.charAt(v) == '?') {
						s++;
					}
					v++;
				}
				int p = 0;
				v = 0;
				List<Integer> field2 = new ArrayList<Integer>();
				while (v < field.length) {
					field2.add((field[v]*row));
					v++;
				}
				v = 1;
				while (v <= s) {
					if (field[p] != null && database[(i+field2.get(p)+1)] != null && database[(i+field2.get(p)+1)].toString().equals("int")) {
						ps.setInt(v, (Integer)o.get(field[p]));
					}
					else if (field[p] != null && database[(i+field2.get(p)+1)] != null && database[(i+field2.get(p)+1)].toString().equals("string")) {
						try {
							ps.setString(v, o.get(field[p]).toString());
						}
						catch(NullPointerException e) {
							ps.setString(v, null);
						}
					}
					if (field[p] != null) {
						v++;
					}
					p++;
				}
				ps.executeUpdate();
				ps.close();
				//conn.close();
				return true;
			}
			i++;
		}
		return false;
	}
	
	
	/**
	 * Realiza consultas en cualquier tabla de la base de datos
	 * @param obj Objeto de tipo generico que puede ser solo de tipo: User, Post o Comment
	 * @param sql Sentencia SQL que permite realizar SELECT
	 * @param field Campos que pertenecen a database
	 * @return Devuelve true si la sentencia SQL se ha completado
	 * @throws SQLException Devuelve una excepcion si hay algun error
	 */
	public Collection<T> read(T obj, String sql, Integer[] field) throws SQLException {
		int i = 0;
		String clase = "";
		while (i < row) {
			clase = database[i];
			if (clase != null && obj.getClass().toString().equals(clase)) {
				List<Object> o = tObject(i, obj);
				
				int aux = i;
				i += row;
				
				Connection conn = MariaDBConnection.getConnection();
				PreparedStatement ps;
				ps = conn.prepareStatement(sql);
				
				int v = 0;
				int s = 0;
				while (v < sql.length()) {
					if (sql.charAt(v) == '?') {
						s++;
					}
					v++;
				}
				int p = 0;
				v = 1;
				if (field == null) {
					while (database[i] != null && v <= s) {
						if (database[i+1].toString().equals("int")) {
							ps.setInt(v, (Integer)o.get(p));
						}
						else if (database[i+1].toString().equals("string")) {
							try {
								ps.setString(v, o.get(field[p]).toString());
							}
							catch(NullPointerException e) {
								ps.setString(v, null);
							}
						}
						p++;
						v++;
						i += row;
					}
				}
				else {
					v = 0;
					List<Integer> field2 = new ArrayList<Integer>();
					while (v < field.length) {
						field2.add((field[v]*row));
						v++;
					}
					v = 1;
					while (v <= s) {
						if (field[p] != null && database[(i+field2.get(p)+1)] != null && database[(i+field2.get(p)+1)].toString().equals("int")) {
							ps.setInt(v, (Integer)o.get(field[p]));
						}
						else if (field[p] != null && database[(i+field2.get(p)+1)] != null && database[(i+field2.get(p)+1)].toString().equals("string")) {
							try {
								ps.setString(v, o.get(field[p]).toString());
							}
							catch(NullPointerException e) {
								ps.setString(v, null);
							}
						}
						if (field[p] != null) {
							v++;
						}
						p++;
					}
				}
				Collection<T> result = new ArrayList<T>();
				ResultSet rs = ps.executeQuery();
				
				while (rs.next()) {
					v = 1;
					i = (aux + row);
					switch(aux) {
						case 0:
							User u = new User();
							while (database[i] != null) {
								if (database[i].toString().equals("id")) {
									u.setId(rs.getInt(v));
								}
								else if (database[i].toString().equals("nombre")) {
									u.setNombre(rs.getString(v));
								}
								else if (database[i].toString().equals("password")) {
									u.setPassword(rs.getString(v));
								}
								else if (database[i].toString().equals("avatar")) {
									u.setAvatar(rs.getString(v));
								}
								v++;
								i += row;
							}
							result.add((T)u);
							break;
						case 2:
							Post po = new Post();
							while (database[i] != null) {
								if (database[i].toString().equals("id")) {
									po.setId(rs.getInt(v));
								}
								else if (database[i].toString().equals("fecha")) {
									po.setFecha(rs.getString(v));
								}
								else if (database[i].toString().equals("texto")) {
									po.setTxt(rs.getString(v));
								}
								else if (database[i].toString().equals("multimedia")) {
									po.setMultimedia(rs.getString(v));
								}
								v++;
								i += row;
							}
							result.add((T)po);
							break;
						case 4:
							Comment c = new Comment();
							while (database[i] != null) {
								if (database[i].toString().equals("fecha")) {
									c.setFecha(rs.getString(v));
								}
								else if (database[i].toString().equals("texto")) {
									c.setTxt(rs.getString(v));
								}
								v++;
								i += row;
							}
							result.add((T)c);
							break;
					}
					
				}
				ps.close();
				//conn.close();
				return result;
			}
			i++;
		}
		return null;
	}
	
	
	/**
	 * Realiza modificaciones en cualquier tabla de la base de datos
	 * @param obj Objeto de tipo generico que puede ser solo de tipo: User, Post o Comment
	 * @param sql Sentencia SQL que permite realizar: INSERT, UPDATE y DELETE
	 * @param field Campos que pertenecen a database
	 * @return Devuelve true si la sentencia SQL se ha completado
	 * @throws SQLException Devuelve una excepcion si hay algun error
	 */
	public boolean update(T obj, String sql, Integer[] field) throws SQLException {
		return this.create(obj, sql, field);
	}
	
	
	/**
	 * Realiza modificaciones en cualquier tabla de la base de datos
	 * @param obj Objeto de tipo generico que puede ser solo de tipo: User, Post o Comment
	 * @param sql Sentencia SQL que permite realizar: INSERT, UPDATE y DELETE
	 * @param field Campos que pertenecen a database
	 * @return Devuelve true si la sentencia SQL se ha completado
	 * @throws SQLException Devuelve una excepcion si hay algun error
	 */
	public boolean delete(T obj, String sql, Integer[] field) throws SQLException {
		return this.create(obj, sql, field);
	}
	
}
