package proyecto.RedSocial.proyecto;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.Base64;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import proyecto.RedSocial.proyecto.model.DAO.CommentDAO;
import proyecto.RedSocial.proyecto.model.DAO.PostDAO;
import proyecto.RedSocial.proyecto.model.DAO.UserDAO;
import proyecto.RedSocial.proyecto.model.Entity.Comment;
import proyecto.RedSocial.proyecto.model.Entity.Post;
import proyecto.RedSocial.proyecto.model.Entity.User;

public class UserPostController extends AController implements Initializable {

	  @FXML
	    private Text comments;

	    @FXML
	    private ImageView iconComment;

	    @FXML
	    private ImageView iconLike;

	    @FXML
	    private ImageView imgfollowuser;

	    @FXML
	    private Text likes;

	    @FXML
	    private Text nameFolllowUser;

	    @FXML
	    private TextArea textComment;

	    
	    UserDAO ps= new UserDAO();
	    CommentDAO cd = new CommentDAO();

	public void setData(Post post) {
		//new ByteArrayInputStream(Base64.getDecoder().decode(post.getMultimedia()));
		//Image img = new Image(getClass().getResourceAsStream(UserPostController.user.getAvatar()));	
		//imgfollowuser.setImage(img);
		
		textComment.setText(UserPostController.post.getFecha()+"\n\n" +UserPostController.post.getTxt());
		likes.setText(ps.getByLikePost(new User(user.getId(),"","","")).size()+"");
		comments.setText(cd.getById(new Comment("","",new User(), new Post(post.getId(),"","",""))).size()+ "");
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
