package boundary;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

import control.LivroControl;
import entity.Livro;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringWrapper;
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

public class LivroBoundary implements EventHandler<ActionEvent>,
										IGerarTela {
	
	private TextField tfCodigoLivro = new TextField();
	private TextField tfNomeLivro = new TextField();
	private TextField tfIdiomaLivro = new TextField();
	private TextField tfAnoLancamento = new TextField();
	
	private Button btnAdicionar = new Button("Adicionar");
	private Button btnAtualizar = new Button("Atualizar");
	private Button btnExcluir = new Button("Excluir");
	private Button btnPesquisar = new Button("Pesquisar");
	
	private Label lblCodigoLivro = new Label("Codigo: ");
	private Label lblNomeLivro = new Label("Nome: ");
	private Label lblIdiomaLivro = new Label("Idioma: ");
	private Label lblAnoLancamento = new Label("Ano de Lancamento: ");
	
	TableView<Livro> table = new TableView<>();
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	LivroControl control = new LivroControl();
	
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
		
		paneTxt.add(lblNomeLivro, 0, 6);
		paneTxt.add(tfNomeLivro, 1, 6);
		
		paneTxt.add(lblIdiomaLivro, 0, 7);
		paneTxt.add(tfIdiomaLivro, 1, 7);
		
		paneTxt.add(lblAnoLancamento, 0, 8);
		paneTxt.add(tfAnoLancamento, 1, 8);
		
		paneTxt.add(paneBotoes, 1, 11);
		paneTxt.add(new Label(""), 0, 13);
		
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
			if (isBlank(tfCodigoLivro.getText())) {
				JOptionPane.showMessageDialog(null, "Digite o código do livro a ser pesquisado", 
						"ERRO", JOptionPane.WARNING_MESSAGE);
			} else {
				executarComando("pesquisar");
			}
			
		} else if (e.getTarget() == btnExcluir) {
			if (isBlank(tfCodigoLivro.getText())) {
				JOptionPane.showMessageDialog(null, "Digite o código do livro a ser excluido", 
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
        Bindings.bindBidirectional(	tfCodigoLivro.textProperty(), 
        							control.getCodigoLivroProperty(), 
        							(StringConverter<Number>)converter);
        Bindings.bindBidirectional(tfNomeLivro.textProperty(), control.getNomeLivroProperty());
        Bindings.bindBidirectional(tfIdiomaLivro.textProperty(), control.getIdiomaLivroProperty());
        Bindings.bindBidirectional(tfAnoLancamento.textProperty(), 
        							control.getAnoLancamentoProperty(),
        							dateConverter);
	}
	
	@Override
	public void generateTable() {
		
		TableColumn<Livro, Integer> codigoColumn = new TableColumn<>("Codigo Livro");
		codigoColumn.setMinWidth(200);
		
		TableColumn<Livro, String> nomeLivroColumn = new TableColumn<>("Nome Livro");
		nomeLivroColumn.setMinWidth(200);
		
		TableColumn<Livro, String> idiomaLivroColumn = new TableColumn<>("Idioma Livro");
		idiomaLivroColumn.setMinWidth(200);
		
		TableColumn<Livro, String> anoLivroColumn = new TableColumn<>("Ano Lançamento Livro");
		anoLivroColumn.setMinWidth(200);
		
		codigoColumn.setCellValueFactory( new PropertyValueFactory<Livro, Integer>("codigoLivro"));
		nomeLivroColumn.setCellValueFactory( new PropertyValueFactory<Livro, String>("nomeLivro"));
		idiomaLivroColumn.setCellValueFactory( new PropertyValueFactory<Livro, String>("idiomaLivro"));
		anoLivroColumn.setCellValueFactory( (item) -> { return new ReadOnlyStringWrapper(item.getValue().getAnoLancamento().format(formatter)); 
		});
		
		table.getColumns().addAll(codigoColumn, nomeLivroColumn, idiomaLivroColumn, anoLivroColumn);
		table.setItems(control.getLista());
	}
	
	@Override
	public boolean campoIsBlank() {
		
		return isBlank(tfCodigoLivro.getText()) || isBlank(tfNomeLivro.getText()) || 
				isBlank(tfIdiomaLivro.getText()) || isBlank(tfAnoLancamento.getText());
	}
	
	@Override
	public boolean isBlank(String txt) {
		
		return txt == null || txt.trim().isEmpty();
	}

	@Override
	public void limparCampos() {
		
		tfCodigoLivro.setText("");
		tfNomeLivro.setText("");
		tfIdiomaLivro.setText("");
		tfAnoLancamento.setText("");
	}
	
	@Override
	public void executarComando(String cmd) {
		
		if ("adicionar".equals(cmd)) {
			try {
				control.inserirLivro();
			} catch (DAOException e) {
				e.printStackTrace();
			}
			limparCampos();
			
		} else if ("atualizar".equals(cmd)) {
			try {
				control.atualizarLivro();
			} catch (DAOException e) {
				e.printStackTrace();
			}
			
		} else if ("pesquisar".equals(cmd)) {
			try {
				control.buscarLivro();
			} catch (DAOException e) {
				e.printStackTrace();
			}
			
		} else if ("excluir".equals(cmd)) {
			try {
				control.excluirLivro();
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
}
