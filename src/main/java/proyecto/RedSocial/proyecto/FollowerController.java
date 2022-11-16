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
import proyecto.RedSocial.proyecto.model.Entity.User;
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
		List<User> useres = new ArrayList<>(useres());
		for (int i = 0; i < useres.size(); i++) {
			FXMLLoader fxmloader = new FXMLLoader();
			fxmloader.setLocation(getClass().getResource("itemUser.fxml"));

			try {
				HBox apane = fxmloader.load();
				ItemUserController uc = fxmloader.getController();
				uc.setData(useres.get(i));
				Vboxlayout.getChildren().add(apane);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	
	private List<User> useres() {
		List<User> ls = new ArrayList<>();
		User user = new User();

		user.setNombre("zoiberg");
		user.setAvatar("/img/zoiberg.png");
		ls.add(user);

		user = new User();
		user.setNombre("leela");
		user.setAvatar("/img/leela.png");
		ls.add(user);

		user = new User();
		user.setNombre("fry");
		user.setAvatar("/img/fry.png");
		ls.add(user);

		user = new User();
		user.setNombre("bender");
		user.setAvatar("/img/bender.png");
		ls.add(user);

		return ls;
	}

}
