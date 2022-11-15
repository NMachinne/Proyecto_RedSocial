package proyecto.RedSocial.proyecto;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UserController implements Initializable {

	// variables para el perfil del usuario
	@FXML
	private Button delPost;

	@FXML
	private Button editPerfil;

	@FXML
	private Text getNameUser;

	@FXML
	private ImageView imgUser;

	@FXML
	private Text nFollowed;

	@FXML
	private Text nFollower1;

	@FXML
	private Text nPost;

	@FXML
	private GridPane postGrid;
	@FXML
	private ScrollPane userPosts;
	// private List<Post> posts; //para post

	// variables de la lista de seguidos/seguidores
	@FXML
	private ScrollPane followUser;

	@FXML
	private Text getnameFollow;

	// variables de seguidores
	@FXML
	private Button btnUnfollow;

	@FXML
	private ImageView imgfollowuser;

	@FXML
	private Text nameFolllowUser;

	/**
	 * accede a la seccion de seguidos
	 * 
	 * @param event
	 */
	@FXML
	void openFollowed(MouseEvent event) {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("follow-ed.fxml"));
		getnameFollow.setText("FOLLOWER");

		try {
			App.setRoot("follow-ed");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * accede a la seccion de seguidores
	 * 
	 * @param event
	 */
	@FXML
	void openFollower(MouseEvent event) {
		
		
		try {			
			App.setRoot("follow-ed");
			Thread.sleep(2000);
			getnameFollow.setText("FOLLOWER");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * permite volver al perfil del usuario
	 * @param event
	 */
	 @FXML
	    void backtoPerfil(MouseEvent event) {
		 try {
				App.setRoot("user");
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	/**
	 * Edita el perfil del propio usuario
	 * 
	 * @param event
	 */
	@FXML
	void PerfilEdit(ActionEvent event) {
		delPost.setVisible(true);
		imgUser.setVisible(true);
		delPost.setDisable(false);
		imgUser.setDisable(false);
	}

	/**
	 * 
	 * @param event
	 */
	@FXML
	void selectedPostDeleted(ActionEvent event) {

	}

	/**
	 * Permite editar los post del usuario
	 * 
	 * @param event
	 */
	@FXML
	void selectedPostEdit(ActionEvent event) {

	}

	/**
	 * Cambia la imagen del Avatar del usuario
	 * 
	 * @param event
	 */
	@FXML
	void imageChange(MouseEvent event) {

	}

	/**
	 * Deja de seguir al usuario
	 * 
	 * @param event
	 */
	@FXML
	void unfollowUser(ActionEvent event) {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	/*
	 * 
	 * 
	 * @Override public void initialize(URL location, ResourceBundle resources) {
	 * posts = new ArrayList<>(data());
	 * 
	 * int columns =0; int rows = 1; try { for (int i = 0; i < posts.size; i++) {
	 * FXMLLoader fxmloader = new FXMLLoader();
	 * fxmloader.setLocation(getClass().getResource("post.fxml"));
	 * 
	 * VBox postbox = fxmloader.load();
	 * 
	 * postController postController = fxmloader.getController();
	 * postController.setData(posts.get(i));
	 * 
	 * if (columns ==3) { columns =0; ++rows; } }
	 * 
	 * 
	 * postGrid.add(postBox, columns++, rows); GridPane.setMargin(postBox, new
	 * Insets(topRightBottomLeft: 10)); } catch (IOException e) {
	 * e.printStackTrace(); } }
	 * 
	 * private List<Post> UserPosts(){ List<Post> lP= new ArrayList<>(); Post pst =
	 * new Post(); pst.setProfileImageSrc("/img/locaciozaicondeimages");
	 * 
	 * }
	 */
}
