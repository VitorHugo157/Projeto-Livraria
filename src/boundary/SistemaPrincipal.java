package boundary;

import java.io.InputStream;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class SistemaPrincipal implements EventHandler<ActionEvent>,
									        IGerarTelaMain {
	
	private BorderPane panePrincipal = new BorderPane();
	private GridPane paneTxt = new GridPane();
	
	private Label lblBemVindo = new Label();
	
	private LivroBoundary livroBoundary = new LivroBoundary();
	private AutorBoundary autorBoundary = new AutorBoundary();
	private EditoraBoundary editoraBoundary = new EditoraBoundary();
	private EdicaoBoundary edicaoBoundary = new EdicaoBoundary();
	private ClienteBoundary clienteBoundary = new ClienteBoundary();
	private ClienteLivroBoundary clBoundary = new ClienteLivroBoundary();
	private LivroAutorBoundary laBoundary = new LivroAutorBoundary();
	private EdicaoEditoraLivroBoundary eelBoundary = new EdicaoEditoraLivroBoundary();
	
	private MenuBar menuBar = new MenuBar();
	
	private Menu menuArquivo = new Menu("Arquivo");
	private Menu menuLivro = new Menu("Livro");
	private Menu menuAutor = new Menu("Autor");
	private Menu menuEditora = new Menu("Editora");
	private Menu menuEdicao = new Menu("Edição");
	private Menu menuCliente = new Menu("Cliente");
	private Menu menuAssociacoes = new Menu("Associações");
	
	private MenuItem itemSair = new MenuItem("Sair");
	private MenuItem itemTelaInicial = new MenuItem("Tela Inicial");
	private MenuItem itemLivro = new MenuItem("Manter Livro");
	private MenuItem itemAutor = new MenuItem("Manter Autor");
	private MenuItem itemEditora = new MenuItem("Manter Editora");
	private MenuItem itemEdicao = new MenuItem("Manter Ediçao");
	private MenuItem itemCliente = new MenuItem("Manter Cliente");
	private MenuItem itemClienteLivro = new MenuItem("Cliente - Livro");
	private MenuItem itemLivroAutor = new MenuItem("Livro - Autor");
	private MenuItem itemEdicaoEditoraLivro = new MenuItem("Edição - Editora - Livro");
	
	private Button btnLivro = new Button("Manter Livro");
	private Button btnAutor = new Button("Manter Autor");
	private Button btnEditora = new Button("Manter Editora");
	private Button btnEdicao = new Button("Manter Edição");
	private Button btnCliente = new Button("Manter Cliente");
	private Button btnClienteLivro = new Button("Cliente - Livro");
	private Button btnLivroAutor = new Button("Livro - Autor");
	private Button btnEdicaoEditoraLivro = new Button("Edição - Editora - Livro");
	
	private Label lblDados = new Label("Manutenção de Dados");
	private Label lblAssociacoes = new Label("Associações");
	
	private String typeUser = "";
	
	@Override
	public Pane gerarTela(String typeUser) {
		
		this.typeUser = typeUser;
		
		menuArquivo.getItems().addAll(itemTelaInicial, itemSair);
		menuLivro.getItems().addAll(itemLivro);
		menuAutor.getItems().addAll(itemAutor);
		menuEditora.getItems().addAll(itemEditora);
		menuEdicao.getItems().addAll(itemEdicao);
		menuCliente.getItems().addAll(itemCliente);
		menuAssociacoes.getItems().addAll(itemClienteLivro, itemLivroAutor, itemEdicaoEditoraLivro);
		
		menuBar.getMenus().addAll(menuArquivo, menuLivro, menuAutor, menuEditora, menuEdicao, menuCliente, menuAssociacoes);
		
		itemSair.setOnAction(this);
		itemTelaInicial.setOnAction(this);
		itemLivro.setOnAction(this);
		itemAutor.setOnAction(this);
		itemEditora.setOnAction(this);
		itemEdicao.setOnAction(this);
		itemCliente.setOnAction(this);
		itemClienteLivro.setOnAction(this);
		itemLivroAutor.setOnAction(this);
		itemEdicaoEditoraLivro.setOnAction(this);
		btnLivro.setOnAction(this);
		btnAutor.setOnAction(this);
		btnEditora.setOnAction(this);
		btnEdicao.setOnAction(this);
		btnCliente.setOnAction(this);
		btnClienteLivro.setOnAction(this);
		btnLivroAutor.setOnAction(this);
		btnEdicaoEditoraLivro.setOnAction(this);
		
		lblBemVindo.setText("Bem vindo ao sistema da LibMaster, " + typeUser);
		
		lblBemVindo.setMinWidth(250);
		paneTxt.setHgap(1);
		paneTxt.setVgap(15);
		
		Group grp = new Group();
		InputStream is = getClass().getResourceAsStream("/boundary/images/telaPrincipal.png");
		Image img = new Image(is);
		Canvas canvas = new Canvas(1650, 850);
		GraphicsContext ctx = canvas.getGraphicsContext2D();
		ctx.drawImage(img, 0, 0);
		grp.getChildren().addAll(canvas, paneTxt);
		
		paneTxt.add(lblBemVindo, 150, 2);
		
		paneTxt.add(lblDados, 149, 13);
		paneTxt.add(lblAssociacoes, 281, 13);
		
		paneTxt.add(btnLivro, 149, 17);
		paneTxt.add(btnAutor, 149, 19);
		paneTxt.add(btnEditora, 149, 21);
		paneTxt.add(btnEdicao, 149, 23);
		paneTxt.add(btnCliente, 149, 25);
		paneTxt.add(btnClienteLivro, 281, 17);
		paneTxt.add(btnLivroAutor, 281, 19);
		paneTxt.add(btnEdicaoEditoraLivro, 281, 21);
		
		panePrincipal.setTop(menuBar);
		panePrincipal.setCenter(grp);
		
		setStyleLabel();
		
		return panePrincipal;
	}
	
	private void setStyleLabel() {
		
		lblDados.getStyleClass().add("label-main-screen");
		
		lblAssociacoes.getStyleClass().add("label-main-screen");
		
		lblBemVindo.getStyleClass().add("label-welcome");
		
	}

	@Override
	public void handle(ActionEvent e) {
		if (e.getTarget() == itemLivro || e.getTarget() == btnLivro) {
			panePrincipal.setCenter(livroBoundary.gerarTela(typeUser));
		} else if (e.getTarget() == itemAutor || e.getTarget() == btnAutor) {
			panePrincipal.setCenter(autorBoundary.gerarTela(typeUser));
		} else if (e.getTarget() == itemEditora || e.getTarget() == btnEditora) {
			panePrincipal.setCenter(editoraBoundary.gerarTela(typeUser));
		} else if (e.getTarget() == itemEdicao || e.getTarget() == btnEdicao) {
			panePrincipal.setCenter(edicaoBoundary.gerarTela(typeUser));
		} else if (e.getTarget() == itemCliente || e.getTarget() == btnCliente) {
			panePrincipal.setCenter(clienteBoundary.gerarTela(typeUser));
		} else if (e.getTarget() == itemClienteLivro || e.getTarget() == btnClienteLivro) {
			panePrincipal.setCenter(clBoundary.gerarTela(typeUser));
		} else if (e.getTarget() == itemLivroAutor || e.getTarget() == btnLivroAutor) {
			panePrincipal.setCenter(laBoundary.gerarTela(typeUser));
		} else if (e.getTarget() == itemEdicaoEditoraLivro || e.getTarget() == btnEdicaoEditoraLivro) {
			panePrincipal.setCenter(eelBoundary.gerarTela(typeUser));
		} else if (e.getTarget() == itemSair) {
			System.exit(0);
		} else if (e.getTarget() == itemTelaInicial) {
			gerarTelaMain(typeUser);
		}
	}
	
	public void gerarTelaMain(String typeUser) {
		
		this.typeUser = typeUser;
		
		paneTxt = new GridPane();
		
		lblBemVindo.setText("Bem vindo ao sistema da LibMaster, " + typeUser);
		
		lblBemVindo.setMinWidth(250);
		paneTxt.setHgap(1);
		paneTxt.setVgap(15);
		
		Group grp = new Group();
		InputStream is = getClass().getResourceAsStream("/boundary/images/telaPrincipal.png");
		Image img = new Image(is);
		Canvas canvas = new Canvas(1650, 850);
		GraphicsContext ctx = canvas.getGraphicsContext2D();
		ctx.drawImage(img, 0, 0);
		grp.getChildren().addAll(canvas, paneTxt);
		
		paneTxt.add(lblBemVindo, 150, 2);
		
		paneTxt.add(lblDados, 149, 13);
		paneTxt.add(lblAssociacoes, 281, 13);
		
		paneTxt.add(btnLivro, 149, 17);
		paneTxt.add(btnAutor, 149, 19);
		paneTxt.add(btnEditora, 149, 21);
		paneTxt.add(btnEdicao, 149, 23);
		paneTxt.add(btnCliente, 149, 25);
		paneTxt.add(btnClienteLivro, 281, 17);
		paneTxt.add(btnLivroAutor, 281, 19);
		paneTxt.add(btnEdicaoEditoraLivro, 281, 21);
		
		panePrincipal.setTop(menuBar);
		panePrincipal.setCenter(grp);
		
		setStyleLabel();
	}
}
