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

public class ItemUserController implements Initializable {

	// variables de seguidores con el archivo itemUser.fxml
    @FXML
    private Button btnUnfollow;

    @FXML
    private ImageView imgfollowuser;

    @FXML
    private Text nameFolllowUser;

    @FXML
    void unfollowUser(ActionEvent event) {

    }

	public void setData(User user) {
		Image img = new Image(getClass().getResourceAsStream(user.getAvatar()));
		imgfollowuser.setImage(img);
		nameFolllowUser.setText(user.getNombre());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
