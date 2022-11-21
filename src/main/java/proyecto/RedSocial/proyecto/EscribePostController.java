package proyecto.RedSocial.proyecto;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import proyecto.RedSocial.proyecto.model.DAO.PostDAO;
import proyecto.RedSocial.proyecto.model.Entity.Post;
import proyecto.RedSocial.proyecto.model.Entity.User;

public class EscribePostController  extends AController{
	@FXML
	private Button SubeImagenes;

	@FXML
	private Button SubePost;

	@FXML
	private TextArea texto;

	@FXML
	private Button Cancelar;
	@FXML
	private Label ruta;
	
	static Post p1 = new Post();
	static PostDAO ps = new PostDAO();
	@FXML
	void multiFileChooser(ActionEvent event) {
		// LoginController lg=new LoginController();
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().add(new ExtensionFilter("PDF Files", "*.pdf", ".jpg", "*.*"));
		List<File> f = fc.showOpenMultipleDialog(null);
		
		String nam = "";
		String imagen = "";
		for (File file : f) {
			nam = file.getAbsolutePath();
			//imagen = LoginController.encrypt(file.getAbsolutePath());
			imagen=Base64.getEncoder().encodeToString(file.getAbsolutePath().getBytes());
		}

		ruta.setText(nam);
		p1.setLikes(new User(login_user.getId(), "", "", ""));
		LocalDateTime locdate = LocalDateTime.now().withNano(0);
		p1.setFecha(locdate+"");
		p1.setMultimedia(imagen);
	//	ps.save(p1);
	}
	@FXML
	void sube(ActionEvent event) {
		String text=texto.getText();
		p1.setTxt(text);
		ps.save(p1);
		texto.setText("");
	}
	@FXML
	void cancelar(ActionEvent event) {
		try {
			App.setRoot("post");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
