package boundary;

import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

import control.LivroAutorControl;
import entity.ClienteLivro;
import entity.LivroAutor;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;
import persistence.DAOException;

public class LivroAutorBoundary implements EventHandler<ActionEvent>, 
										      IGerarTela {
	
	private TextField tfCodigoLivro = new TextField();
	private TextField tfCodigoAutor = new TextField();
	
	private Button btnAdicionar = new Button("Adicionar");
	private Button btnAtualizar = new Button("Atualizar");
	private Button btnExcluir = new Button("Excluir");
	private Button btnPesquisar = new Button("Pesquisar");
	
	private Label lblCodigoLivro = new Label("Codigo Livro: ");
	private Label lblCodigoAutor = new Label("Codigo Autor: ");
	
	private TableView<LivroAutor> table = new TableView<>();
	private LivroAutorControl control = new LivroAutorControl();
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	@Override
	public Pane gerarTela(String typeUser) {
		BorderPane panePrincipal = new BorderPane();
		GridPane paneTxt = new GridPane();
		FlowPane paneBotoes = new FlowPane();
		table = new TableView<>();
		
		paneTxt.setHgap(20);
		paneTxt.setVgap(15);
		paneBotoes.setVgap(10);
		paneBotoes.setHgap(25);
		
		ColumnConstraints colLabels = new ColumnConstraints();
		colLabels.setHalignment(HPos.RIGHT);
		colLabels.setPercentWidth(12);

		ColumnConstraints colTextField = new ColumnConstraints();
		colTextField.setPercentWidth(50);
		paneTxt.getColumnConstraints().addAll(colLabels, colTextField);
		
		panePrincipal.setCenter(paneTxt);
		panePrincipal.setBottom(table);
		
		vincularCampos();
		verifyUser(typeUser);
		generateTable();
		
		paneBotoes.getChildren().addAll(btnAdicionar, btnPesquisar, btnAtualizar, btnExcluir);
		
		paneTxt.add(lblCodigoLivro, 0, 5);
		paneTxt.add(tfCodigoLivro, 1, 5);
		
		paneTxt.add(lblCodigoAutor, 0, 6);
		paneTxt.add(tfCodigoAutor, 1, 6);
		
		paneTxt.add(paneBotoes, 1, 9);
		
		btnAdicionar.setOnAction(this);
		btnAtualizar.setOnAction(this);
		btnPesquisar.setOnAction(this);
		btnExcluir.setOnAction(this);
		
		return panePrincipal;
	}
	
	@Override
	public void executarComando(String cmd) {
		
		if ("adicionar".equals(cmd)) {
			try {
				control.inserirLivroAutor();
			} catch (DAOException e) {
				e.printStackTrace();
			}
			limparCampos();
		} else if ("atualizar".equals(cmd)) {
			try {
				control.atualizarLivroAutor();
			} catch (DAOException e) {
				e.printStackTrace();
			}
		} else if ("pesquisar".equals(cmd)) {
			try {
				control.buscarLivroAutor();
			} catch (DAOException e) {
				e.printStackTrace();
			}
		} else if ("excluir".equals(cmd)) {
			try {
				control.excluirLivroAutor();
			} catch (DAOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void verifyUser(String typeUser) {
		
		if ("user".equals(typeUser)) {
			btnAdicionar.setVisible(false);
			btnExcluir.setVisible(false);
			btnAtualizar.setVisible(false);
		}
	}

	@Override
	public void vincularCampos() {
		
		StringConverter<? extends Number> converter = new IntegerStringConverter();
		Bindings.bindBidirectional(	tfCodigoLivro.textProperty(), 
				control.getCodigoLivroProperty(), 
				(StringConverter<Number>)converter);
		Bindings.bindBidirectional(	tfCodigoAutor.textProperty(), 
				control.getCodigoAutorProperty(), 
				(StringConverter<Number>)converter);
	}

	@Override
	public void generateTable() {
		
		TableColumn<LivroAutor, Integer> livroColumn = new TableColumn<>("Codigo Livro");
		livroColumn.setMinWidth(125);
		
		TableColumn<LivroAutor, Integer> autorColumn = new TableColumn<>("Codigo Autor");
		autorColumn.setMinWidth(125);
		
		TableColumn<LivroAutor, String> nomeLivroColumn = new TableColumn<>("Nome Livro");
		nomeLivroColumn.setMinWidth(250);
		
		TableColumn<LivroAutor, String> idiomaLivroColumn = new TableColumn<>("Idioma Livro");
		idiomaLivroColumn.setMinWidth(125);
		
		TableColumn<LivroAutor, String> anoLivroColumn = new TableColumn<>("Ano Lancamento");
		anoLivroColumn.setMinWidth(150);
		
		TableColumn<LivroAutor, String> nomeAutorColumn = new TableColumn<>("Nome Autor");
		nomeAutorColumn.setMinWidth(250);
		
		TableColumn<LivroAutor, String> nacionalidadeColumn = new TableColumn<>("Nacionalidade Autor");
		nacionalidadeColumn.setMinWidth(250);
		
		livroColumn.setCellValueFactory( new PropertyValueFactory<LivroAutor, Integer>("codigoLivro"));
		autorColumn.setCellValueFactory( new PropertyValueFactory<LivroAutor, Integer>("codigoAutor"));
		nomeLivroColumn.setCellValueFactory( new PropertyValueFactory<LivroAutor, String>("nomeLivro"));
		idiomaLivroColumn.setCellValueFactory( new PropertyValueFactory<LivroAutor, String>("idiomaLivro"));
		anoLivroColumn.setCellValueFactory( (item) -> { return new ReadOnlyStringWrapper(item.getValue().getAnoLancamento().format(formatter)); 
		});
		nomeAutorColumn.setCellValueFactory( new PropertyValueFactory<LivroAutor, String>("nomeAutor"));
		nacionalidadeColumn.setCellValueFactory( new PropertyValueFactory<LivroAutor, String>("nacionalidade"));
		
		table.getColumns().addAll(livroColumn, autorColumn, 
				nomeLivroColumn, idiomaLivroColumn, anoLivroColumn, 
				nomeAutorColumn, nacionalidadeColumn);
		
		table.setItems(control.getLista());
	}

	@Override
	public void handle(ActionEvent e) {

		if (e.getTarget() == btnAdicionar) {
			if (campoIsBlank()) {
				JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos", 
						"ERRO", JOptionPane.WARNING_MESSAGE);
			} else {
				executarComando("adicionar");
			}
		
		} else if (e.getTarget() == btnAtualizar) {
			if (campoIsBlank()) {
				JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos", 
						"ERRO", JOptionPane.WARNING_MESSAGE);
			} else {
				executarComando("atualizar");
			}
		
		} else if (e.getTarget() == btnPesquisar) {
			if ( !((isBlank(tfCodigoLivro.getText()))) || !((isBlank(tfCodigoAutor.getText()))) ) {
				JOptionPane.showMessageDialog(null, "Consultas de associações não devem ter campos preenchidos", 
						"ERRO", JOptionPane.WARNING_MESSAGE);
			} else {
				executarComando("pesquisar");
			}
			
		} else if (e.getTarget() == btnExcluir) {
			if (isBlank(tfCodigoLivro.getText()) || isBlank(tfCodigoAutor.getText())) {
				JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos", 
						"ERRO", JOptionPane.WARNING_MESSAGE);
			} else {
				executarComando("excluir");
			}
		}
	}
	
	@Override
	public boolean campoIsBlank() {

		return isBlank(tfCodigoLivro.getText()) || isBlank(tfCodigoAutor.getText());
	}
	
	@Override
	public boolean isBlank(String txt) {
		
		return txt == null || txt.trim().isEmpty();
	}
	
	@Override
	public void limparCampos() {
		
		tfCodigoLivro.setText("");
		tfCodigoAutor.setText("");
	}
	
}
