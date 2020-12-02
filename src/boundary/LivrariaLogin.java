package boundary;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LivrariaLogin extends Application implements EventHandler<ActionEvent> {

	private BorderPane bp = new BorderPane();
	private GridPane gp = new GridPane();
	
	private Button btnLogar = new Button("Acessar");
	
	private TextField tfLogin = new TextField();
	private PasswordField pfSenha = new PasswordField();
	
	private Label lblLogin = new Label("Usuário:");
	private Label lblSenha = new Label("Senha: ");
	
	private String typeUser = "";
	
	private SistemaPrincipal sp = new SistemaPrincipal();
	
	private Scene scn;
	
	@Override
	public void start(Stage stage) throws Exception {
		
		scn = new Scene(bp, 1600, 850);
		
		gp.setHgap(20);
		gp.setVgap(20);
		
		bp.setCenter(gp);
		
		gp.add(new Label("Login"), 26, 18);
		gp.add(lblLogin, 25, 20);
		gp.add(tfLogin, 26, 20);
		gp.add(lblSenha, 25, 22);
		gp.add(pfSenha, 26, 22);
		gp.add(btnLogar, 26, 25);
		
		btnLogar.setOnAction(this);
		
		bp.getStyleClass().add("border-pane");
		btnLogar.setMinWidth(250.0);
		scn.getStylesheets().add("/boundary/css/DarkTheme.css");
		
		stage.getIcons().add(new Image(getClass().getResourceAsStream("/boundary/images/icon.png")));
		stage.setScene(scn);
		stage.setTitle("Livraria");
		stage.show();
	}
	
	@Override
	public void handle(ActionEvent e) {
		if (e.getTarget() == btnLogar) {
			executarComando("logar");
		}
	}
	
	public static void main(String[] args) {
		Application.launch(LivrariaLogin.class, args);
	}
	
	public void executarComando(String cmd) {
		
		if ("logar".equals(cmd)) {
			typeUser = verificarLogin();
			if (loginExist(typeUser)) {
				bp.setCenter(sp.gerarTela(typeUser));
			}
		}
	}

	private String verificarLogin() {
		
		if (("".equals(tfLogin.getText()) || ("".equals(pfSenha.getText())))) {
			JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos", "ERRO", JOptionPane.WARNING_MESSAGE);
		
		
		} else if ("user".equals(tfLogin.getText()) && !("user@123".equals(pfSenha.getText()))) {
			JOptionPane.showMessageDialog(null, "Senha incorreta", "ERRO", JOptionPane.ERROR_MESSAGE);
		} else if ("user".equals(tfLogin.getText()) && "user@123".equals(pfSenha.getText())) {
			typeUser = "user";
		
		
		} else if ("admin".equals(tfLogin.getText()) && !("admin@123".equals(pfSenha.getText()))) {
			JOptionPane.showMessageDialog(null, "Senha incorreta", "ERRO", JOptionPane.ERROR_MESSAGE);
		} else if ("admin".equals(tfLogin.getText()) && "admin@123".equals(pfSenha.getText())) {
			typeUser = "admin";
		
		
		} else {
			JOptionPane.showMessageDialog(null, "Login não existe", "ERRO", JOptionPane.ERROR_MESSAGE);
		}
		
		return typeUser;
	}
	
	private boolean loginExist(String typeUser) {
		return "admin".equals(typeUser) || "user".equals(typeUser);
	}
}
