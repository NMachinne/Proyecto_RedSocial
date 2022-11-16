package proyecto.RedSocial.proyecto;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class LoginController {

    @FXML
    private Button createAcoount;

    @FXML
    private PasswordField getpassword;

    @FXML
    private TextField getusername;

    @FXML
    private ImageView photo;

    @FXML
    private Text pswrd;

    @FXML
    private Text recpasswrd;

    @FXML
    private Button sigin;

    @FXML
    private Text username;

    @FXML
    void createaccount(ActionEvent event) {
    	

    }

    @FXML
    void recoverpasswd(MouseEvent event) {

    }

    @FXML
    void signinAplication(ActionEvent event) {
    	
    }
    
    
    @FXML
    void alertAcount() {
    	Alert al = new Alert(Alert.AlertType.CONFIRMATION);
    	al.setHeaderText("Usuario no encontrado");
    	al.setTitle("CREAR NUEVO USUARIO?");
    	al.showAndWait();
    }

}
