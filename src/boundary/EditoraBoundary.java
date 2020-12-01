package boundary;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

import control.EditoraControl;
import entity.Editora;
import entity.Livro;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.Scene;
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
import javafx.util.converter.LocalDateStringConverter;
import persistence.DAOException;

public class EditoraBoundary implements EventHandler<ActionEvent>,
										   IGerarTela {
	
	private TextField tfCodigoEditora = new TextField();
	private TextField tfNomeEditora = new TextField();
	private TextField tfLogradouroEditora = new TextField();
	private TextField tfNumLograEditora = new TextField();
	private TextField tfCepEditora = new TextField();
	private TextField tfTelefoneEditora = new TextField();
	
	private Button btnAdicionar = new Button("Adicionar");
	private Button btnAtualizar = new Button("Atualizar");
	private Button btnExcluir = new Button("Excluir");
	private Button btnPesquisar = new Button("Pesquisar");
	
	private Label lblCodigoEditora = new Label("Codigo: ");
	private Label lblNomeEditora = new Label("Nome: ");
	private Label lblLogradouroEditora = new Label("Logradouro: ");
	private Label lblNumLograEditora = new Label("Numero: ");
	private Label lblCepEditora = new Label("CEP: ");
	private Label lblTelefoneEditora = new Label("Telefone: ");
	
	private TableView<Editora> table = new TableView<>();
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private EditoraControl control = new EditoraControl();

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
		
		paneTxt.add(lblCodigoEditora, 0, 5);
		paneTxt.add(tfCodigoEditora, 1, 5);
		
		paneTxt.add(lblNomeEditora, 0, 6);
		paneTxt.add(tfNomeEditora, 1, 6);
		
		paneTxt.add(lblLogradouroEditora, 0, 7);
		paneTxt.add(tfLogradouroEditora, 1, 7);
		
		paneTxt.add(lblNumLograEditora, 0, 8);
		paneTxt.add(tfNumLograEditora, 1, 8);
		
		paneTxt.add(lblCepEditora, 0, 9);
		paneTxt.add(tfCepEditora, 1, 9);
		
		paneTxt.add(lblTelefoneEditora, 0, 10);
		paneTxt.add(tfTelefoneEditora, 1, 10);
		
		paneTxt.add(paneBotoes, 1, 13);
		paneTxt.add(new Label(""), 0, 17);
		
		btnAdicionar.setOnAction(this);
		btnAtualizar.setOnAction(this);
		btnPesquisar.setOnAction(this);
		btnExcluir.setOnAction(this);
		
		return panePrincipal;
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
			executarComando("pesquisar");
			
		} else if (e.getTarget() == btnExcluir) {
			if (isBlank(tfCodigoEditora.getText())) {
				JOptionPane.showMessageDialog(null, "Digite o código da editora a ser excluída", 
						"ERRO", JOptionPane.WARNING_MESSAGE);
			} else {
				executarComando("excluir");
			}
		}
	}
	
	@Override
	public void vincularCampos() {

		StringConverter<? extends Number> converter = new IntegerStringConverter();
		StringConverter<LocalDate> dateConverter = new LocalDateStringConverter(formatter, formatter);
		Bindings.bindBidirectional(	tfCodigoEditora.textProperty(), 
				control.getCodigoEditoraProperty(), 
				(StringConverter<Number>)converter);
		Bindings.bindBidirectional(tfNomeEditora.textProperty(), control.getNomeEditoraProperty());
		Bindings.bindBidirectional(tfLogradouroEditora.textProperty(), control.getLogradouroEditoraProperty());
		Bindings.bindBidirectional(	tfNumLograEditora.textProperty(), 
				control.getNumLograEditoraProperty(), 
				(StringConverter<Number>)converter);
		Bindings.bindBidirectional(tfCepEditora.textProperty(), control.getCepEditoraProperty());
		Bindings.bindBidirectional(tfTelefoneEditora.textProperty(), control.getTelefoneEditoraProperty());
	}
	
	@Override
	public void generateTable() {
		TableColumn<Editora, Integer> codigoColumn = new TableColumn<>("Codigo Livro");
		codigoColumn.setMinWidth(200);
		
		TableColumn<Editora, String> nomeEditoraColumn = new TableColumn<>("Nome Editora");
		nomeEditoraColumn.setMinWidth(200);
		
		TableColumn<Editora, String> logradouroColumn = new TableColumn<>("Logradouro");
		logradouroColumn.setMinWidth(200);
		
		TableColumn<Editora, Integer> numLograColumn = new TableColumn<>("Numero");
		numLograColumn.setMinWidth(200);
		
		TableColumn<Editora, String> cepEditoraColumn = new TableColumn<>("CEP");
		cepEditoraColumn.setMinWidth(200);
		
		TableColumn<Editora, String> telefoneColumn = new TableColumn<>("Telefone Editora");
		telefoneColumn.setMinWidth(200);
		
		codigoColumn.setCellValueFactory( new PropertyValueFactory<Editora, Integer>("codigoEditora"));
		nomeEditoraColumn.setCellValueFactory( new PropertyValueFactory<Editora, String>("nomeEditora"));
		logradouroColumn.setCellValueFactory( new PropertyValueFactory<Editora, String>("logradouroEditora"));
		numLograColumn.setCellValueFactory( new PropertyValueFactory<Editora, Integer>("numLograEditora"));
		cepEditoraColumn.setCellValueFactory( new PropertyValueFactory<Editora, String>("cepEditora"));
		telefoneColumn.setCellValueFactory( new PropertyValueFactory<Editora, String>("telefoneEditora"));
		
		table.getColumns().addAll(codigoColumn, nomeEditoraColumn, logradouroColumn, numLograColumn, cepEditoraColumn, telefoneColumn);
		table.setItems(control.getEditoras());
	}
	
	@Override
	public void executarComando(String cmd) {

		if ("adicionar".equals(cmd)) {
			try {
				control.inserirEditora();
			} catch (DAOException e) {
				e.printStackTrace();
			}
			limparCampos();
		} else if ("atualizar".equals(cmd)) {
			try {
				control.atualizarEditora();
			} catch (DAOException e) {
				e.printStackTrace();
			}
		} else if ("pesquisar".equals(cmd)) {
			try {
				control.buscarEditora();
			} catch (DAOException e) {
				e.printStackTrace();
			}
		} else if ("excluir".equals(cmd)) {
			try {
				control.excluirEditora();
			} catch (DAOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public boolean campoIsBlank() {
		return isBlank(tfCodigoEditora.getText()) || isBlank(tfNomeEditora.getText()) || 
				isBlank(tfLogradouroEditora.getText()) || isBlank(tfNumLograEditora.getText()) ||
				isBlank(tfCepEditora.getText()) || isBlank(tfTelefoneEditora.getText());
	}

	@Override
	public boolean isBlank(String txt) {
		
		return txt == null || txt.trim().isEmpty();
	}

	@Override
	public void limparCampos() {
		tfCodigoEditora.setText("");
		tfNomeEditora.setText("");
		tfLogradouroEditora.setText("");
		tfNumLograEditora.setText("");
		tfCepEditora.setText("");
		tfTelefoneEditora.setText("");
	}

}
