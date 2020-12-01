package boundary;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class SistemaPrincipal implements EventHandler<ActionEvent>,
									        IGerarTelaMain {
	
	private BorderPane panePrincipal = new BorderPane();
	
	private Label lblBemVindo = new Label("Bem vindo ao sistema da Livraria");
	
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
	private MenuItem itemLivro = new MenuItem("Manter Livro");
	private MenuItem itemAutor = new MenuItem("Manter Autor");
	private MenuItem itemEditora = new MenuItem("Manter Editora");
	private MenuItem itemEdicao = new MenuItem("Manter Edicao");
	private MenuItem itemCliente = new MenuItem("Manter Cliente");
	private MenuItem itemClienteLivro = new MenuItem("Cliente - Livro");
	private MenuItem itemLivroAutor = new MenuItem("Livro - Autor");
	private MenuItem itemEdicaoEditoraLivro = new MenuItem("Edição - Editora - Livro");
	
	private String typeUser = "";
	
	@Override
	public Pane gerarTela(String typeUser) {
		this.typeUser = typeUser;
		
		menuArquivo.getItems().addAll(itemSair);
		menuLivro.getItems().addAll(itemLivro);
		menuAutor.getItems().addAll(itemAutor);
		menuEditora.getItems().addAll(itemEditora);
		menuEdicao.getItems().addAll(itemEdicao);
		menuCliente.getItems().addAll(itemCliente);
		menuAssociacoes.getItems().addAll(itemClienteLivro, itemLivroAutor, itemEdicaoEditoraLivro);
		
		menuBar.getMenus().addAll(menuArquivo, menuLivro, menuAutor, menuEditora, menuEdicao, menuCliente, menuAssociacoes);
		
		itemSair.setOnAction(this);
		itemLivro.setOnAction(this);
		itemAutor.setOnAction(this);
		itemEditora.setOnAction(this);
		itemEdicao.setOnAction(this);
		itemCliente.setOnAction(this);
		itemClienteLivro.setOnAction(this);
		itemLivroAutor.setOnAction(this);
		itemEdicaoEditoraLivro.setOnAction(this);
		
		lblBemVindo.setStyle("-fx-font-size: 30pt;"
				+ "-fx-font-family: Segoe UI Semibold;");
		
		panePrincipal.setTop(menuBar);
		panePrincipal.setCenter(lblBemVindo);
		
		return panePrincipal;
	}
	
	@Override
	public void handle(ActionEvent e) {
		if (e.getTarget() == itemLivro) {
			panePrincipal.setCenter(livroBoundary.gerarTela(typeUser));
		} else if (e.getTarget() == itemAutor) {
			panePrincipal.setCenter(autorBoundary.gerarTela(typeUser));
		} else if (e.getTarget() == itemEditora) {
			panePrincipal.setCenter(editoraBoundary.gerarTela(typeUser));
		} else if (e.getTarget() == itemEdicao) {
			panePrincipal.setCenter(edicaoBoundary.gerarTela(typeUser));
		} else if (e.getTarget() == itemCliente) {
			panePrincipal.setCenter(clienteBoundary.gerarTela(typeUser));
		} else if (e.getTarget() == itemClienteLivro) {
			panePrincipal.setCenter(clBoundary.gerarTela(typeUser));
		} else if (e.getTarget() == itemLivroAutor) {
			panePrincipal.setCenter(laBoundary.gerarTela(typeUser));
		} else if (e.getTarget() == itemEdicaoEditoraLivro) {
			panePrincipal.setCenter(eelBoundary.gerarTela(typeUser));
		} else if (e.getTarget() == itemSair) {
			System.exit(0);
		}
	}
}
