package proyecto.RedSocial.proyecto;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import proyecto.RedSocial.proyecto.model.DAO.CommentDAO;
import proyecto.RedSocial.proyecto.model.DAO.PostDAO;
import proyecto.RedSocial.proyecto.model.DAO.UserDAO;
import proyecto.RedSocial.proyecto.model.Entity.Comment;
import proyecto.RedSocial.proyecto.model.Entity.Post;
import proyecto.RedSocial.proyecto.model.Entity.User;

public class PostController extends AController implements Initializable, Runnable {

	@FXML
	private Text likes;

	@FXML
	private TextArea post_texto;

	@FXML
	private TextArea comment_texto;

	private static int action;

	private static PostController p;

	@FXML
	void banadir(ActionEvent event) throws IOException {
		// App.setRoot("");
	}

	/**
	 * Muestra el search.fxml
	 * 
	 * @param event Objeto ActionEvent
	 * @throws IOException
	 */
	@FXML
	void bbuscar(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("search.fxml"));
		Parent root1 = (Parent) fxmlLoader.load();
		Stage stage1 = new Stage();
		stage1.setResizable(false);
		stage1.initModality(Modality.APPLICATION_MODAL);
		stage1.setTitle("Buscador");
		stage1.setScene(new Scene(root1));
		stage1.show();
		action = 1;
		Thread t = new Thread(p);
		t.setDaemon(true);
		t.start();
	}

	/**
	 * Muestra el perfil del usuario
	 * 
	 * @param event Objeto ActionEvent
	 * @throws IOException
	 */
	@FXML
	void busuario(ActionEvent event) throws IOException {
		AController.user = AController.login_user;
		App.setRoot("user");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		post_texto.setText("Cargando Post...");
		comment_texto.setText("Cargando Comentarios...");
		action = 0;
		p = this;
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				Thread t = new Thread(p);
				t.setDaemon(true);
				t.start();
			}
		});
	}

	/**
	 * Metodo run que inicia el Objeto Thread
	 */
	@Override
	public void run() {
		if (action == 0) {
			while (true) {
				try {
					if (post != null) {
						post_texto.setText("Fecha: " + post.getFecha() + "\n\n" + post.getTxt());
						Collection<Comment> comment2 = null;
						comment2 = new CommentDAO().getById(new Comment("", post.getId() + "", new User(), new Post()));
						if (comment2.size() >= 1) {
							String comments = "";
							for (Comment c : comment2) {
								User user_comment = (User) new UserDAO()
										.getById(new User(c.getUser().getId(), null, null, null)).toArray()[0];
								comments += "Fecha: " + c.getFecha() + "\nUsuario: " + user_comment.getNombre() + "\n\n"
										+ c.getTxt() + "\n\n";
							}
							comment_texto.setText(comments);
						} else {
							comment_texto.setText("Sin Comentarios");
						}
						return;
					} else {
						Collection<User> follow = new UserDAO().getByFollow(login_user);
						int l = 0;
						Post post2 = null;
						for (User f : follow) {
							post2 = (Post) new PostDAO().getByIdUser(new Post(0, f.getNombre(), "", "")).toArray()[0];
							if (post2 != null && l < post2.getId()) {
								l = post2.getId();
							}
						}
						if (post2 != null) {
							post_texto.setText("Fecha: " + post2.getFecha() + "\n\n" + post2.getTxt());
							Collection<Comment> comment2 = null;
							comment2 = new CommentDAO()
									.getById(new Comment("", post2.getId() + "", new User(), new Post()));
							if (comment2.size() >= 1) {
								String comments = "";
								for (Comment c : comment2) {
									User user_comment = (User) new UserDAO()
											.getById(new User(c.getUser().getId(), null, null, null)).toArray()[0];
									comments += "Fecha: " + c.getFecha() + "\nUsuario: " + user_comment.getNombre()
											+ "\n\n" + c.getTxt() + "\n\n";
								}
								comment_texto.setText(comments);
							} else {
								comment_texto.setText("Sin Comentarios");
							}
							return;
						} else {
							post_texto.setText("Sin Post");
							comment_texto.setText("Sin Comentarios");
						}
					}
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} else if (action == 1) {
			while (true) {
				try {
					if (user != null) {
						try {
							App.setRoot("user");
							return;
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
