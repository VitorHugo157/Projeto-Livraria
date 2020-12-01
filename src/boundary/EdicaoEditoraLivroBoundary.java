package boundary;

import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

import control.ClienteLivroControl;
import control.EdicaoEditoraLivroControl;
import entity.ClienteLivro;
import entity.EdicaoEditoraLivro;
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

public class EdicaoEditoraLivroBoundary implements EventHandler<ActionEvent>, 
												      IGerarTela {
	
	private TextField tfIsbn = new TextField();
	private TextField tfCodigoEditora = new TextField();
	private TextField tfCodigoLivro = new TextField();
	
	private Button btnAdicionar = new Button("Adicionar");
	private Button btnAtualizar = new Button("Atualizar");
	private Button btnExcluir = new Button("Excluir");
	private Button btnPesquisar = new Button("Pesquisar");
	
	private Label lblIsbn = new Label("ISBN: ");
	private Label lblCodigoEditora = new Label("Codigo Editora");
	private Label lblCodigoLivro = new Label("Codigo Livro");
	
	private TableView<EdicaoEditoraLivro> table = new TableView<>();
	private EdicaoEditoraLivroControl control = new EdicaoEditoraLivroControl();
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
		
		paneTxt.add(lblIsbn, 0, 5);
		paneTxt.add(tfIsbn, 1, 5);
		
		paneTxt.add(lblCodigoEditora, 0, 6);
		paneTxt.add(tfCodigoEditora, 1, 6);
		
		paneTxt.add(lblCodigoLivro, 0, 7);
		paneTxt.add(tfCodigoLivro, 1, 7);
		
		paneTxt.add(paneBotoes, 1, 10);
		
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
				control.inserirEdicaoEditoraLivro();
			} catch (DAOException e) {
				e.printStackTrace();
			};
			limparCampos();
		} else if ("atualizar".equals(cmd)) {
			try {
				control.atualizarEdicaoEditoraLivro();
			} catch (DAOException e) {
				e.printStackTrace();
			};
		} else if ("pesquisar".equals(cmd)) {
			try {
				control.buscarEdicaoEditoraLivro();
			} catch (DAOException e) {
				e.printStackTrace();
			}
		} else if ("excluir".equals(cmd)) {
			try {
				control.excluirEdicaoEditoraLivro();
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
			if ( !((isBlank(tfIsbn.getText()))) || !((isBlank(tfCodigoEditora.getText()))) || !((isBlank(tfCodigoLivro.getText()))) ) {
				JOptionPane.showMessageDialog(null, "Consultas de associações não devem ter campos preenchidos", 
						"ERRO", JOptionPane.WARNING_MESSAGE);
			} else {
				executarComando("pesquisar");
			}
			
		} else if (e.getTarget() == btnExcluir) {
			if (isBlank(tfIsbn.getText()) || isBlank(tfCodigoEditora.getText()) || isBlank(tfCodigoLivro.getText())) {
				JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos", 
						"ERRO", JOptionPane.WARNING_MESSAGE);
			} else {
				executarComando("excluir");
			}
		}
	}

	@Override
	public void vincularCampos() {
		StringConverter<? extends Number> converter = new IntegerStringConverter();
		Bindings.bindBidirectional(	tfIsbn.textProperty(), 
				control.getIsbnEdicaoProperty(), 
				(StringConverter<Number>)converter);
		Bindings.bindBidirectional(	tfCodigoEditora.textProperty(), 
				control.getCodigoEditoraProperty(), 
				(StringConverter<Number>)converter);
		Bindings.bindBidirectional(	tfCodigoLivro.textProperty(), 
				control.getCodigoLivroProperty(), 
				(StringConverter<Number>)converter);
	}

	@Override
	public void generateTable() {
		
		TableColumn<EdicaoEditoraLivro, Integer> isbnColumn = new TableColumn<>("ISBN");
		isbnColumn.setMinWidth(125);
		
		TableColumn<EdicaoEditoraLivro, Integer> editoraColumn = new TableColumn<>("Codigo Editora");
		editoraColumn.setMinWidth(125);
		
		TableColumn<EdicaoEditoraLivro, Integer> livroColumn = new TableColumn<>("Codigo Livro");
		livroColumn.setMinWidth(125);
		
		TableColumn<EdicaoEditoraLivro, Double> precoColumn = new TableColumn<>("Preço Edição");
		precoColumn.setMinWidth(125);
		
		TableColumn<EdicaoEditoraLivro, String> anoEdicaoColumn = new TableColumn<>("Ano Edição");
		anoEdicaoColumn.setMinWidth(125);
		
		TableColumn<EdicaoEditoraLivro, String> nomeEditoraColumn = new TableColumn<>("Nome Editora");
		nomeEditoraColumn.setMinWidth(150);
		
		TableColumn<EdicaoEditoraLivro, String> telEditoraColumn = new TableColumn<>("Telefone Editora");
		telEditoraColumn.setMinWidth(150);
		
		TableColumn<EdicaoEditoraLivro, String> nomeLivroColumn = new TableColumn<>("Nome Livro");
		nomeLivroColumn.setMinWidth(150);
		
		TableColumn<EdicaoEditoraLivro, String> idiomaLivroColumn = new TableColumn<>("Idioma Livro");
		idiomaLivroColumn.setMinWidth(150);
		
		TableColumn<EdicaoEditoraLivro, String> anoLancamentoColumn = new TableColumn<>("Lancamento Livro");
		anoLancamentoColumn.setMinWidth(150);
		
		isbnColumn.setCellValueFactory( new PropertyValueFactory<EdicaoEditoraLivro, Integer>("isbnEdicao"));
		editoraColumn.setCellValueFactory( new PropertyValueFactory<EdicaoEditoraLivro, Integer>("codigoEditora"));
		livroColumn.setCellValueFactory( new PropertyValueFactory<EdicaoEditoraLivro, Integer>("codigoLivro"));
		precoColumn.setCellValueFactory( new PropertyValueFactory<EdicaoEditoraLivro, Double>("precoEdicao"));
		anoEdicaoColumn.setCellValueFactory( (item) -> { return new ReadOnlyStringWrapper(item.getValue().getAnoEdicao().format(formatter)); 
		});
		nomeEditoraColumn.setCellValueFactory( new PropertyValueFactory<EdicaoEditoraLivro, String>("nomeEditora"));
		telEditoraColumn.setCellValueFactory( new PropertyValueFactory<EdicaoEditoraLivro, String>("telefoneEditora"));
		nomeLivroColumn.setCellValueFactory( new PropertyValueFactory<EdicaoEditoraLivro, String>("nomeLivro"));
		idiomaLivroColumn.setCellValueFactory( new PropertyValueFactory<EdicaoEditoraLivro, String>("idiomaLivro"));
		anoLancamentoColumn.setCellValueFactory( (item) -> { return new ReadOnlyStringWrapper(item.getValue().getAnoLancamento().format(formatter)); 
		});
		
		table.getColumns().addAll(isbnColumn, editoraColumn, livroColumn, 
				precoColumn, anoEdicaoColumn, 
				nomeEditoraColumn, telEditoraColumn, 
				nomeLivroColumn, idiomaLivroColumn, anoLancamentoColumn);
		table.setItems(control.getLista());
	}
	
	@Override
	public boolean campoIsBlank() {

		return isBlank(tfIsbn.getText()) || isBlank(tfCodigoEditora.getText()) || isBlank(tfCodigoLivro.getText());
	}
	
	@Override
	public boolean isBlank(String txt) {
		
		return txt == null || txt.trim().isEmpty();
	}
	
	@Override
	public void limparCampos() {
		
		tfIsbn.setText("");
		tfCodigoEditora.setText("");
		tfCodigoLivro.setText("");
	}
}
