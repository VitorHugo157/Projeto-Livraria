package boundary;

import java.io.InputStream;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LivrariaLogin extends Application implements EventHandler<ActionEvent> {
	
	private BorderPane bp = new BorderPane();
	private GridPane gp = new GridPane();
	
	private Button btnLogar = new Button("Acessar");
	
	private TextField tfUser = new TextField();
	private PasswordField pfPass = new PasswordField();
	
	private Label lblUser = new Label("Username");
	private Label lblPass = new Label("Password");
	private Label lblWelcome = new Label("Sistema de Gerenciamento de Dados da LibMaster");
	
	private String typeUser = "";
	
	private SistemaPrincipal sp = new SistemaPrincipal();
	
	private Scene scn;
	
	@Override
	public void start(Stage stage) throws Exception {
		
		FlowPane fpUser = new FlowPane();
		FlowPane fpPass = new FlowPane();
		FlowPane fpLogin = new FlowPane();
		fpLogin.setHgap(5);
		fpUser.setHgap(5);
		fpPass.setHgap(5);
		fpLogin.getChildren().addAll(new Label("                    "), btnLogar);
		fpUser.getChildren().addAll(new Label("     "), lblUser, tfUser);
		fpPass.getChildren().addAll(new Label("     "), lblPass, pfPass);
		
		Group grp = new Group();
		InputStream is = getClass().getResourceAsStream("/boundary/images/login.jpg");
		Image img = new Image(is);
		Image img2 = new Image(getClass().getResourceAsStream("/boundary/images/login2.png"));
		Canvas canvas = new Canvas(1650, 850);
		GraphicsContext ctx = canvas.getGraphicsContext2D();
		ctx.drawImage(img, -50, 0);
		ctx.drawImage(img2, 1150, 150);
		grp.getChildren().addAll(canvas, gp);
				
		scn = new Scene(bp, 1650, 850);
		
		
		gp.setHgap(5);
		gp.setVgap(15);
		bp.setCenter(grp);
		gp.add(lblWelcome, 40, 15);
		gp.add(fpUser, 62, 17);
		gp.add(fpPass, 62, 19);
		gp.add(fpLogin, 62, 23);
		
		btnLogar.setOnAction(this);
		tfUser.setOnAction(this);
		pfPass.setOnAction(this);
		tfUser.setId("tfUser");
		pfPass.setId("pfPass");
		
		btnLogar.setMinWidth(250.0);
		bp.getStyleClass().add("border-pane");
		lblWelcome.getStyleClass().add("label-welcome");
		scn.getStylesheets().add("/boundary/css/DarkTheme.css");
		
		stage.getIcons().add(new Image(getClass().getResourceAsStream("/boundary/images/icon.png")));
		stage.setScene(scn);
		stage.setTitle("Livraria");
		stage.show();
	}
	
	@Override
	public void handle(ActionEvent e) {
		String cmd = e.getSource().toString();
//		System.out.println(cmd);
		if (e.getTarget() == btnLogar || cmd.contains("pfPass") || cmd.contains("tfUser")) {
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
		
		if (("".equals(tfUser.getText()) || ("".equals(pfPass.getText())))) {
			JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos", "ERRO", JOptionPane.WARNING_MESSAGE);
		
		
		} else if ("user".equals(tfUser.getText()) && !("user@123".equals(pfPass.getText()))) {
			JOptionPane.showMessageDialog(null, "Senha incorreta", "ERRO", JOptionPane.ERROR_MESSAGE);
		} else if ("user".equals(tfUser.getText()) && "user@123".equals(pfPass.getText())) {
			typeUser = "user";
		
		
		} else if ("admin".equals(tfUser.getText()) && !("admin@123".equals(pfPass.getText()))) {
			JOptionPane.showMessageDialog(null, "Senha incorreta", "ERRO", JOptionPane.ERROR_MESSAGE);
		} else if ("admin".equals(tfUser.getText()) && "admin@123".equals(pfPass.getText())) {
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