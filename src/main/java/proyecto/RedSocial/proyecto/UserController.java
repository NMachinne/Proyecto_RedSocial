package proyecto.RedSocial.proyecto;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Platform;
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

public class UserController extends AController implements Initializable,Runnable {

	// variables para el perfil del usuario con el archivo user.fxml
	@FXML
	private Button delPost;

	@FXML
	private Button followuser;

	@FXML
	private Button editPerfil;

	@FXML
	private Text getNameUser;

	@FXML
	private ImageView imgUser;

	@FXML
	private Text nFollowed;

	@FXML
	private Text namefollowed;

	@FXML
	private Text namefollower;
	@FXML
	private Text nFollower;

	@FXML
	private Text nPost;
	@FXML
	private Text idnameFollowed;

	@FXML
	private Text idnameFollower;

	@FXML
	private GridPane postGrid;
	@FXML
	private ScrollPane userPosts;
	
	private static int action;
    
    private static UserController u;
	PostDAO pd = new PostDAO();
	UserDAO ud = new UserDAO(); 

	/**
	 * permite seguir al usuario que se muestra
	 * 
	 * @param event
	 */
	@FXML
	void follow(ActionEvent event) {
		
		//try {
		//	if (new UserDAO().getByFollowById(new User(login_user.getId(),user.getId()+"","","")).toArray()[0] != null) {
		//		ud.deleteFollow(new User(login_user.getId(),user.getId()+"","",""));
		//		followuser.setText("SEGUIR");
				//System.out.println(followuser.getText().equals("SIGUIENDO"));
		//	} 

		//} catch (Exception e) {
		//	ud.insertFollow(new User(login_user.getId(),user.getId()+"","",""));
		//	followuser.setText("SIGUIENDO");
		//}
		boolean follow = false;
		try {
			if (new UserDAO().getByFollowById(new User(login_user.getId(),user.getId()+"","","")).toArray()[0] != null);
			follow = true;
		}
		catch (Exception e) {
			follow = false;
		}
		if (follow) {
			ud.deleteFollow(new User(login_user.getId(),user.getId()+"","",""));
		}
		else {
			ud.insertFollow(new User(login_user.getId(),user.getId()+"","",""));
		}	
		
		
	}

	/**
	 * accede a la seccion de seguidos
	 * 
	 * @param event
	 */
	@FXML
	void openFollowed(MouseEvent event) {

		try {
			nameFollow = idnameFollowed.getText();
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
			nameFollow = idnameFollower.getText();
			App.setRoot("follow-ed");
		} catch (IOException e) {
			System.out.println(e);
		}

	}

	/**
	 * vuelve al menu principal del usuario
	 * 
	 * @param event
	 */
	@FXML
	void backtoMenu(MouseEvent event) {
		try {
			user = null;
			App.setRoot("post");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

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

	/**
	 * Edita el perfil del propio usuario
	 * 
	 * @param event
	 */
	@FXML
	void perfilEdit(ActionEvent event) {

	
		if (!delPost.isVisible()) {
			delPost.setVisible(true);
			imgUser.setVisible(true);
			delPost.setDisable(false);
			imgUser.setDisable(false);
		} else {
			delPost.setVisible(false);
			imgUser.setVisible(false);
			delPost.setDisable(true);
			imgUser.setDisable(true);
		}

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
		if (login_user.getId() == user.getId()) {
			editPerfil.toFront();
			editPerfil.setVisible(true);
			
		} else {
			followuser.toFront();
			followuser.setVisible(true);
			
		}
		
		action = 0;
		u = this;
		getNameUser.setText(user.getNombre());
		nPost.setText(pd.getAllByIdUser(new Post(user.getId(), "", "", "")).size() + "");
		nFollowed.setText(ud.getByFollowed(user).size()+"");
		nFollower.setText(ud.getByFollow(user).size()+"");
		pd.getByIdUser(new Post(0,user.getId()+"","",""));
		Platform.runLater(new Runnable() {
		    @Override
		    public void run() {
		    	Thread t = new Thread(u);
		    	t.setDaemon(true);
		    	t.start();
		    }
		});
		
	}
	/* *
	 * 
	 
	public void loadUserPost() {
		int columns =0;
		int rows = 1;
		  try {
			  for (int i = 0; i < posts.size(); i++) {
				  	FXMLLoader fxmloader = new FXMLLoader();
				  	fxmloader.setLocation(getClass().getResource("post.fxml"));
		 
		  VBox postbox = fxmloader.load();
		  
		  PostController pc = fxmloader.getController();
		  pc.(posts.get(i));		  
		  if (columns ==3) { 
			  columns =0;
			  ++rows; 
			  } 
		  }	  
		  postGrid.add(postbox, columns++, rows);
		  GridPane.setMargin(postbox, new Insets(10));
		  }catch (IOException e){
			  e.printStackTrace(); 
		  } 
	}
	 */

	@Override
	public void run() {
		User uvar= user;
		if (action ==0) {
			
			try {
				while (true) {
					if ( user != null) {
	        			try {
							Thread.sleep(1000);
							try {
								nPost.setText(pd.getAllByIdUser(new Post(uvar.getId(), "", "", "")).size() + "");
								
								nFollowed.setText(ud.getByFollowed(uvar).size()+"");
								nFollower.setText(ud.getByFollow(uvar).size()+"");
							} catch (Exception e) {
								e.printStackTrace();
							}
							boolean follow = false;
	        				try {
	        					if (new UserDAO().getByFollowById(new User(login_user.getId(),user.getId()+"","","")).toArray()[0] != null);
	        					follow = true;
	        				}
	        				catch (Exception e) {
	        					follow = false;
	        				}
	        				if (follow) {
	        					followuser.setText("SIGUIENDO");
	        				}
	        				else {
	        					followuser.setText("SEGUIR");
	        				}
	        				
							Thread.sleep(1000);
							
						} catch (InterruptedException e) {
							
							e.printStackTrace();
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
				
			
		} else if (action ==1) {
			
		}
				
	}
}
