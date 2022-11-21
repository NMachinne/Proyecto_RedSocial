package proyecto.RedSocial.proyecto;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import proyecto.RedSocial.proyecto.model.Entity.Post;
import proyecto.RedSocial.proyecto.model.Entity.User;
import proyecto.RedSocial.proyecto.model.DAO.PostDAO;
import proyecto.RedSocial.proyecto.model.DAO.UserDAO;

public class FollowerController extends AController implements Initializable {

	// variables de la lista de seguidos/seguidores con el archivo follow-ed.fxml
	@FXML
    private VBox Vboxlayout;

	@FXML
	private ScrollPane followUser;

	@FXML
	private Text getnameFollow;
	

	/**
	 * permite volver al perfil del usuario
	 * 
	 * @param event
	 */
	@FXML
	void backtoPerfil(MouseEvent event) {
		try {
			App.setRoot("user");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		getnameFollow.setText(nameFollow);
		if (nameFollow.equals("Follower")) {
			loadFollower();
		} else {
			loadFollowed();
		}

	}
	
	public void loadFollowed() {
		Collection<User> useres = new UserDAO().getByFollow(new User(user.getId(), "", "", ""));
		User uaux= new User();
		System.out.println(useres);
		for (int i = 0; i < useres.size(); i++) {
			FXMLLoader fxmloader = new FXMLLoader();
			fxmloader.setLocation(getClass().getResource("itemUser.fxml"));	
			try {
				HBox apane = fxmloader.load();
				ItemUserController uc = fxmloader.getController();
				uaux =((User)useres.toArray()[i]);	
				uc.setData((User)new UserDAO().getByIdUser(new User(0,uaux.getNombre(),"","")).toArray()[0]);
				Vboxlayout.getChildren().add(apane);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void loadFollower() {
		Collection<User> useres = new UserDAO().getByFollowed(new User(user.getId(), "", "", ""));
		User uaux= new User();
		System.out.println(useres);
		for (int i = 0; i < useres.size(); i++) {
			FXMLLoader fxmloader = new FXMLLoader();
			fxmloader.setLocation(getClass().getResource("itemUser.fxml"));	
			try {
				HBox apane = fxmloader.load();
				ItemUserController uc = fxmloader.getController();
				uaux =((User)useres.toArray()[i]);
				uc.setData((User)new UserDAO().getById(new User(uaux.getId(),"","","")).toArray()[0]);
				Vboxlayout.getChildren().add(apane);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
