package boundary;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

import control.AutorControl;
import entity.Autor;
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
import javafx.util.converter.LocalDateStringConverter;
import persistence.DAOException;

public class AutorBoundary implements EventHandler<ActionEvent>, 
										IGerarTela {

	private TextField txtCodigoAutor = new TextField();
	private TextField txtNomeAutor = new TextField();
	private TextField txtNascimentoAutor = new TextField();
	private TextField txtNacionalidadeAutor = new TextField();
	private TextField txtBiografiaAutor = new TextField();
	
	private Button btnAdicionar = new Button("Adicionar");
	private Button btnAtualizar = new Button("Atualizar");
	private Button btnExcluir = new Button("Excluir");
	private Button btnPesquisar = new Button("Pesquisar");
	
	private Label lblCodigoAutor = new Label("Codigo: ");
	private Label lblNomeAutor = new Label("Nome: ");
	private Label lblNascimentoAutor = new Label("Nascimento: ");
	private Label lblNacionalidadeAutor = new Label("Nacionalidade: ");
	private Label lblBiografiaAutor = new Label("Biografia: ");
	
	TableView<Autor> table = new TableView<>();
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	AutorControl control = new AutorControl();
	
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
		
		paneTxt.add(lblCodigoAutor, 0, 5);
		paneTxt.add(txtCodigoAutor, 1, 5);
		
		paneTxt.add(lblNomeAutor, 0, 6);
		paneTxt.add(txtNomeAutor, 1, 6);
		
		paneTxt.add(lblNascimentoAutor, 0, 7);
		paneTxt.add(txtNascimentoAutor, 1, 7);
		
		paneTxt.add(lblNacionalidadeAutor, 0, 8);
		paneTxt.add(txtNacionalidadeAutor, 1, 8);
		
		paneTxt.add(lblBiografiaAutor, 0, 9);
		paneTxt.add(txtBiografiaAutor, 1, 9);
		
		paneTxt.add(paneBotoes, 1, 12);
		paneTxt.add(new Label(""), 0, 16);
		
		btnAdicionar.setOnAction(this);
		btnAtualizar.setOnAction(this);
		btnPesquisar.setOnAction(this);
		btnExcluir.setOnAction(this);
		
		return panePrincipal;
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
			if (txtCodigoAutor.getText() == "") {
				JOptionPane.showMessageDialog(null, "Digite o código do autor a ser pesquisado", 
						"ERRO", JOptionPane.WARNING_MESSAGE);
			} else {
				executarComando("pesquisar");
			}
			
		} else if (e.getTarget() == btnExcluir) {
			if (txtCodigoAutor.getText() == "") {
				JOptionPane.showMessageDialog(null, "Digite o código do autor a ser excluido", 
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
		Bindings.bindBidirectional(	txtCodigoAutor.textProperty(), 
				control.getCodigoAutorProperty(), 
				(StringConverter<Number>)converter);
		Bindings.bindBidirectional(txtNomeAutor.textProperty(), control.getNomeAutorProperty());
		Bindings.bindBidirectional(txtNacionalidadeAutor.textProperty(), control.getNacionalidadeAutorProperty());
		Bindings.bindBidirectional(txtBiografiaAutor.textProperty(), control.getBiografiaAutorProperty());
		Bindings.bindBidirectional(txtNascimentoAutor.textProperty(), 
				control.getNascimentoAutorProperty(),
				dateConverter);
	}
	
	@Override
	public void generateTable() {

		TableColumn<Autor, Integer> codigoColumn = new TableColumn<>("Codigo Autor");
		codigoColumn.setMinWidth(200);

		TableColumn<Autor, String> nomeAutorColumn = new TableColumn<>("Nome Autor");
		nomeAutorColumn.setMinWidth(200);

		TableColumn<Autor, String> nascimentoColumn = new TableColumn<>("Nascimento");
		nascimentoColumn.setMinWidth(200);

		TableColumn<Autor, String> nacionalidadeColumn = new TableColumn<>("Nacionalidade");
		nacionalidadeColumn.setMinWidth(200);
		
		TableColumn<Autor, String> biografiaColumn = new TableColumn<>("Biografia");
		biografiaColumn.setMinWidth(200);

		codigoColumn.setCellValueFactory( new PropertyValueFactory<Autor, Integer>("codigoAutor"));
		nomeAutorColumn.setCellValueFactory( new PropertyValueFactory<Autor, String>("nomeAutor"));
		nascimentoColumn.setCellValueFactory( (item) -> { return new ReadOnlyStringWrapper(item.getValue().getNascimentoAutor().format(formatter)); 
		});
		nacionalidadeColumn.setCellValueFactory( new PropertyValueFactory<Autor, String>("nacionalidadeAutor"));
		biografiaColumn.setCellValueFactory( new PropertyValueFactory<Autor, String>("biografiaAutor"));
		

		table.getColumns().addAll(codigoColumn, nomeAutorColumn, nascimentoColumn, nacionalidadeColumn, biografiaColumn);
		table.setItems(control.getLista());
	}
	
	@Override
	public void executarComando(String cmd) {
		
		if ("adicionar".equals(cmd)) {
			try {
				control.inserirAutor();
			} catch (DAOException e) {
				e.printStackTrace();
			}
			limparCampos();
			
		} else if ("atualizar".equals(cmd)) {
			try {
				control.atualizarAutor();
			} catch (DAOException e) {
				e.printStackTrace();
			}
			
		} else if ("pesquisar".equals(cmd)) {
			try {
				control.buscarAutor();
			} catch (DAOException e) {
				e.printStackTrace();
			}
			
		} else if ("excluir".equals(cmd)) {
			try {
				control.excluirAutor();
			} catch (DAOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public boolean campoIsBlank() {

		return isBlank(txtCodigoAutor.getText()) || isBlank(txtNomeAutor.getText()) || 
				isBlank(txtNascimentoAutor.getText()) || isBlank(txtNacionalidadeAutor.getText()) ||
				isBlank(txtBiografiaAutor.getText());
	}
	
	@Override
	public boolean isBlank(String txt) {
		
		return txt == null || txt.trim().isEmpty();
	}
	
	@Override
	public void limparCampos() {
		
		txtCodigoAutor.setText("");
		txtNomeAutor.setText("");
		txtNascimentoAutor.setText("");
		txtNacionalidadeAutor.setText("");
		txtBiografiaAutor.setText("");
	}
	
	@Override
	public void verifyUser(String typeUser) {
		
		if ("user".equals(typeUser)) {
			btnAdicionar.setVisible(false);
			btnExcluir.setVisible(false);
			btnAtualizar.setVisible(false);
		}
	}
}
